package com.keyman.licensemanager.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.keyman.licensemanager.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // You can add custom query methods here if needed
    UserEntity findByLoginName(String loginName);
    Boolean existsByLoginName(String loginName);
}