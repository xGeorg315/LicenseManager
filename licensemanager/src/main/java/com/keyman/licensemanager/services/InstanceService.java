package com.keyman.licensemanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keyman.licensemanager.entities.Instance;
import com.keyman.licensemanager.repositorys.InstanceRepository;
import com.keyman.licensemanager.DTOs.InstanceDTO;

import java.util.List;
import java.util.Optional;

@Service
public class InstanceService {

    @Autowired
    private InstanceRepository instanceRepository;

    // Andere Methoden bleiben unverändert

    public Instance createInstance(InstanceDTO instanceDTO) {
        Instance instance = convertDTOtoInstance(instanceDTO);
        return instanceRepository.save(instance);
    }

    public Instance updateInstance(Long id, InstanceDTO updatedInstanceDTO) {
        Optional<Instance> existingInstanceOptional = instanceRepository.findById(id);

        if (existingInstanceOptional.isPresent()) {
            Instance existingInstance = existingInstanceOptional.get();
            updateInstanceFromDTO(existingInstance, updatedInstanceDTO);
            return instanceRepository.save(existingInstance);
        } else {
            return null; // Handle not found case
        }
    }

    // Andere Methoden bleiben unverändert

    private Instance convertDTOtoInstance(InstanceDTO instanceDTO) {
        Instance instance = new Instance();
        instance.setName(instanceDTO.getName());
        instance.setIpAdress(instanceDTO.getIpAdress());
        instance.setType(instanceDTO.getType());
        instance.setStatus(instanceDTO.getStatus());
        // Setzen Sie andere Felder entsprechend

        return instance;
    }

    private void updateInstanceFromDTO(Instance instance, InstanceDTO instanceDTO) {
        instance.setName(instanceDTO.getName());
        instance.setIpAdress(instanceDTO.getIpAdress());
        instance.setType(instanceDTO.getType());
        instance.setStatus(instanceDTO.getStatus());
        // Aktualisieren Sie andere Felder entsprechend
    }
}
