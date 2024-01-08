package com.keyman.licensemanager.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.keyman.licensemanager.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // You can add custom query methods here if needed
    boolean existsById(Long id);
    
}