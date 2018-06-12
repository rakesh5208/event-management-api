/*******************************************************************************
 *  ============================================================================
 *                       Copyright (C) Pfizer Inc.
 *                               All rights reserved
 *  ============================================================================
 *  Notice: All Rights Reserved.
 *  This material contains the trade secrets and confidential information of
 *  Pfizer Inc., which embody substantial creative effort, ideas and
 *  expressions. No part of this material may be reproduced or transmitted
 *  in any form or by any means, electronic, mechanical, optical or otherwise,
 *  including photocopying and recording, or in connection with any information
 *  storage or retrieval system, without written permission from:
 *
 *  Hospira, a Pfizer company
 *  13520 Evening Creek Dr., Suite 200
 *  San Diego, CA 92128
 *  www.hospira.com
 *******************************************************************************/
package com.learningwithrakesh.EventManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learningwithrakesh.EventManagement.entity.Event;
import com.learningwithrakesh.EventManagement.repository.EventRepository;

/**
 *
 */
@Service()
public class EventServiceImpl implements EventService {

	@Autowired
	EventRepository eventRepo;

	/**
	 * get list of all events
	 */
	@Override
	public List<Event> getAllEvents() {
		return this.eventRepo.findAll();
	}

	/**
	 * 
	 */
	@Override
	public Event createEvent(Event event) {
		Long currentTimeInMillsec = System.currentTimeMillis();
		event.setWhenCreated(currentTimeInMillsec);
		event.setWhenLastUpdated(currentTimeInMillsec);
		return this.eventRepo.save(event);
	}

	/**
	 * 
	 */
	@Override
	public Event updateEvent(Long id, Event event) {
		/**
		 * Set Event event properties that user can update
		 */
		Event existedValue = this.eventRepo.getOne(id);
		event.setWhenCreated(existedValue.getWhenCreated());
		event.setWhenLastUpdated(System.currentTimeMillis());
		return this.eventRepo.save(event);
	}

	/**
	 * 
	 */
	@Override
	public Event getEvent(Long id) {
		System.out.println(this.eventRepo.getOne(id).toString());
		return this.eventRepo.getOne(id);
	}

	/**
	 * 
	 */
	@Override
	public void deleteEvent(Long id) {
		this.eventRepo.deleteById(id);
	}

}
