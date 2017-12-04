package com.bridgelabz.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
	
	@Autowired
	PersonDao personDao;

	public void save(Person person) {
		
		personDao.save(person);
	}

}
