package com.babting.igo.igoserver.dao;

import java.util.List;

import javax.jdo.annotations.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jdo.LocalPersistenceManagerFactoryBean;
import org.springframework.stereotype.Repository;

import com.babting.igo.igoserver.model.jdo.LocationInfo;
import com.babting.igo.igoserver.model.jdo.LocationPhoto;

@Repository
public class JdoDAO extends AbstractJdoDAO {
	@Autowired
	public JdoDAO(LocalPersistenceManagerFactoryBean pmf) {
		super(pmf);
	}

	@Transactional
	public LocationInfo store(final LocationInfo greeting) {
		return getPersistenceManager().makePersistent(greeting);
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<LocationInfo> getAll() {
		return (List<LocationInfo>) getPersistenceManager().newQuery(LocationInfo.class).execute();
	}
	
	
	public LocationPhoto store(final LocationPhoto locationPhotoInfo) {
		return getPersistenceManager().makePersistent(locationPhotoInfo);
	}
}
