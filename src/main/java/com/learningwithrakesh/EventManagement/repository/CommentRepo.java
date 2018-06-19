package com.learningwithrakesh.EventManagement.repository;

import java.util.List;

import com.learningwithrakesh.EventManagement.entity.Comment;

/**
 *
 */
public interface CommentRepo {

	public List<Comment> findAll(long eventId);

	public Comment save(Comment comment, long eventId);
}
