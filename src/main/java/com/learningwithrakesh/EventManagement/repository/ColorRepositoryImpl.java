package com.learningwithrakesh.EventManagement.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.learningwithrakesh.EventManagement.entity.Color;

/**
 *
 */
@Repository
public class ColorRepositoryImpl implements ColorRepository {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<Color> findAll() {

		return null;
	}


	@Override
	public Color save(Color color) {
		Session currentSession = sessionFactory.openSession();
		currentSession.beginTransaction();
		Long id = (Long) currentSession.save(color);
		currentSession.getTransaction().commit();
		Color createdColor = (Color) currentSession.load(Color.class, id);
		currentSession.close();
		return createdColor;
	}


	@Override
	public Color getOne(long id) {

		return null;
	}


	@Override
	public void deleteById(long id) {

		
	}

}
