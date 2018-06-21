package com.learningwithrakesh.EventManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.learningwithrakesh.EventManagement.entity.Comment;
import com.learningwithrakesh.EventManagement.service.CommentService;

/**
 *
 */
@SuppressWarnings("javadoc")
@CrossOrigin
@RestController
@RequestMapping("/{rootId}/comments")
public class CommentsController {

	@Autowired
	CommentService commentService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Comment> getAll(@PathVariable("rootId") long rootId) {
		return this.commentService.getAllComments(rootId);
	}


	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Comment addComment(@RequestBody Comment comment, @PathVariable("rootId") long rootId) {
		return this.commentService.addComment(comment, rootId);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public List<Comment> getAllReplyOnComment(@PathVariable("rootId") long rootId,
			@PathVariable("id") long onCommentId) {
		return this.commentService.getAllRepliesOnComment(rootId, onCommentId);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Comment replyOnComment(@RequestBody Comment comment, @PathVariable("rootId") long rootId,
			@PathVariable("id") long onCommentId) {
		return this.commentService.replyOnComment(comment, rootId, onCommentId);
	}
}
