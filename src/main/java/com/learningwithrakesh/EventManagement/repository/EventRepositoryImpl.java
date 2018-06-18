package com.learningwithrakesh.EventManagement.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.learningwithrakesh.EventManagement.entity.Color;
import com.learningwithrakesh.EventManagement.entity.Event;

/**
 *
 */
@Component
public class EventRepositoryImpl implements EventRepository {

	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	ColorRepository colorRepo;
	
	@Override
	public List<Event> findAll() {

		Session openSession = sessionFactory.openSession();
		openSession.beginTransaction();
		List<Event> eventList = openSession.createQuery("from Event").list();

		openSession.getTransaction().commit();
		openSession.close();
		return eventList;
	}
	@Override
	public Event save(Event event) {
		Session openSession = sessionFactory.openSession();
		openSession.beginTransaction();

		Long savedEventId = (Long) openSession.save(event);
		for (Color c : event.getColors()) {
			this.colorRepo.save(c);
		}
		openSession.getTransaction().commit();
		Event savedEvent = (Event) openSession.load(Event.class, savedEventId);
		openSession.close();
		return savedEvent;
	}

	@Override
	public Event getOne(long id) {
		Session openSession = sessionFactory.openSession();
		openSession.beginTransaction();
		Event event = (Event) openSession.byId(Event.class).load(id);
		openSession.getTransaction().commit();
		openSession.close();
		return event;
	}

	@Override
	public void deleteById(long id) {

	}


}
