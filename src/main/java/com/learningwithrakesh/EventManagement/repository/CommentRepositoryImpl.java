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
