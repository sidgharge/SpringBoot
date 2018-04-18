package com.bridgelabz.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelabz.model.User;

@Repository
public class UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<User> getAll() {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(User.class);
		List<User> users = criteria.list();
		session.close();
		return users;
	}

	public void create(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.doWork(new Work() {

			@Override
			public void execute(Connection connection) throws SQLException {

				System.out.println(connection);
			}
		});

		session.save(user);
	}

	public void update(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.update(user);
	}

	public void delete(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(user);
	}

	public User getUserById(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(User.class, id);
	}

}
