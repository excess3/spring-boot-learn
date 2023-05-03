package com.springboot.learnspringboot.socialmedia;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private UserDaoService daoService;

    public UserController(UserDaoService service) {
        this.daoService = service;
    }

    //http://localhost:9090/users
    @GetMapping("/users")
    public List<UserV1> retrieveAllUsers()
    {
        return daoService.findAll();
    }

    @GetMapping("/users/{id}")
    public UserV1 retrieveUser(@PathVariable int id)
    {
        Optional<UserV1> user = daoService.findUser(id);

        if(user.isEmpty())
        {
           /* Exception will be seen in response if dev tools plugin exists in pom
            and if the application is not run from jar (in case of jar the dev tools will be automatically disabled)*/
            throw new UserNotFoundException("id:" + id);
        }

        return user.get();
    }

    @PostMapping("/users")
    public ResponseEntity<UserV1> createUser(@Valid @RequestBody UserV1 user) // by adding @Valid annotation we say that validation inside User bean should be used
    {
        UserV1 savedUser = daoService.createUser(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id)
    {
        daoService.deleteById(id);
    }
}
