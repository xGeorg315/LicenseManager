package com.keyman.licensemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.keyman.licensemanager.services.ContractService;
import com.keyman.licensemanager.services.UserService;

import com.keyman.licensemanager.DTOs.ContractDTO;
import com.keyman.licensemanager.entities.Contract;
import com.keyman.licensemanager.entities.Customer;
import com.keyman.licensemanager.entities.UserEntity;
import com.keyman.licensemanager.repositorys.ContractRepository;
import com.keyman.licensemanager.repositorys.CustomerRepository;
import com.keyman.licensemanager.repositorys.UserRepository;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/contracts")
public class ContractController {

    @Autowired
    private ContractService contractService;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<Contract> getAllContracts() {
        return contractService.getAllContracts();
    }

    @GetMapping("/admin/{id}")
    public Contract getContractById(@PathVariable Long id) {
        return contractService.getContractById(id).orElse(null);
    }

    @Transactional
    @PutMapping("/edit")
    public ResponseEntity<String> UserEdits(@RequestBody ContractDTO contractDTO)
    {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity loggedUser;
        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            loggedUser = userService.getUserByLoginName(username);
        } else {
            String username = principal.toString();
            loggedUser = userService.getUserByLoginName(username);
        }
        Contract contract = contractService.getContractById(loggedUser.getContract().getId()).orElse(null);

        if(contractDTO.getIp1() != null){
            contract.setIpAddress1(contractDTO.getIp1());
        }
        if(contractDTO.getIp1() != null && contractDTO.getIp2() != null){
            contract.setIpAddress2(contractDTO.getIp2());
        }
        if(contractDTO.getIp1() != null && contractDTO.getIp2() != null && contractDTO.getIp3() != null){
            contract.setIpAddress3(contractDTO.getIp3());
        }
        if(contractDTO.getVersion() != null){
            contract.setVersion(contractDTO.getVersion());
        }
        contractService.updateContract(contract.getId(), contract);
        return new ResponseEntity<>("edit success", HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/admin/edit")
    public ResponseEntity<String> AdminEdits(@RequestBody ContractDTO contractDTO) throws ParseException{

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity loggedUser;
        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            loggedUser = userService.getUserByLoginName(username);
        } else {
            String username = principal.toString();
            loggedUser = userService.getUserByLoginName(username);
        }
        Contract contract = contractService.getContractById(loggedUser.getContract().getId()).orElse(null);

        Contract updateContract = mapDTOtoContract(contractDTO, contract);

        contractService.updateContract(updateContract.getId(), contract);

        return new ResponseEntity<>("admin edit success", HttpStatus.OK);
    }

    @Transactional
    @GetMapping("/getOwn")
    public Contract getOwnContract() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity loggedUser;
        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            loggedUser = userService.getUserByLoginName(username);
        } else {
            String username = principal.toString();
            loggedUser = userService.getUserByLoginName(username);
        }
        return contractService.getContractById(loggedUser.getContract().getId()).orElse(null);
    }

    @PostMapping("/admin/add")
    public ResponseEntity<String> createContract(@RequestBody ContractDTO contractDTO) {
        contractDTO.checkIfNull();

        Contract contract = new Contract();

        if(customerRepository.existsById(contractDTO.getCustomer_id())){
            Customer customer = customerRepository.findById(contractDTO.getCustomer_id()).get();
            contract.setCustomer(customer);     
        }
        else
        {
            //return new ResponseEntity<>("Customer not found", HttpStatus.BAD_REQUEST);
        }

        try {
            contract = mapDTOtoContract(contractDTO, contract);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        contractService.createContract(contract);

        return new ResponseEntity<>("Contract created", HttpStatus.OK);
    }

    @PutMapping("/admin/{id}")
    public Contract updateContract(@PathVariable Long id, @RequestBody Contract updatedContract) {
        return contractService.updateContract(id, updatedContract);
    }

    @DeleteMapping("/admin/{id}")
    public void deleteContract(@PathVariable Long id) {
        contractService.deleteContract(id);
    }

    public Contract mapDTOtoContract(ContractDTO contractDTO, Contract contract) throws ParseException
    {
        if(contractDTO.getStartDate() != null)
            contract.setStartDate(new SimpleDateFormat("dd/MM/yyyy").parse(contractDTO.getEndDate()));
        if(contractDTO.getEndDate() != null) 
            contract.setEndDate(new SimpleDateFormat("dd/MM/yyyy").parse(contractDTO.getEndDate()));
        if(contractDTO.getIp1() != null){ 
            contract.setIpAddress1(contractDTO.getIp1());
            if(contractDTO.getIp2() != null) 
            contract.setIpAddress2(contractDTO.getIp2());
            {
                if(contractDTO.getIp3() != null) 
                contract.setIpAddress3(contractDTO.getIp3());
            }
        }

        if(contractDTO.getLicensekey() != null) 
            contract.setLicenseKey(contractDTO.getLicensekey());
        if(contractDTO.getField1() != null) 
            contract.setField1(contractDTO.getField1());
        if(contractDTO.getField2() != null) 
            contract.setField2(contractDTO.getField2());
        if(contractDTO.getField3() != null) 
            contract.setField3(contractDTO.getField3());
        if(contractDTO.getVersion() != null) 
            contract.setVersion(contractDTO.getVersion());

        UserEntity user;
        if(contractDTO.getUser1_id() != null && userRepository.existsById(contractDTO.getUser1_id())){
            user = userRepository.findById(contractDTO.getUser1_id()).get();
            user.setContract(contract);
            userService.updateUser(user.getId(), user);
            
        }
        if(contractDTO.getUser2_id() != null && userRepository.existsById(contractDTO.getUser2_id())){
            user = userRepository.findById(contractDTO.getUser2_id()).get();
            user.setContract(contract);  
            userService.updateUser(user.getId(), user);
            
        }

        return contract;
    }


}
