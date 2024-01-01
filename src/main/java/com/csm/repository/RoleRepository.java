package com.csm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.csm.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

    Role findByName(String name);

}
