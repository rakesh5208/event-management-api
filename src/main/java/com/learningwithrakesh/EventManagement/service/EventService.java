package com.learningwithrakesh.EventManagement.service;

import java.util.List;

import com.learningwithrakesh.EventManagement.entity.Event;

/**
 *
 */
public interface EventService {
	/**
	 * get list of all events
	 * 
	 * @return
	 */
	public List<Event> getAllEvents();

	/**
	 * create an event
	 * 
	 * @param event
	 * @return
	 */
	public Event createEvent(Event event);

	/**
	 * Update an event
	 * 
	 * @param event
	 * @return
	 */
	public Event updateEvent(Long id, Event event);

	/**
	 * get an event
	 * 
	 * @param id
	 * @return
	 */
	public Event getEvent(Long id);

	/**
	 * delete an event
	 * 
	 * @param id
	 * @return
	 */
	public void deleteEvent(Long id);
}
