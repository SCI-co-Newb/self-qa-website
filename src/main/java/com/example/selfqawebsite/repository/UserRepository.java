package com.example.selfqawebsite.repository;

import com.example.selfqawebsite.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);

    void deleteByUsernameAndPassword(String username, String password);
}

