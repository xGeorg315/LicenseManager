package com.keyman.licensemanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keyman.licensemanager.entities.Instance;
import com.keyman.licensemanager.repositorys.InstanceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InstanceService {

    @Autowired
    private InstanceRepository instanceRepository;

    public List<Instance> getAllInstances() {
        return instanceRepository.findAll();
    }

    public Optional<Instance> getInstanceById(Long id) {
        return instanceRepository.findById(id);
    }

    public Instance createInstance(Instance instance) {
        return instanceRepository.save(instance);
    }

    public Instance updateInstance(Long id, Instance updatedInstance) {
        if (instanceRepository.existsById(id)) {
            updatedInstance.setId(id);
            return instanceRepository.save(updatedInstance);
        } else {
            return null; // Handle not found case
        }
    }

    public void deleteInstance(Long id) {
        instanceRepository.deleteById(id);
    }

    
}

