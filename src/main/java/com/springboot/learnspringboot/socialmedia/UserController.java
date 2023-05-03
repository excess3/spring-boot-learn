package com.springboot.learnspringboot.socialmedia;

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
    public List<User> retrieveAllUsers()
    {
        return daoService.findAll();
    }

    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id)
    {
        Optional<User> user = daoService.findUser(id);

        if(user.isEmpty())
        {
            /*Exception will be seen in response if dev tools plugin exists in pom
            and if the application is not run from jar (in case of jar the dev tools will be automatically disabled)*/
            throw new UserNotFoundException("id:" + id);
        }

        return user.get();
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user)
    {
        User savedUser = daoService.createUser(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
