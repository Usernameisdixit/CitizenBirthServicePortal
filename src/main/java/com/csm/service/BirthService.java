package com.csm.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csm.repository.BirthRepository;
import com.csm.model.BirthDetails;

@Service
public class BirthService {
	
	@Autowired
	BirthRepository birthRepository;
	
	public List<BirthDetails> findAll()
	{
		return birthRepository.findAll();
	}

}
