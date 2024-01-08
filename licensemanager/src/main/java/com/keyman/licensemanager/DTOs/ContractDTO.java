package com.keyman.licensemanager.DTOs;


import lombok.Data;

@Data
public class ContractDTO {
    
    private String startDate;
    private String endDate;
    private String ip1;
    private String ip2;
    private String ip3;
    private String licensekey;
    private Long user1_id;
    private Long user2_id;
    private Long customer_id;
    private Long field1;
    private Long field2;
    private Long field3;
    private String version;

    public boolean checkIfNull()
    {
        if(startDate == null) return true;
        if(endDate == null) return true;
        if(ip1 == null) return true;
        if(licensekey == null) return true;
        if(user1_id == null) return true;
        if(user2_id == null) return true;
        if(customer_id == null) return true;
        if(field1 == null) return true;
        if(field2 == null) return true;
        if(field3 == null) return true;
        if(version == null) return true;

        return false; 
    }
}
