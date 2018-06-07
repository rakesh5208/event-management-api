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
package com.learningwithrakesh.EventManagement.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 *
 */
@MappedSuperclass
public class BaseDomain implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private Long whenCreated;
	private Long whenLastUpdated;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Long getWhenCreated() {
		return whenCreated;
	}

	public void setWhenCreated(Long whenCreated) {
		this.whenCreated = whenCreated;
	}

	public Long getWhenLastUpdated() {
		return whenLastUpdated;
	}

	public void setWhenLastUpdated(Long whenLastUpdated) {
		this.whenLastUpdated = whenLastUpdated;
	}

}
