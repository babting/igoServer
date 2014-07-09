package com.babting.igo.igoserver.dao;

import javax.jdo.PersistenceManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jdo.LocalPersistenceManagerFactoryBean;

public class AbstractJdoDAO {
	private final LocalPersistenceManagerFactoryBean pmf;
	
	@Autowired
	public AbstractJdoDAO(final LocalPersistenceManagerFactoryBean pmf) {
		this.pmf = pmf;
	}

	public PersistenceManager getPersistenceManager() {
		return pmf.getObject().getPersistenceManager();
	}
}
