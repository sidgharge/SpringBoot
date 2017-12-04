package com.bridgelabz.person;

import javax.persistence.*;

@Entity
@Table
public class Person {

	@Id
	int id;
	
	@Column
	String firstName;
	
	@Column
	String lastName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	
}
