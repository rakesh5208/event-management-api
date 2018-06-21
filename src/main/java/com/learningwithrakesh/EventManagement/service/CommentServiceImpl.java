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
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Component;

import com.learningwithrakesh.EventManagement.entity.Comment;
import com.learningwithrakesh.EventManagement.repository.CommentRepo;

/**
 *
 */
@Component
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentRepo commentRepo;

	@Override
	public List<Comment> getAllComments(long rootId) {
		return commentRepo.findAll(rootId);
	}

	@Override
	public Comment addComment(Comment comment, long rootId) {
		return commentRepo.save(comment, rootId);
	}

	@Override
	public Comment replyOnComment(Comment comment, long rootId, long onCommentId) {
		return this.commentRepo.saveReplyComment(comment, rootId, onCommentId);
	}

	@Override
	public List<Comment> getAllRepliesOnComment(long rootId, long commentId) {
		return this.commentRepo.getAllRepliesOnComment(rootId, commentId);
	}

}
