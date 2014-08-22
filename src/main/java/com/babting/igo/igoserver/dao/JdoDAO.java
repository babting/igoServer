package com.babting.igo.igoserver.dao;

import java.util.List;

import javax.jdo.Query;
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
	
	@Transactional
	public List<LocationInfo> getLocList(Double smallLatitude, Double smallLongitude, Double largeLatitude, Double largeLongitude) {
		Query q = getPersistenceManager().newQuery(LocationInfo.class);
		q.setFilter("latitude >= smallLatitude && latitude >= largeLatitude");
		q.setFilter("longitude >= smallLongitude && longitude <= largeLongitude");
		q.declareParameters("double smallLatitude, double largeLatitude, double smallLongitude, double largeLongitude");
		
		try {
			Double[] params = {smallLatitude, largeLatitude, smallLongitude, largeLongitude};
			return (List<LocationInfo>) q.executeWithArray(params);
		} finally {
			q.closeAll();
		}
	}
	
	public LocationPhoto store(final LocationPhoto locationPhotoInfo) {
		return getPersistenceManager().makePersistent(locationPhotoInfo);
	}
}
