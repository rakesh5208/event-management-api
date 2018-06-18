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

import com.learningwithrakesh.EventManagement.entity.Color;

/**
 *
 */
@Component
public class ColorRepositoryImpl implements ColorRepository {

	@Autowired
	SessionFactory sessionFactory;
	/* (non-Javadoc)
	 * @see com.learningwithrakesh.EventManagement.repository.CurdRepository#findAll()
	 */
	@Override
	public List<Color> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.learningwithrakesh.EventManagement.repository.CurdRepository#save(java.lang.Object)
	 */
	@Override
	public Color save(Color color) {
		Session currentSession = sessionFactory.openSession();
		currentSession.beginTransaction();
		Long id = (Long) currentSession.save(color);
		currentSession.getTransaction().commit();
		Color createdColor = (Color) currentSession.load(Color.class, id);
		currentSession.close();
		return createdColor;
	}

	/* (non-Javadoc)
	 * @see com.learningwithrakesh.EventManagement.repository.CurdRepository#getOne(long)
	 */
	@Override
	public Color getOne(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.learningwithrakesh.EventManagement.repository.CurdRepository#deleteById(long)
	 */
	@Override
	public void deleteById(long id) {
		// TODO Auto-generated method stub
		
	}

}
