package com.spiegelberger.spring.data.jpa.derived.queries.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.spiegelberger.spring.data.jpa.derived.queries.entity.User;


public interface UserRepository extends JpaRepository<User, Integer>{

	 List<User>findByProfessionIgnoreCase(String profession);
	 
	 List<User> findByNameIsNotIgnoreCase(String name);
	 
	 List<User> findByAgeGreaterThanEqualAndAgeLessThan(int ageStart, int ageEnd);
	
	 long countByAge(int age);
	
	 List<User>deleteByName(String name);
	
	 List<User>findByProfessionIgnoreCaseAndAge(String profession, int age);
	
	 Page<User> findAll(Pageable pageable);
	 

}
