package com.bridgelabz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.model.User;
import com.bridgelabz.service.GenericService;

@RestController
@RequestMapping("/springjwt")
public class ResourceController {
	@Autowired
	private GenericService userService;

	@RequestMapping(value = "/users/{name}")
	//@PreAuthorize("hasAnyAuthority('STANDARD_USER')")
	public User getUser(@PathVariable("name") String username) {
		System.out.println("Name got: "+SecurityContextHolder.getContext().getAuthentication());
		return userService.findByUsername(username);
	}

	@RequestMapping(value = "/admin/users", method = RequestMethod.GET)
	//@PreAuthorize("hasAuthority('ADMIN_USER')")
	public List<User> getUsers() {
		System.out.println("Name got as: "+SecurityContextHolder.getContext().getAuthentication());
		return userService.findAllUsers();
	}
}
