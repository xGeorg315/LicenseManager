package com.keyman.licensemanager.entities;

import javax.validation.constraints.Size;

import org.apache.tomcat.jni.User;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 60)
    private String name;

    @Size(max = 30)
    private String department;

    @Size(max = 60)
    private String street;

    @Size(max = 60)
    private String town;

    @Size(max = 60)
    private String zipCode;

    @Size(max = 30)
    private String country;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<UserEntity> users = new ArrayList<UserEntity>();;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Contract> contracts = new ArrayList<Contract>();;

    // Getters and setters
    
    public Long getId()
    {
        return id;
    }

    public void setId(long ID_)
    {
        id = ID_;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name_)
    {
        name = name_;
    }

    public void setDepartment(String department_)
    {
        department = department_;
    }

    public void setStreet(String street_)
    {
        street = street_;
    }

    public void setZipCode(String zipCode_)
    {
        zipCode = zipCode_;
    }

    public void setTown(String town_)
    {
        town = town_;
    }

    public void setCountry(String country_)
    {
        country = country_;
    }

    public String getDepartment() {
        return department;
    }

    public String getStreet() {
        return department;
    }

    public String getTown() {
        return town;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCountry() {
        return country;
    }

    /*public Object getUsers() {
        return users;
    }*/
}
