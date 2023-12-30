package com.keyman.licensemanager;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
public class Instance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 30)
    private String name;

    @Size(max = 30)
    private String ipAddress;

    @Size(max = 30)
    private String type;

    private int status;

    @ManyToOne
    @JoinColumn(name = "contract_id")
    private Contract contract;

    // Getters and setters

    public void setId(long Id_)
    {
        id = Id_;
    }
}

