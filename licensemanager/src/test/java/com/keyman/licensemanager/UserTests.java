package com.keyman.licensemanager;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.keyman.licensemanager.entities.Roles;
import com.keyman.licensemanager.entities.UserEntity;
import com.keyman.licensemanager.repositorys.RolesRepository;
import com.keyman.licensemanager.repositorys.UserRepository;
import com.keyman.licensemanager.services.UserService;

@SpringBootTest
@Transactional
class UserTests{
    
    @Autowired
    private UserService userService;
    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void CreateUser(){

        UserEntity user = createTestUser();
        UserEntity createdUser = userService.createUser(user);

        //Check if user exists
        assertNotNull(userRepository.findById(createdUser.getId()));
        
        //Check if Password is encoded
        Boolean passwordEncoded = false;
        UserEntity actualUser = userRepository.findById(createdUser.getId()).get();
        if(actualUser.getPassword() != "1234"){
            passwordEncoded = true;
        }
        assertTrue(passwordEncoded);
    }

    @Test
    void DeleteUser(){
        userService.createUser(createTestUser());
        UserEntity userToDelete = userRepository.findByLoginName("loginname");

        userRepository.deleteById(userToDelete.getId());

        assertFalse(userRepository.findById(userToDelete.getId()).isPresent());

    }

    @Test
    void GetAllUsers(){
        userService.createUser(createTestUser());
        userService.createUser(createTestUser());

        assertTrue(userService.getAllUsers().size() > 1);
    }

    private UserEntity createTestUser(){
        UserEntity user = new UserEntity();
        Roles roles;
        user.setAdmin(true); 
        user.setEmail("test@mail");
        user.setFirstName("firstname");
        user.setLastName("lastname");
        user.setLoginName("loginname");
        roles = rolesRepository.findByName("ADMIN").get();
        user.setRoles(Collections.singletonList(roles));
        user.setPassword(passwordEncoder.encode("1234"));
        return user;
    }
}
