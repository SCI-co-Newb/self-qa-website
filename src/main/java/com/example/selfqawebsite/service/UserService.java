package com.example.selfqawebsite.service;

import com.example.selfqawebsite.entity.User;
import com.example.selfqawebsite.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // POST method
    public User save(User user) {
        return userRepository.save(user);
    }

    // GET methods
    public User findById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    // DELETE method
    public String deleteById(long id) {
        userRepository.deleteById(id);
        return "product removed";
    }

    public String deleteByUsernameAndPassword(String username, String password) {
        userRepository.deleteByUsernameAndPassword(username, password);
        return "product removed";
    }
}
