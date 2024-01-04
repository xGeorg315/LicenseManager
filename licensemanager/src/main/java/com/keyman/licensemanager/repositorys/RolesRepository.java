package com.keyman.licensemanager.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.keyman.licensemanager.entities.Roles;

import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository <Roles, Long> {

    Optional<Roles> findByName (String name);
}