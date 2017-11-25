package com.bridgelabz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bridgelabz.dao.UserDao;
import com.bridgelabz.model.User;

@Service
public class UserService {
	
	@Autowired
	UserDao userDao;

	public List<User> getAll(){
		return userDao.getAll();
	}
	
	@Transactional
	public void create(User user) {
		userDao.create(user);
	}
	
	@Transactional
	public void update(User user) {
		userDao.update(user);
	}

	@Transactional
	public void delete(int id) {
		User user = userDao.getUserById(id);
		userDao.delete(user);
	}
}
