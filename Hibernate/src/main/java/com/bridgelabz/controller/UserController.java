package com.bridgelabz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.model.User;
import com.bridgelabz.service.UserService;


@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public List<User> getAll(){
		return userService.getAll();
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public void create(@RequestBody User user) {
		userService.create(user);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public void update(@RequestBody User user) {
		userService.update(user);
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") int id) {
		userService.delete(id);
	}
}
