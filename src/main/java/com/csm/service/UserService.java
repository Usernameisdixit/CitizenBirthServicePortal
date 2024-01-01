package com.csm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csm.model.User;
import com.csm.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
    private UserRepository userRepository;
    
    public void saveUser(User user) {
        userRepository.save(user);
    }
    
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    public User findByPhonenumber(String phonenumber) {
        return userRepository.findByPhonenumber(phonenumber);
    }

}
