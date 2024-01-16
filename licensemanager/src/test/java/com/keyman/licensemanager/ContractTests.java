package com.keyman.licensemanager;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.keyman.licensemanager.entities.Contract;
import com.keyman.licensemanager.repositorys.ContractRepository;
import com.keyman.licensemanager.services.ContractService;

@SpringBootTest
@Transactional
class ContractTests{
    
    @Autowired
    private ContractService contractService;
    @Autowired
    private ContractRepository contractRepository;

    @Test
    void createContract(){

        Contract contract = createTestContract();
        Contract createdContract = contractService.createContract(contract);

        //Check if contract exists
        assertNotNull(contractRepository.findById(createdContract.getId()));
    }

    @Test
    void deleteContract(){

        Contract contract = createTestContract();
        contractService.createContract(contract);
        Contract contractToDelete = contractRepository.findById(contract.getId()).get();

        contractRepository.deleteById(contractToDelete.getId());

        assertFalse(contractRepository.findById(contractToDelete.getId()).isPresent());

    }

    @Test
    void getAllContracts(){
        contractService.createContract(createTestContract());
        contractService.createContract(createTestContract());

        assertTrue(contractService.getAllContracts().size() > 1);
    }

    private Contract createTestContract(){
        Contract contract = new Contract();
        contract.setIpAddress1("192.168.0.1");
        contract.setIpAddress2("192.168.0.2");  
        contract.setIpAddress3("192.168.0.3"); 
        contract.setLicenseKey("testkey");
        contract.setVersion("2.1");
        return contract;
    }
}
