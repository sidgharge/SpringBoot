package com.nouhoun.springboot.jwt.integration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nouhoun.springboot.jwt.integration.domain.User;
import com.nouhoun.springboot.jwt.integration.service.GenericService;

@RestController
@RequestMapping("/springjwt")
public class ResourceController {
	@Autowired
	private GenericService userService;

	@RequestMapping(value = "/users/{name}")
	@PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
	public User getUser(@PathVariable("name") String username) {
		System.out.println("Name got: "+username);
		return userService.findByUsername(username);
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN_USER')")
	public List<User> getUsers() {
		return userService.findAllUsers();
	}
}
