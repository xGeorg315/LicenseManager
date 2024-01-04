package com.keyman.licensemanager.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;

import java.util.Date;

@Entity
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

    @ManyToOne
    @JoinColumn(name = "user1_id")
    private UserEntity user1;

    @ManyToOne
    @JoinColumn(name = "user2_id")
    private UserEntity user2;

    private int field1;

    private int field2;

    private int field3;

    private int field4;

    // Getters and setters

    public void setId(long Id_)
    {
        id = Id_;
    }
}
