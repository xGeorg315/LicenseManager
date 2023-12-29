package com.keyman.licensemanager;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 30)
    private String firstName;

    @Size(max = 30)
    private String lastName;

    @Size(max = 30)
    private String loginName;

    @Size(max = 30)
    private String token;

    @Size(max = 30)
    private String email;

    private String password; // Hashed password field

    private boolean isAdmin;

    @Size(max = 30)
    private String phoneNumber1;

    @Size(max = 30)
    private String phoneNumber2;

    // Getters and setters

    public void setId(long id)
    {
        this.id = id;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }

    // Add other methods as needed
}
