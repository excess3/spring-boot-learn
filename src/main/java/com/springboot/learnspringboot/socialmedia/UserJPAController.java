package com.springboot.learnspringboot.socialmedia;

import com.springboot.learnspringboot.socialmedia.jpa.UserRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJPAController {

    private UserRepository userRepository;//new

    public UserJPAController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //http://localhost:9090/users
    @GetMapping("/users")
    public List<User> retrieveAllUsers()
    {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id)
    {
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty())
        {
           /* Exception will be seen in response if dev tools plugin exists in pom
            and if the application is not run from jar (in case of jar the dev tools will be automatically disabled)*/
            throw new UserNotFoundException("id:" + id);
        }

        EntityModel<User> entityModel = EntityModel.of(user.get());
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());// Add link for user to retrieve all users

        entityModel.add(link.withRel("all-users"));

        return entityModel;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) // by adding @Valid annotation we say that validation inside User bean should be used
    {
        User savedUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id)
    {
        userRepository.deleteById(id);
    }

    @GetMapping("/users/{id}/posts")
    public List<Post> retrieveUserPosts(@PathVariable int id)
    {
        Optional<User> user = userRepository.findById(id);

        return user.get().getPosts();
    }
}
