package com.learningwithrakesh.EventManagement.repository;

import java.util.List;

import com.learningwithrakesh.EventManagement.entity.Comment;

/**
 *
 */
public interface CommentRepo {

	public List<Comment> findAll(long eventId);

	public Comment getOne(long id);

	public Comment save(Comment comment, long eventId);

	public List<Comment> getAllRepliesOnComment(long eventId, long commentId);

	public Comment saveReplyComment(Comment comment, long eventId, long onCommentId);
}
