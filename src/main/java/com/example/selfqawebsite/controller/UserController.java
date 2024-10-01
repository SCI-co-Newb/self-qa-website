package com.example.selfqawebsite.controller;

import com.example.selfqawebsite.entity.User;
import com.example.selfqawebsite.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private UserController(UserService userService) {
        this.userService = userService;
    }

    // This method finds the user by their id,
    // returns an ok response if found and user json,
    // else returns a not found response
    @GetMapping("/{requestedId}")
    public ResponseEntity<User> findUserById(@PathVariable Long requestedId) {
        User user = userService.findById(requestedId);

        if (user != null) {
            // 200 meaning OK user is found
            return ResponseEntity.ok(user);
        } else {
            // 404 meaning not founds
            return ResponseEntity.notFound().build();
        }
    }

    // probably make get methods to findbyusername and findbyusernameandpassword for different scenerios

    // This method saves user into the repository and creates a link so id is linked to the url,
    // making the above function properly
    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucb) {
        User savedUser = userService.save(user);
        URI locationOfNewUser = ucb
                .path("/users/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(locationOfNewUser).build();
    }
}
