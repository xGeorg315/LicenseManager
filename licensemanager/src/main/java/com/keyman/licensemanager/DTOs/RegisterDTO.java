package com.keyman.licensemanager.DTOs;

import lombok.Data;

@Data
public class RegisterDTO {
    String username;
    String password;
    String name;
    String lastName;
    String email;
    boolean isAdmin;
    String phoneNumber1;
    String phoneNumber2;

    public boolean checkIfNull()
    {
        if(username.equals(null) || username.equals("")) return true;
        if(password.equals(null) || password.equals("")) return true;
        if(name.equals(null) || name.equals("")) return true;
        if(lastName.equals(null) || lastName.equals("")) return true;
        if(email.equals(null) || email.equals("")) return true;
        if(phoneNumber1.equals(null) || phoneNumber1.equals("")) return true;
        if(phoneNumber2.equals(null) || phoneNumber2.equals("")) return true;

        return false;
    }
    public boolean isAdmin()
    {
        if(isAdmin) return true;
        return false;
    }
}