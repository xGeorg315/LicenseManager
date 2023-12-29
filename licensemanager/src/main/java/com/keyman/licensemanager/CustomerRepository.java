package com.keyman.licensemanager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<User, Long> {

    // You can add custom query methods here if needed
    void save(Customer customer);
    
}