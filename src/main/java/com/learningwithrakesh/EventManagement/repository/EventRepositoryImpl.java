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
package com.learningwithrakesh.EventManagement.repository;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.learningwithrakesh.EventManagement.entity.Event;

/**
 *
 */
@Component
public class EventRepositoryImpl implements EventRepository {

	@Autowired
	SessionFactory sessionFactory;
	
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
		Serializable savedEntity = openSession.save(event);
		openSession.getTransaction().commit();
		openSession.close();
		return new Event();
	}

	@Override
	public Event getOne(long id) {
		Session openSession = sessionFactory.openSession();
		openSession.beginTransaction();
		Query createQuery = openSession.createQuery("from Event where id = :id");
		createQuery.setParameter("id", id);
		List<Event> list = createQuery.list();
		openSession.getTransaction().commit();
		openSession.close();
		if (list.size() > 1) {
			return null;
		}
		
		return list.get(0);
	}

	@Override
	public void deleteById(long id) {

	}


}
