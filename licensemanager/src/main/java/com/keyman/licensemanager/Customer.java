package com.keyman.licensemanager;

import jakarta.validation.constraints.Size;
import jakarta.persistence.*;
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
    private List<User> users;

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

    public String getDepartment() {
        return department;
    }

    public String getStreet() {
        return street;
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

    public Object getUsers() {
        return users;
    }
}
