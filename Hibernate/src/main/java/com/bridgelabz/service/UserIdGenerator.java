package com.bridgelabz.service;

import java.io.Serializable;
import java.sql.Connection;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class UserIdGenerator implements IdentifierGenerator {
	
	int i = 0;

	@Override
	public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
		Connection connection = session.connection();
		System.out.println(connection);
		return ++i;
	}

}
