package com.keyman.licensemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.keyman.licensemanager.DTOs.AllCustomerReturnDTO;
import com.keyman.licensemanager.DTOs.CustomerDTO;
import com.keyman.licensemanager.entities.Customer;
import com.keyman.licensemanager.services.ContractService;
import com.keyman.licensemanager.services.CustomerService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.keyman.licensemanager.DTOs.AllCustomerReturnDTO;
//Test




@RestController
@RequestMapping("/customers")
public class CustomerController {

    String first_letter = "";
    char firstChar = ' ';
    List<CustomerDTO> liste;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ContractService contractService;

    @PostMapping("/admin/create_Customer")
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        CustomerDTO createdCustomer = customerService.createCustomer(customerDTO);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }


    @GetMapping("/list_customers")

    public @ResponseBody AllCustomerReturnDTO list_customers() {
        AllCustomerReturnDTO allCustomerReturnDTO = new AllCustomerReturnDTO(customerService.getAllCustomers(), contractService.getAllContracts());
        return allCustomerReturnDTO;
    }


    @GetMapping("/admin/list_customers")
    public ResponseEntity<AllCustomerReturnDTO> list_customers_admin() 
    {
        AllCustomerReturnDTO allCustomerReturnDTO = new AllCustomerReturnDTO(customerService.getAllCustomers(), contractService.getAllContracts());
        return new ResponseEntity<>(allCustomerReturnDTO, HttpStatus.OK);

    }
    
    
    @GetMapping("list_first_customer")
    public ResponseEntity<CustomerDTO> getFirstCustomerWithUsers() {
    List<CustomerDTO> customersWithUsers = customerService.getAllCustomersWithUsers();

    if (!customersWithUsers.isEmpty()) {
        CustomerDTO firstCustomer = customersWithUsers.get(0);
        /*first_letter = firstCustomer.getName();
        firstChar = first_letter.charAt(0);
        System.out.println(first_letter);
        System.out.println(firstChar);*/
        return new ResponseEntity<>(firstCustomer, HttpStatus.OK);
    } else {
        // Wenn die Liste leer ist, könnte dies je nach Anforderungen auch einen anderen HTTP-Statuscode sein
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

    @DeleteMapping("/admin/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }

    @PutMapping("/admin/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
        return customerService.updateCustomer(id, updatedCustomer);
    }

    @GetMapping("/admin/search")
    public List<CustomerDTO> searchCustomersByName(@RequestParam String regex) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        return customerService.searchCustomersByName(pattern);
    }


    @GetMapping("/admin/list_customers_first_letter")
    public List<CustomerDTO> getCustomersWithFirstLetter(@RequestParam String regex) {
        List<CustomerDTO> customersWithUsers = customerService.getAllCustomersWithUsers();

        if (customersWithUsers.isEmpty()) {
            return Collections.emptyList();  // Wenn die Liste leer ist, eine leere Liste zurückgeben
        }

        char firstChar = regex.charAt(0);
        List<CustomerDTO> filteredList = new ArrayList<>();

        for (CustomerDTO customer : customersWithUsers) {
            if (customer.getName().charAt(0) == firstChar) {
                filteredList.add(customer);
            }
        }

        return filteredList;
    }


    @GetMapping("list_customers_first_letter_2")
    public List<CustomerDTO> getCustomersWithFirstLetter() {
        List<CustomerDTO> customersWithUsers = customerService.getAllCustomersWithUsers();

        if (customersWithUsers.isEmpty()) {
            return Collections.emptyList();  // Wenn die Liste leer ist, eine leere Liste zurückgeben
        }

        char firstChar = customersWithUsers.get(0).getName().charAt(0);
        List<CustomerDTO> filteredList = new ArrayList<>();

        for (CustomerDTO customer : customersWithUsers) {
            if (customer.getName().charAt(0) == firstChar) {
                filteredList.add(customer);
            }
        }

        return filteredList;
    }



}
