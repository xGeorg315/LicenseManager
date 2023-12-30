package com.keyman.licensemanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping
    public String login(@RequestBody LoginDTO loginDTO) {
        // Retrieve user by loginName from the database
        User user = userService.getUserByLoginName(loginDTO.getLoginName());

        // Check if the user exists and the password matches
        if (user != null && isPasswordValid(loginDTO.getPassword(), user.getPassword())) {
            return "Login successful";
        } else {
            return "Login failed";
        }
    }

    // Helper method to validate the password
    private boolean isPasswordValid(String rawPassword, String hashedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }
}

