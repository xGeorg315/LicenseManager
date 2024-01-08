package com.keyman.licensemanager.DTOs;

import lombok.Data;

@Data
public class RegisterDTO {
    String username;
    String password;
    String name;
    String lastName;
    String email;
    String role_user;
    String phoneNumber1;
    String phoneNumber2;
    Long customer_id;

    public boolean checkIfNull()
    {
        if(username == null|| username.equals("")) return true;
        if(password == null || password.equals("")) return true;
        if(name == null || name.equals("")) return true;
        if(lastName == null || lastName.equals("")) return true;
        if(email == null || email.equals("")) return true;
        if(phoneNumber1 == null || phoneNumber1.equals("")) return true;
        if(phoneNumber2 == null || phoneNumber2.equals("")) return true;
        if(role_user == null || role_user.equals("")) return true;
        //if(customer_id == null) return true;


        return false;
    }
 
}