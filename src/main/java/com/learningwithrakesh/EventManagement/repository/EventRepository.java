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

import org.springframework.data.jpa.repository.JpaRepository;

import com.learningwithrakesh.EventManagement.entity.Event;

/**
 *
 */
public interface EventRepository extends JpaRepository<Event, Integer> {

}
