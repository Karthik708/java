package com.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {
	
	@Autowired
	private UserDaoService service;
	
	//retrieveAllUsers
	@GetMapping("/users")
	public List<User> retriveAllUsers(){
		return service.findAll();
	}
	
	@GetMapping("users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		User user = service.findOne(id);
		if(user == null) {
			throw new UserNotFoundException("id-"+id);
		}
		
		//HATEOAS
		EntityModel<User> entity = new EntityModel<User>(user);
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retriveAllUsers());
		entity.add(linkTo.withRel("all-users"));
		
		return entity;
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUsers(@Valid @RequestBody User user){
		User savedUser = service.save(user);
		//Created 
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = service.deleteById(id);
		if(user == null) {
			throw new UserNotFoundException("id-"+id);
		}
		
	}
}
