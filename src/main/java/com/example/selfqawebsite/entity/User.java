package com.example.selfqawebsite.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users") // Map to the 'users' table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Primary key auto-increment
    @Column(name = "id")  // Explicitly map to the 'id' column
    private Long id;

    @Column(name = "username", nullable = false, unique = true)  // Map to the 'username' column
    private String username;

    @Column(name = "password", nullable = false)  // Map to the 'password' column
    private String password;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
