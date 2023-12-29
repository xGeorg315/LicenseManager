package com.keyman.licensemanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
                .filter(customer -> pattern.matcher(customer.getName()).find())
                .map(this::mapToCustomerDTO)
                .collect(Collectors.toList());
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
        customerDTO.setUsers(customer.getUsers());
        return customerDTO;
    }
}

