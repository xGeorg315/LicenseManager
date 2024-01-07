package com.keyman.licensemanager;

import com.keyman.licensemanager.entities.Contract;
import com.keyman.licensemanager.entities.Instance;
import com.keyman.licensemanager.repositorys.InstanceRepository;
import com.keyman.licensemanager.services.InstanceService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class InstanceServiceTest {

    @Mock
    private InstanceRepository instanceRepository;

    @InjectMocks
    private InstanceService instanceService;

    @Test
    void InstanceServiceTest() {
        // Mocking data
        List<Instance> mockInstances = new ArrayList<>();
        mockInstances.add(createMockInstance(1L, "Instance1", "192.168.1.1", "Type1", 1, new Contract()));

        Mockito.when(instanceRepository.findAll()).thenReturn(mockInstances);

        // Test
        List<Instance> result = instanceService.getAllInstances();

        // Assertions
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testGetInstanceById() {
        // Mocking data
        Long instanceId = 1L;
        Instance mockInstance = createMockInstance(instanceId, "Instance1", "192.168.1.1", "Type1", 1, new Contract());

        Mockito.when(instanceRepository.findById(instanceId)).thenReturn(Optional.of(mockInstance));

        // Test
        Optional<Instance> result = instanceService.getInstanceById(instanceId);

        // Assertions
        assertTrue(result.isPresent());
        assertEquals(instanceId, result.get().getId());
    }

    @Test
    void testCreateInstance() {
        // Mocking data
        Instance newInstance = createMockInstance(null, "NewInstance", "192.168.1.2", "Type2", 1, new Contract());

        Mockito.when(instanceRepository.save(newInstance)).thenReturn(newInstance);

        // Test
        Instance result = instanceService.createInstance(newInstance);

        // Assertions
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(newInstance.getName(), result.getName());
    }

    @Test
    void testUpdateInstance() {
        // Mocking data
        Long instanceId = 1L;
        Instance existingInstance = createMockInstance(instanceId, "Instance1", "192.168.1.1", "Type1", 1, new Contract());
        Instance updatedInstance = createMockInstance(instanceId, "UpdatedInstance", "192.168.1.3", "Type3", 2, new Contract());

        Mockito.when(instanceRepository.existsById(instanceId)).thenReturn(true);
        Mockito.when(instanceRepository.save(updatedInstance)).thenReturn(updatedInstance);

        // Test
        Instance result = instanceService.updateInstance(instanceId, updatedInstance);

        // Assertions
        assertNotNull(result);
        assertEquals(updatedInstance.getName(), result.getName());
        assertEquals(updatedInstance.getIpAdress(), result.getIpAdress());
        assertEquals(updatedInstance.geType(), result.geType());
        assertEquals(updatedInstance.getStatus(), result.getStatus());
    }

    @Test
    void testUpdateInstanceNotFound() {
        // Mocking data
        Long nonExistingInstanceId = 99L;
        Instance updatedInstance = createMockInstance(nonExistingInstanceId, "UpdatedInstance", "192.168.1.3", "Type3", 2, new Contract());

        Mockito.when(instanceRepository.existsById(nonExistingInstanceId)).thenReturn(false);

        // Test
        Instance result = instanceService.updateInstance(nonExistingInstanceId, updatedInstance);

        // Assertions
        assertEquals(null, result);
    }

    @Test
    void testDeleteInstance() {
        // Mocking data
        Long instanceId = 1L;

        // Test
        instanceService.deleteInstance(instanceId);

        // Verify that deleteById was called once with the correct parameter
        Mockito.verify(instanceRepository, Mockito.times(1)).deleteById(instanceId);
    }

    // Helper method to create a mock Instance
    private Instance createMockInstance(Long id, String name, String ipAddress, String type, int status, Contract contract) {
        Instance instance = new Instance();
        instance.setId(id);
        instance.setName(name);
        instance.setIpAdress(ipAddress);
        instance.setType(type);
        instance.setStatus(status);
        instance.setContract(contract);
        return instance;
    }
}
