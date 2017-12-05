package com.bridgelabz.model;

import java.util.HashSet;
import java.util.Set;


public class ServiceProvider {

	private int SP_ID;

	private String name;

	private Set<Resident> residents = new HashSet<>();

	public int getSP_ID() {
		return SP_ID;
	}

	public void setSP_ID(int sP_ID) {
		SP_ID = sP_ID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Resident> getResidents() {
		return residents;
	}

	public void setResidents(Set<Resident> residents) {
		this.residents = residents;
	}

}
