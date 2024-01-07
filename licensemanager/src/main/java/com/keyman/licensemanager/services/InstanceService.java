package com.keyman.licensemanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keyman.licensemanager.DTOs.InstanceDTO;
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

<<<<<<< Updated upstream
    public Instance createInstance(InstanceDTO instanceDTO) {
        Instance instance = convertDTOtoInstance(instanceDTO);
=======
    public Optional<Instance> getInstanceById(Long id) {
        return instanceRepository.findById(id);
    }

    public Instance createInstance(InstanceDTO instanceDTO) {
        Instance instance = convertDTOToEntity(instanceDTO);
>>>>>>> Stashed changes
        return instanceRepository.save(instance);
    }

    public Instance updateInstance(Long id, InstanceDTO updatedInstanceDTO) {
<<<<<<< Updated upstream
        Optional<Instance> existingInstanceOptional = instanceRepository.findById(id);

        if (existingInstanceOptional.isPresent()) {
            Instance existingInstance = existingInstanceOptional.get();
            updateInstanceFromDTO(existingInstance, updatedInstanceDTO);
            return instanceRepository.save(existingInstance);
        } else {
            return null; // Handle not found case
=======
        if (instanceRepository.existsById(id)) {
            Instance existingInstance = instanceRepository.findById(id).orElse(null);
            if (existingInstance != null) {
                updateInstanceFromDTO(existingInstance, updatedInstanceDTO);
                return instanceRepository.save(existingInstance);
            }
>>>>>>> Stashed changes
        }
        return null; // Handle not found case
    }

<<<<<<< Updated upstream
    // Andere Methoden bleiben unverändert

    private Instance convertDTOtoInstance(InstanceDTO instanceDTO) {
        Instance instance = new Instance();
        instance.setName(instanceDTO.getName());
        instance.setIpAdress(instanceDTO.getIpAdress());
        instance.setType(instanceDTO.getType());
        instance.setStatus(instanceDTO.getStatus());
        // Setzen Sie andere Felder entsprechend

        return instance;
=======
    private Instance convertDTOToEntity(InstanceDTO instanceDTO) {
        Instance instance = new Instance();
        instance.setStatus(instanceDTO.getStatus());
        instance.setIpAdress(instanceDTO.getIpAdress());
        instance.setName(instanceDTO.getName());
        instance.setType(instanceDTO.getType());
        return instance;
    }


    public void deleteInstance(Long id) {
        instanceRepository.deleteById(id);
>>>>>>> Stashed changes
    }

    private void updateInstanceFromDTO(Instance instance, InstanceDTO instanceDTO) {
        instance.setName(instanceDTO.getName());
        instance.setIpAdress(instanceDTO.getIpAdress());
        instance.setType(instanceDTO.getType());
        instance.setStatus(instanceDTO.getStatus());
        // Aktualisieren Sie andere Felder entsprechend
    }
<<<<<<< Updated upstream
=======

    private void updateInstanceFromDTO(Instance instance, InstanceDTO instanceDTO) {
        instance.setStatus(instanceDTO.getStatus());
        instance.setIpAdress(instanceDTO.getIpAdress());
        instance.setName(instanceDTO.getName());
        instance.setType(instanceDTO.getType());
    }

>>>>>>> Stashed changes
}
