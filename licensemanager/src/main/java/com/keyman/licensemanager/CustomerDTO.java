package com.keyman.licensemanager;

import java.util.List;

import com.fasterxml.jackson.core.sym.Name;

public class CustomerDTO {

   
    private Long id;
    private String name;
    private String department;
    private String street;
    private String town;
    private String zipCode;
    private String country;
    private Object users;

    Customer customer;
    // Getters and setters

    public Long getId()
    {
        return id;
    }

    public void setId(Long long1)
    {
        id = long1;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String Name_)
    {
        name = Name_;
    }
    
    public String getDepartment( Customer department_)
    {
        return department;
    }

    public void setDepartment( String department_)
    {
        department = department_;
    }

    public void setStreet(String Street_) 
    {
        street = Street_;
    }

    public String getStreet()
    {
        return street;
    }

    public void setTown(String town2) 
    {
        town = town2;
    }

    public String getTown()
    {
        return town;
    }

    public void setZipCode(String zipCode2) 
    {
        zipCode = zipCode2;
    }

    public String getZipCode()
    {
        return town;
    }

    public void setCountry(String country2) 
    {
        country = country2;
    }

    public String getCountry()
    {
        return town;
    }

    public void setUsers(Object object)
    {
        users = object;
    }

    public Object getUsers()
    {
        return users;
    }

    
    
}
