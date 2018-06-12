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
package com.learningwithrakesh.EventManagement.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

/**
 *
 */
@Configuration
@EnableAutoConfiguration
@PropertySource(value = { "classpath:application.properties" })
@EntityScan(basePackages = { "com.learningwithrakesh.EventManagement" })
public class PersistentConfig {

	@Autowired
	Environment env;
	@Bean
	public DataSource dataSource() {
		System.out.println("Initiating datasource");
		DataSourceBuilder<?> create = DataSourceBuilder.create();
		create.url("jdbc:mysql://localhost:3306/eventmanagement");
		create.username("root");
		create.password("root");
		create.driverClassName("org.mariadb.jdbc.Driver");
		return create.build();
	}


	@Bean
	public LocalSessionFactoryBean setupSessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan("com.learningwithrakesh.EventManagement.entity");
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}

	private Properties hibernateProperties(){
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", env.getRequiredProperty("spring.jpa.properties.hibernate.dialect"));
		properties.setProperty("hibernate.ddl-auto", env.getRequiredProperty("spring.jpa.hibernate.ddl-auto"));
		properties.setProperty("hibernate.show_sql", env.getRequiredProperty("spring.jpa.show-sql"));

		properties.setProperty("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
		System.out.println(properties.toString());
		return properties;
	}
}
