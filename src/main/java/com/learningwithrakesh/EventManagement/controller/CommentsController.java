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
}
