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

    public void setId(Long iD)
    {
        this.id = iD;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getDepartment()
    {
        return department;
    }

    public void setDepartment( String department_)
    {
        this.department = department_;
    }

    public void setStreet(String street_)
    {
        this.street = street_;
    }

    public String getStreet()
    {
        return street;
    }

    public void setTown(String town)
    {
        this.town = town;
    }

    public String getTown()
    {
        return town;
    }

    public void setZipCode(String zipCode)
    {
        this.zipCode = zipCode;
    }

    public String getZipCode()
    {
        return town;
    }

    public void setCountry(String country)
    {
        this.country = country;
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
