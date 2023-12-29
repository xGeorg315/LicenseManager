package com.keyman.licensemanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContractService {

    @Autowired
    private ContractRepository contractRepository;

    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    public Optional<Contract> getContractById(Long id) {
        return contractRepository.findById(id);
    }

    public Contract createContract(Contract contract) {
        return contractRepository.save(contract);
    }

    public Contract updateContract(Long id, Contract updatedContract) {
        if (contractRepository.existsById(id)) {
            updatedContract.setId(id);
            return contractRepository.save(updatedContract);
        } else {
            return null; // Handle not found case
        }
    }

    public void deleteContract(Long id) {
        contractRepository.deleteById(id);
    }
}

