package com.keyman.licensemanager;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.keyman.licensemanager.entities.Instance;
import com.keyman.licensemanager.repositorys.InstanceRepository;
import com.keyman.licensemanager.services.InstanceService;

@SpringBootTest
class InstanceServiceTest {

    @MockBean
    private InstanceRepository instanceRepository;

    @Test
    void getAllInstances() {
        // Mocking data
        Instance instance1 = new Instance();
        Instance instance2 = new Instance();
        List<Instance> mockInstances = Arrays.asList(instance1, instance2);

        // Mocking repository method
        when(instanceRepository.findAll()).thenReturn(mockInstances);

        // Initializing the service
        InstanceService instanceService = new InstanceService();
        instanceService.setInstanceRepository(instanceRepository);

        // Testing the service method
        List<Instance> result = instanceService.getAllInstances();

        // Asserting the result
        assertEquals(2, result.size());
    }

    @Test
    void getInstanceById() {
        // Mocking data
        Long instanceId = 1L;
        Instance mockInstance = new Instance();
        mockInstance.setId(instanceId);

        // Mocking repository method
        when(instanceRepository.findById(instanceId)).thenReturn(Optional.of(mockInstance));

        // Initializing the service
        InstanceService instanceService = new InstanceService();
        instanceService.setInstanceRepository(instanceRepository);

        // Testing the service method
        Optional<Instance> result = instanceService.getInstanceById(instanceId);

        // Asserting the result
        assertTrue(result.isPresent());
        assertEquals(instanceId, result.get().getId());
    }

    // Add more test cases for createInstance, updateInstance, and deleteInstance methods
}
