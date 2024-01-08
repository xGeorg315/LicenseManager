package com.keyman.licensemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.keyman.licensemanager.DTOs.UserUpdateDTO;
import com.keyman.licensemanager.entities.UserEntity;
import com.keyman.licensemanager.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserEntityController {

    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserEntityController(PasswordEncoder passwordEncoder)
    {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/get-{id}")
    public UserEntity getUserById(@PathVariable Long id) {
        return userService.getUserById(id).orElse(null);
    }

    @PutMapping("/admin/update-{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable Long id, @RequestBody UserUpdateDTO updatedUser) {
        
        UserEntity currentUser = userService.getUserById(id).get();

        UserEntity user = userService.userChangeNonNullValues(updatedUser, currentUser, true);

        return new ResponseEntity<>(userService.updateUser(id, user), HttpStatus.OK);
    }

    @PutMapping("/update")
    public UserEntity updateUser(@RequestBody UserUpdateDTO updatedUser) {
        
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity loggedUser;
        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            loggedUser = userService.getUserByLoginName(username);
        } else {
            String username = principal.toString();
            loggedUser = userService.getUserByLoginName(username);
        }
        Long id = loggedUser.getId();
        UserEntity user = userService.userChangeNonNullValues(updatedUser, loggedUser, false);
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/admin/delete-{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }


}