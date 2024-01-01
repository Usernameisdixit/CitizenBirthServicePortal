package com.csm.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.csm.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    
    User findByEmail(String email);
    
    User findByPhonenumber(String phonenumber);
    
    

    
    
    
    

}
