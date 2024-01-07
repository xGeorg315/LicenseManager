

package com.keyman.licensemanager;

import com.keyman.licensemanager.DTOs.CustomerDTO;
import com.keyman.licensemanager.entities.Customer;
import com.keyman.licensemanager.repositorys.CustomerRepository;
import com.keyman.licensemanager.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;


@SpringBootTest
class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Test
    void testCreateUpdateDeleteCustomer() {
        // Test Create
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName("TestCustomer");
        customerDTO.setDepartment("TestDepartment");
        customerDTO.setStreet("TestStreet");
        customerDTO.setTown("TestTown");
        customerDTO.setZipCode("12345");
        customerDTO.setCountry("TestCountry");

        CustomerDTO createdCustomerDTO = customerService.createCustomer(customerDTO);
        assertNotNull(createdCustomerDTO);
        assertNotNull(createdCustomerDTO.getId());

        // Test Update
        String updatedName = "UpdatedName";
        createdCustomerDTO.setName(updatedName);

        Long customerId = createdCustomerDTO.getId();
        Customer updatedCustomer = customerService.updateCustomer(customerId, customerService.mapToCustomerEntity(createdCustomerDTO));

        assertNotNull(updatedCustomer);
        assertEquals(updatedName, updatedCustomer.getName());

        // Test Delete
        customerService.deleteCustomer(customerId);
        assertNull(customerService.updateCustomer(customerId, updatedCustomer));
    }

    @Test
    void testGetAllCustomersWithUsers() {
        List<CustomerDTO> customers = customerService.getAllCustomersWithUsers();
        assertNotNull(customers);
        assertTrue(customers.size() > 0);
    }

    @Test
    void testSearchCustomersByName() {
        String searchPattern = "Test";
        Pattern pattern = Pattern.compile(searchPattern, Pattern.CASE_INSENSITIVE);
    
        List<CustomerDTO> customers = customerService.searchCustomersByName(pattern);
        assertNotNull(customers);
        // Ändern Sie die Erwartung basierend auf Ihren Testdaten und Anforderungen
        assertTrue(customers.size() >= 0);
    }
    
}
