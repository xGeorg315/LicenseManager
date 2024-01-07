package com.keyman.licensemanager.DTOs;

import lombok.Data;

@Data
public class UserUpdateDTO {
    String username;
    String firstName;
    String lastName;
    String email;
    String phoneNumber1;
    String phoneNumber2;
    String password;
}
