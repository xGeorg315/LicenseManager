package com.keyman.licensemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.keyman.licensemanager.entities.UserEntity;
import com.keyman.licensemanager.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserEntityController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserEntity getUserById(@PathVariable Long id) {
        return userService.getUserById(id).orElse(null);
    }

    @PutMapping("/admin/{id}")
    public UserEntity updateUser(@PathVariable Long id, @RequestBody UserEntity updatedUser) {
        return userService.updateUser(id, updatedUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PostMapping("/create-user")
    public UserEntity createUser(@RequestBody UserEntity user) {
        return userService.createUser(user);
    }
}