package com.spiegelberger.spring.data.jpa.derived.queries.service;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.spiegelberger.spring.data.jpa.derived.queries.dao.UserRepository;
import com.spiegelberger.spring.data.jpa.derived.queries.entity.User;

@Service
@Transactional
public class UserService {
	
	private UserRepository repository;

	@Autowired
	public UserService(UserRepository repository) {
		this.repository = repository;
	}
	
	@PostConstruct
	public void initDB() {
		List<User> users = new ArrayList<>();
		users.add(new User("Adam", "IT", 23));
		users.add(new User("Julie", "IT", 24));
		users.add(new User("Susan", "MANAGEMENT", 26));
		users.add(new User("Sacha", "IT", 23));
		users.add(new User("Diane", "ADMINISTRATION", 20));
		users.add(new User("Eva", "ADMINISTRATION", 56));
		users.add(new User("Robert", "ADMINISTRATION", 43));
		
		repository.saveAll(users);
	}
	
	public List<User> getUsers(){
		return repository.findAll();
	}
	
	public List<User>findByProfession(String profession){
		return repository.findByProfessionIgnoreCase(profession);
	}
	
	public List<User> findByNameIsNot(String name) {
		return repository.findByNameIsNotIgnoreCase(name);
	}
	
	public List<User> findByAgeGreaterThanEqualAndAgeLessThan(int ageStart, int ageEnd){
		return repository.findByAgeGreaterThanEqualAndAgeLessThan(ageStart, ageEnd);
	}
	
	public long getCounts(int age) {
		return repository.countByAge(age);
	}
	
	public List<User>deleteUserByName(String name){
		return repository.deleteByName(name);
	}
	
	public List<User>findByProfessionAndAge(String profession, int age){
		return repository.findByProfessionIgnoreCaseAndAge(profession, age);
	}
	
	
	
		public List<User> getUserSort(String field) {
			return repository.findAll(Sort.by(field));
		}
	
	// pagination
		public Page<User> getPaginatedUser() {
			Pageable pageable = PageRequest.of(0, 3);
			Page<User> page = repository.findAll(pageable);
			return page;
		}
	
	

}
