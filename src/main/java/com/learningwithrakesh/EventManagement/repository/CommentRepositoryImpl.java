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

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.learningwithrakesh.EventManagement.entity.Comment;
import com.learningwithrakesh.EventManagement.entity.Event;

/**
 *
 */
@Component
public class CommentRepositoryImpl implements CommentRepo {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	EventRepository eventRepo;

	@Override
	public List<Comment> findAll(long eventId) {
		Session openSession = sessionFactory.openSession();
		openSession.beginTransaction();
		List<Comment> comments = openSession.createQuery("from Comment where event_id = :eventId")
				.setParameter("eventId", eventId).list();
		openSession.getTransaction().commit();
		openSession.close();
		return comments;
	}

	@Override
	public Comment save(Comment comment, long eventId) {
		Event event = this.eventRepo.getOne(eventId);
		Session openSession = sessionFactory.openSession();
		openSession.beginTransaction();
		comment.setWhenCreated(System.currentTimeMillis());
		comment.setEvent(event);
		Long commentId = (Long) openSession.save(comment);
		openSession.getTransaction().commit();
		Comment savedComment = (Comment) openSession.load(Comment.class, commentId);
		openSession.close();
		return savedComment;
	}

}
