package com.keyman.licensemanager.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Size(max = 20)
    private String ipAddress1;

    @Size(max = 20)
    private String ipAddress2;

    @Size(max = 20)
    private String ipAddress3;

   @Lob
    private String licenseKey;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private Long field1;

    private Long field2;

    private Long field3;

    private String version;

    // Getters and setters
}
