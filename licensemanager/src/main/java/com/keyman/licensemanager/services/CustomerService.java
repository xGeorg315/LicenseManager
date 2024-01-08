package com.keyman.licensemanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keyman.licensemanager.DTOs.CustomerDTO;
import com.keyman.licensemanager.entities.Customer;
import com.keyman.licensemanager.repositorys.CustomerRepository;
import java.util.Collections;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<CustomerDTO> getAllCustomersWithUsers() {
        return customerRepository.findAll().stream()
                .map(this::mapToCustomerDTO)
                .collect(Collectors.toList());
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        if (customerRepository.existsById(id)) {
            updatedCustomer.setId(id);
            return customerRepository.save(updatedCustomer);
        } else {
            return null; // Handle not found case
        }
    }

    public List<CustomerDTO> searchCustomersByName(Pattern pattern) {
        return customerRepository.findAll().stream()
                .filter(customer -> {
                    if (pattern == null || customer.getName() == null) {
                        return false; // oder eine andere Logik, wenn null nicht erlaubt ist
                    }
                    return pattern.matcher(customer.getName()).find();
                })
                .map(this::mapToCustomerDTO)
                .collect(Collectors.toList());
    }
    

 
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = mapToCustomerEntity(customerDTO);
        customer = customerRepository.save(customer);
    
        if (customer != null) {
            return mapToCustomerDTO(customer);
        } else {
            // Hier können Sie eine angemessene Behandlung für den Fall hinzufügen, dass der Kunde null ist.
            return null;
        }
    }
    

    public Customer mapToCustomerEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setDepartment(customerDTO.getDepartment());
        customer.setStreet(customerDTO.getStreet());
        customer.setTown(customerDTO.getTown());
        customer.setZipCode(customerDTO.getZipCode());
        customer.setCountry(customerDTO.getCountry());
        // Setzen Sie hier ggf. andere Attribute und Beziehungen
        return customer;
    }
   
    private CustomerDTO mapToCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setDepartment(customer.getDepartment());
        customerDTO.setStreet(customer.getStreet());
        customerDTO.setTown(customer.getTown());
        customerDTO.setZipCode(customer.getZipCode());
        customerDTO.setCountry(customer.getCountry());
        //customerDTO.setUsers(customer.getUsers());
        return customerDTO;
    }

    public List<Customer> getAllCustomers()
    {
        return customerRepository.findAll();
    }
    
}

