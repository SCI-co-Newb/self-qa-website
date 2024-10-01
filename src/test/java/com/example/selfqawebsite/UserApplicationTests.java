package com.example.selfqawebsite;

import com.example.selfqawebsite.entity.User;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = SelfQaWebsiteApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserApplicationTests {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void shouldCreateUser() {
        User newUser = new User();
        newUser.setUsername("admin");
        newUser.setPassword("admin");
        ResponseEntity<Void> responseEntity = restTemplate.postForEntity("/users", newUser, Void.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        URI location = responseEntity.getHeaders().getLocation();
        assertThat(location).isNotNull();
        assertThat(location.getPath()).startsWith("/users/");
    }

}
