package com.springboot.learnspringboot.socialmedia;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class VersioningUserController {

   /* URL Versioning*/
    @GetMapping("/v1/person")
    public UserV1 getFirsUserV1(){
        return new UserV1(1, "Andrey", LocalDate.now());
    }

    @GetMapping("/v2/person")
    public UserV2 getFirsUserV2(){
        return new UserV2(1, "Andrey");
    }

    /*Request parameter versioning*/
    @GetMapping(path ="/person", params = "version=1") // only if request param version equals 1 call this API
    public UserV1 getFirsUserV11(){
        return new UserV1(1, "Andrey", LocalDate.now());
    }

    @GetMapping(path ="/person", params = "version=2") // only if request param version equals 1 call this API
    public UserV2 getFirsUserV22(){
        return new UserV2(1, "Andrey");
    }

    /*Header param versioning*/
    @GetMapping(path ="/person/header", headers = "API-VERSION=1") // only if request param version equals 1 call this API
    public UserV1 getFirsUserV111(){
        return new UserV1(1, "Andrey", LocalDate.now());
    }

    @GetMapping(path ="/person/header", headers = "API-VERSION=2") // only if request param version equals 1 call this API
    public UserV2 getFirsUserV222(){
        return new UserV2(1, "Andrey");
    }

}
