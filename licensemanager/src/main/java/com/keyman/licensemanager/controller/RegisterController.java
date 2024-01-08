package com.keyman.licensemanager.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.keyman.licensemanager.DTOs.LoginDTO;
import com.keyman.licensemanager.DTOs.RegisterDTO;
import com.keyman.licensemanager.entities.Roles;
import com.keyman.licensemanager.entities.UserEntity;
import com.keyman.licensemanager.repositorys.CustomerRepository;
import com.keyman.licensemanager.repositorys.RolesRepository;
import com.keyman.licensemanager.repositorys.UserRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class RegisterController {
    
    private AuthenticationManager authenticationManagerBean;
    private UserRepository userRepository;
    private RolesRepository rolesRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerRepository customerRepository;

    public RegisterController(AuthenticationManager authenticationManagerBean, UserRepository userRepository, RolesRepository rolesRepository, PasswordEncoder passwordEncoder)
    {
        this.authenticationManagerBean = authenticationManagerBean;
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/admin/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO) {
        
        if (userRepository.existsByLoginName(registerDTO.getUsername())){
            return new ResponseEntity<>("username is taken", HttpStatus.BAD_REQUEST);
        }
        

        //Customer customer = customerRepository.findById(registerDTO.getCustomer_id()).get();

        UserEntity user = new UserEntity();
        user.setLoginName(registerDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setFirstName(registerDTO.getName());
        user.setLastName(registerDTO.getLastName());
        user.setEmail(registerDTO.getEmail());
        user.setPhoneNumber1(registerDTO.getPhoneNumber1());
        user.setPhoneNumber2(registerDTO.getPhoneNumber2());
        //user.setCustomer(customer);

        Roles roles;
        System.out.println(registerDTO.getRole_user());
        if(registerDTO.getRole_user() == "ADMIN")
        {
            roles = rolesRepository.findByName("ADMIN").get();
            user.setAdmin(true);
        }
        else
        {
            roles = rolesRepository.findByName("USER").get();
            user.setAdmin(false);
        }
   
        user.setRoles(Collections.singletonList(roles));

        userRepository.save(user);

        return new ResponseEntity<>("User register success", HttpStatus.OK);
    }

    @Transactional
    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        System.out.println(loginDTO.getUsername());
        System.out.println(loginDTO.getPassword());
       
        try {
            Authentication authentication = authenticationManagerBean.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // Authentication successful, handle accordingly
        } catch (AuthenticationException e) {
            return new ResponseEntity<>("user signed in success", HttpStatus.BAD_REQUEST);
            // Authentication failed, handle accordingly
        }
        return new ResponseEntity<>("user signed in success", HttpStatus.OK);
    }    
}
