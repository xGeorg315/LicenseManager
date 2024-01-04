package com.keyman.licensemanager.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class TestController
{
    @GetMapping("/home")
    public String home() {
        return "success";
    }
    
}