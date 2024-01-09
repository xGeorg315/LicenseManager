package com.keyman.licensemanager.DTOs;
import com.keyman.licensemanager.entities.Instance;

public class InstanceDTO {
    private int status;
    private String ipAdress;
    private String name;
    private String type;
    private Long id;
    

Instance instance;

public void setStatus(int status_)
{
    this.status = status_;
}

public int getStatus()
{
    return status;
}

public void setIpAdress(String ipAdress_)
{
    this.ipAdress = ipAdress_;
}

public String getIpAdress()
{
    return ipAdress;
}

public void setName(String name_)
{
    this.name = name_;
}

public String getName()
{
    return name;
}

public void setType(String type_)
{
    this.type = type_;
}

public String getType()
{
    return type;
}

public Long getId()
{
    return id;
}

public void setId(Long id_)
{
    this.id = id_;
}

}
