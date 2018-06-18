package com.learningwithrakesh.EventManagement.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.learningwithrakesh.EventManagement.entity.Color;

/**
 *
 */
@Component
public class ColorRepositoryImpl implements ColorRepository {

	@Autowired
	SessionFactory sessionFactory;
	/* (non-Javadoc)
	 * @see com.learningwithrakesh.EventManagement.repository.CurdRepository#findAll()
	 */
	@Override
	public List<Color> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.learningwithrakesh.EventManagement.repository.CurdRepository#save(java.lang.Object)
	 */
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

	/* (non-Javadoc)
	 * @see com.learningwithrakesh.EventManagement.repository.CurdRepository#getOne(long)
	 */
	@Override
	public Color getOne(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.learningwithrakesh.EventManagement.repository.CurdRepository#deleteById(long)
	 */
	@Override
	public void deleteById(long id) {
		// TODO Auto-generated method stub
		
	}

}
