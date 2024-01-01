package com.csm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.csm.model.BirthDetails;

public interface BirthRepository extends JpaRepository<BirthDetails, Long> {

	List<BirthDetails> findAll();
	
}
