package com.keyman.licensemanager.entities;


import javax.persistence.*;
import javax.validation.constraints.Size;


import aj.org.objectweb.asm.Type;
import lombok.Data;


@Entity
@Data
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



    private Long ContractId;

    // Getters and setters

    public void setId(long Id_)
    {
        id = Id_;
    }

    public Long getId()
    {
        return id;
    }

    public void setName(String name_)
    {
        name = name_;
    }

    public String getName()
    {
        return name;
    }
    
    public void setIpAdress(String ipAddress_)
    {
        ipAddress = ipAddress_;
    }

    public String getIpAdress()
    {
        return ipAddress;
    }

    public void setType(String type_)
    {
        type = type_;
    }

    public String geType()
    {
        return type;
    }

    public void setStatus(int status_)
    {
        status = status_;
    }

    public int getStatus()
    {
        return status;
    }


}

