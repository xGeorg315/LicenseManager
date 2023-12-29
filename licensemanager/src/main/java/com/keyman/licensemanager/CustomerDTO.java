package com.keyman.licensemanager;

import java.util.List;

public class CustomerDTO {

    private Long id;
    private String name;
    private String department;
    private String street;
    private String town;
    private String zipCode;
    private String country;
    private List<User> users;

    // Getters and setters

    public Long getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }
}
