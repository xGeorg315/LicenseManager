package com.keyman.licensemanager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.keyman.licensemanager.DTOs.LoginDTO;
/* 
@RestController
@RequestMapping("/auth/login")
public class LoginController {

    private AuthenticationManager authenticationManagerBean;

    public LoginController(AuthenticationManager authenticationManagerBean)
    {
        this.authenticationManagerBean = authenticationManagerBean;
    }

    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
       
        Authentication authentication = authenticationManagerBean.authenticate(
            new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));



        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("user signed in success", HttpStatus.OK);
    }
}
*/
