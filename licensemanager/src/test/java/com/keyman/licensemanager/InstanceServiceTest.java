package com.keyman.licensemanager;

import com.keyman.licensemanager.entities.Instance;
import com.keyman.licensemanager.repositorys.InstanceRepository;
import com.keyman.licensemanager.services.InstanceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class InstanceServiceTest {

    @Autowired
    private InstanceService instanceService;

    @Autowired
    private InstanceRepository instanceRepository;

    @BeforeEach
    void setUp() {
        // Hier könnten Sie Datenbankinitialisierung oder andere Setup-Aufgaben durchführen
    }

    @Test
    void testCreateUpdateDeleteInstance() {
        // Test Create
        Instance instanceDTO = new Instance();
        instanceDTO.setName("TestInstance");
        instanceDTO.setIpAdress("TestIP");
        instanceDTO.setType("TestType");
        instanceDTO.setStatus(1);

        Instance createdInstance = instanceService.createInstance(instanceDTO);
        assertNotNull(createdInstance);
        assertNotNull(createdInstance.getId());

        // Test Update
        String updatedName = "UpdatedInstance";
        instanceDTO.setName(updatedName);

        Instance updatedInstance = instanceService.updateInstance(createdInstance.getId(), instanceDTO);

        assertNotNull(updatedInstance);
        assertEquals(updatedName, updatedInstance.getName());

        // Test Delete
        instanceService.deleteInstance(createdInstance.getId());
        Optional<Instance> deletedInstance = instanceRepository.findById(createdInstance.getId());
        assertTrue(deletedInstance.isEmpty());
    }

    @Test
    void testGetInstanceById() {
        Instance instance = new Instance();
        instance.setName("TestInstance");
        instance.setIpAdress("TestIP");
        instance.setType("TestType");
        instance.setStatus(1);

        Instance savedInstance = instanceRepository.save(instance);

        Optional<Instance> retrievedInstance = instanceService.getInstanceById(savedInstance.getId());

        assertTrue(retrievedInstance.isPresent());
        assertEquals("TestInstance", retrievedInstance.get().getName());
    }

    @Test
    void testGetAllInstances() {
        List<Instance> instances = instanceService.getAllInstances();
        assertNotNull(instances);
        // Je nach Testdaten und Anforderungen können Sie die Assertions anpassen
    }
}
