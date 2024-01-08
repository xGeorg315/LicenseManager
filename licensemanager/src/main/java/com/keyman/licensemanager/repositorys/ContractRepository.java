package com.keyman.licensemanager.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.keyman.licensemanager.entities.Contract;
import com.keyman.licensemanager.entities.UserEntity;


@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> 
{
    public List<Contract> findAll();
   
}