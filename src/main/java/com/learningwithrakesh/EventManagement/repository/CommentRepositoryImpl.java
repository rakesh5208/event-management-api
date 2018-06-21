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
		List<Comment> comments = openSession.createQuery("from Comment where event_id = :eventId and reply_id is null")
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
	@Override
	public Comment getOne(long id) {
		Session openSession = sessionFactory.openSession();
		openSession.beginTransaction();
		Comment comment = (Comment) openSession.byId(Comment.class).load(id);
		openSession.getTransaction().commit();
		openSession.close();
		return comment;
	}

	@Override
	public Comment saveReplyComment(Comment comment, long eventId, long onCommentId) {
		Event event = this.eventRepo.getOne(eventId);
		Comment replyOnComment = this.getOne(onCommentId);
		Session openSession = sessionFactory.openSession();
		openSession.beginTransaction();
		comment.setWhenCreated(System.currentTimeMillis());
		comment.setEvent(event);
		comment.setReply(replyOnComment);
		Long commentId = (Long) openSession.save(comment);
		openSession.getTransaction().commit();
		Comment savedComment = (Comment) openSession.load(Comment.class, commentId);
		openSession.close();
		return savedComment;
	}

	@Override
	public List<Comment> getAllRepliesOnComment(long eventId, long commentId) {
		Session openSession = this.sessionFactory.openSession();
		openSession.beginTransaction();
		List<Comment> replies = openSession.createQuery("from Comment where event_id= :eventId and reply_id = :replyId")
				.setParameter("eventId", eventId)
				.setParameter("replyId", commentId).list();
		openSession.getTransaction().commit();
		openSession.close();
		return replies;
	}


}
