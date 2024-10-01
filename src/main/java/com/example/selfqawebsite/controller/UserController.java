package com.example.selfqawebsite.controller;

import com.example.selfqawebsite.repository.UserRepository;
import com.example.selfqawebsite.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;
import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository selfQaWebsiteRepository;

    private UserController(UserRepository selfQaWebsiteRepository) {
        this.selfQaWebsiteRepository = selfQaWebsiteRepository;
    }

    // This method finds the user by their id,
    // returns an ok response if found and user json,
    // else returns a not found response
    @GetMapping("/{requestedId}")
    public ResponseEntity<User> findById(@PathVariable Long requestedId) {
        Optional<User> userOptional = selfQaWebsiteRepository.findById(requestedId);
        return userOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // This method saves user into the repository and creates a link so id is linked to the url,
    // making the above function properly
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody User user, UriComponentsBuilder ucb) {
        User savedUser = selfQaWebsiteRepository.save(user);
        URI locationOfNewUser = ucb
                .path("/users/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(locationOfNewUser).build();
    }
}
