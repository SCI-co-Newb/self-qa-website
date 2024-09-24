package com.example.selfqawebsite;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;
import java.net.URI;

@RestController
@RequestMapping("/users")
public class SelfQaWebsiteController {
    private final SelfQaWebsiteRepository selfQaWebsiteRepository;

    private SelfQaWebsiteController(SelfQaWebsiteRepository selfQaWebsiteRepository) {
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
                .buildAndExpand(savedUser.id())
                .toUri();
        return ResponseEntity.created(locationOfNewUser).build();
    }
}
