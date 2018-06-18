package com.learningwithrakesh.EventManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.learningwithrakesh.EventManagement.entity.Event;
import com.learningwithrakesh.EventManagement.service.EventService;

/**
 *
 */
@CrossOrigin()
@RestController()
@RequestMapping("/events")
public class EventController {
	@Autowired
	EventService eventService;

	/**
	 * Get all events
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Event> getAllEvents() {
		return this.eventService.getAllEvents();
	}

	/**
	 * create a event
	 * 
	 * @param event
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Event createEvent(@RequestBody Event event) {
		System.out.println(event.getColors());
		return this.eventService.createEvent(event);
	}
	
	/**
	 * update an event
	 * 
	 * @param event
	 * @return
	 */
	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Event updateEvent(@RequestBody Event event, @PathVariable("id") Long id) {
		return this.eventService.updateEvent(id, event);
	}

	/**
	 * get event by id
	 * 
	 * @param eventId
	 * @return
	 */
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Event getEvent(@PathVariable("id") Long id) {
		System.out.println("Id to be queried" + id);
		return this.eventService.getEvent(id);
	}

	/**
	 * delete an event by id
	 * 
	 * @param eventId
	 * @return
	 */
	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<String> deleteEvent(@PathVariable("id") Long id) {
		System.out.println("Id to be queried" + id);
		this.eventService.deleteEvent(id);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
}
