package com.keyman.licensemanager.controller;

import com.keyman.licensemanager.DTOs.InstanceDTO;
import com.keyman.licensemanager.entities.Contract;
import com.keyman.licensemanager.entities.Instance;
import com.keyman.licensemanager.services.ContractService;
import com.keyman.licensemanager.services.InstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/instances")
public class InstanceController {

    @Autowired
    private InstanceService instanceService;
    @Autowired
    private ContractService contractService;

    @GetMapping("/admin/list_all_instances")
    public ResponseEntity<List<Instance>> getAllInstances() {
        List<Instance> instances = instanceService.getAllInstances();
        return new ResponseEntity<>(instances, HttpStatus.OK);
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<Instance> getInstanceById(@PathVariable Long id) {
        Optional<Instance> instance = instanceService.getInstanceById(id);
        return instance.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/admin/create_instance")
    public ResponseEntity<Instance> createInstance(@RequestBody InstanceDTO instanceDTO) {
        Instance createdInstance = instanceService.createInstance(convertDTOToEntity(instanceDTO));
        return new ResponseEntity<>(createdInstance, HttpStatus.CREATED);
    }

    @PutMapping("/admin/edit/{id}")
    public ResponseEntity<Instance> updateInstance(@PathVariable Long id, @RequestBody InstanceDTO instanceDTO) {
        Instance updatedInstance = instanceService.updateInstance(id, convertDTOToEntity(instanceDTO));
        if (updatedInstance != null) {
            return new ResponseEntity<>(updatedInstance, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<Void> deleteInstance(@PathVariable Long id) {
        instanceService.deleteInstance(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    

    private Instance convertDTOToEntity(InstanceDTO instanceDTO) {
        // Überprüfen Sie, ob id null ist, bevor Sie longValue() aufrufen
        Instance instance = new Instance();
        instance.setStatus(instanceDTO.getStatus());
        instance.setIpAdress(instanceDTO.getIpAdress());
        instance.setName(instanceDTO.getName());
        instance.setType(instanceDTO.getType());
        instance.setContractId(instanceDTO.getId());
        return instance;
    }
    
}
