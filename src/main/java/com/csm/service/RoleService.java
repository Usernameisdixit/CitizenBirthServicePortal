package com.csm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csm.model.Role;
import com.csm.repository.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
    private RoleRepository roleRepository;
    
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

}
