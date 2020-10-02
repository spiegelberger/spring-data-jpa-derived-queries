package com.spiegelberger.spring.data.jpa.derived.queries.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spiegelberger.spring.data.jpa.derived.queries.entity.User;
import com.spiegelberger.spring.data.jpa.derived.queries.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	private UserService service;

	@Autowired
	public UserController(UserService service) {
		this.service = service;
	}
	
	
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return service.getUsers();
	}
	
	@GetMapping("/usersbyprofession/{profession}")
	public List<User>findByProfession(@PathVariable String profession){
		return service.findByProfession(profession);
	}
	
	@GetMapping("/usersbynotthisname/{name}")
	List<User> findByNameIsNot(@PathVariable String name){
		return service.findByNameIsNot(name);
	}
	
	@GetMapping("/usersbyage/{start}/{end}")
	List<User> findByAgeGreaterThanEqualAndAgeLessThan(@PathVariable int start, @PathVariable int end){
		return service.findByAgeGreaterThanEqualAndAgeLessThan(start, end);
	}
	
	@GetMapping("/count/{age}")
	public String getCountByAge(@PathVariable int age) {
		long count=service.getCounts(age);
		return "Total number of records: " + count;
	}
	
	@DeleteMapping("/{name}")
	public List<User>deleteUserByName(@PathVariable String name){
		return service.deleteUserByName(name);
	}
	
	@GetMapping("/multicondition/{profession}/{age}")
	public List<User>getUserByProfessionAndAge(@PathVariable String profession, @PathVariable int age){
		return service.findByProfessionAndAge(profession, age);		
	}
	

	@GetMapping("/sort/{field}")
	public List<User> getSortedUsers(@PathVariable String field) {
		return service.getUserSort(field);
	}
	
	@GetMapping("/paginated")
	public Page<User>getPaginatedData(){
		return service.getPaginatedUser();
	}
	
}
