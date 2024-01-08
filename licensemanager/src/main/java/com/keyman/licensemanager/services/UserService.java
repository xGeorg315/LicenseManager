package com.keyman.licensemanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.keyman.licensemanager.DTOs.UserUpdateDTO;
import com.keyman.licensemanager.entities.UserEntity;
import com.keyman.licensemanager.repositorys.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public UserEntity getUserByLoginName(String LoginName) {
        return userRepository.findByLoginName(LoginName);
    }

    public UserEntity createUser(UserEntity user) {
        return userRepository.save(user);
    }

    public UserEntity updateUser(Long id, UserEntity updatedUser) {
        if (userRepository.existsById(id)) {
            updatedUser.setId(id);
            return userRepository.save(updatedUser);
        } else {
            return null; // Handle not found case
        }
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    
    public UserEntity userChangeNonNullValues(UserUpdateDTO updatedUser, UserEntity currentUser, boolean adminRequest)
    {
        if(!(updatedUser.getUsername() == null || updatedUser.getUsername().equals("")))
            currentUser.setLoginName(updatedUser.getUsername());
        if(!(updatedUser.getPassword() == null || updatedUser.getPassword().equals("")) && !adminRequest)
            currentUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        if(!(updatedUser.getFirstName() == null || updatedUser.getFirstName().equals(""))) 
            currentUser.setFirstName(updatedUser.getFirstName());
        if(!(updatedUser.getLastName() == null || updatedUser.getLastName().equals("")))
            currentUser.setLastName(updatedUser.getLastName());
        if(!(updatedUser.getEmail() == null || updatedUser.getEmail().equals("")))
            currentUser.setEmail(updatedUser.getEmail());
        if(!(updatedUser.getPhoneNumber1() == null || updatedUser.getPhoneNumber1().equals("")))
            currentUser.setPhoneNumber1(updatedUser.getPhoneNumber1());
        if(!(updatedUser.getPhoneNumber2() == null || updatedUser.getPhoneNumber2().equals("")))
            currentUser.setPhoneNumber2(updatedUser.getPhoneNumber2());

        return currentUser;
    }
}
