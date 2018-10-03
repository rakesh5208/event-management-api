package com.learningwithrakesh.EventManagement.repository;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.learningwithrakesh.EventManagement.entity.Event;
import com.learningwithrakesh.EventManagement.entity.User;

@Repository()
public class UserRepositoryImpl implements UserRepository {
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<User> findAll() {
		Session session = sessionFactory.openSession();

		session.beginTransaction();
		List<User> list = session.createQuery("from User").list();
		session.getTransaction().commit();
		session.close();
		return list;
	}

	@Override
	public User save(User user) {
		Session openSession = sessionFactory.openSession();
		openSession.beginTransaction();
		Long userId = user.getId();
		if (userId != null) {
			openSession.saveOrUpdate(user);
		} else {
			userId = (Long) openSession.save(user);
		}
		openSession.getTransaction().commit();
		User savedUser = (User) openSession.load(User.class, userId);
		openSession.close();
		return savedUser;
	}

	@Override
	public User getOne(long id) {
		Session openSession = sessionFactory.openSession();
		openSession.beginTransaction();
		User user = (User) openSession.get(User.class, id);
		openSession.getTransaction().commit();
		openSession.close();
		return user;

	}

	@Override
	public void deleteById(long id) {

	}

	@Override
	public User findByUsername(String username) {
		Session openSession = sessionFactory.openSession();
		openSession.beginTransaction();
		List<User> users = openSession.createQuery("from User where username = :username")
				.setParameter("username", username).list();
		openSession.close();
		if(users.size() > 0){
			return users.get(0);
		}
		return null;
	}

}
