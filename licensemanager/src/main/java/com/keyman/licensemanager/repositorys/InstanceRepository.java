package com.keyman.licensemanager.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.keyman.licensemanager.entities.Instance;

@Repository
public interface InstanceRepository extends JpaRepository<Instance, Long> 
{
    public List<Instance> findAll();
}


