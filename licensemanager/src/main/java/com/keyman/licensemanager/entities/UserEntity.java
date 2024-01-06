package com.keyman.licensemanager.entities;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.context.properties.bind.Name;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.sql.DataSourceDefinition;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Size(max = 30)
    private String firstName;

    @Size(max = 30)
    private String lastName;

    @Size(max = 30)
    private String loginName;

    @Size(max = 30)
    private String token;

    @Size(max = 30)
    private String email;

    private String password; // Hashed password field

    private boolean isAdmin;

    @Size(max = 30)
    private String phoneNumber1;

    @Size(max = 30)
    private String phoneNumber2;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Roles> roles = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "customer_id")  // adjust the column name based on your schema
    private Customer customer;

    // Getters and setters

    public void setId(long id)
    {
        this.id = id;
    }

    public String getPassword()
    {
        return password;
    }

    public String getName()
    {
        return name;
    }
    // Add other methods as needed
}
