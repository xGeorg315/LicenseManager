package com.keyman.licensemanager.DTOs;

import java.util.List;

import com.keyman.licensemanager.entities.Contract;
import com.keyman.licensemanager.entities.Customer;

public class AllCustomerReturnDTO {
    
    public AllCustomerReturnDTO(List<Customer> customers, List<Contract> contracts)
    {
        this.customers = customers;
        this.contracts = contracts;
    }

    private List<Customer> customers;
    private List<Contract> contracts;
}
