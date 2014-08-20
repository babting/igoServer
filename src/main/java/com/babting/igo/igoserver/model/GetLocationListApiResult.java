package com.babting.igo.igoserver.model;

import java.util.ArrayList;
import java.util.List;

public class GetLocationListApiResult extends ApiResult {
	private static final long serialVersionUID = -1471222736060582542L;
	private int count;
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	private List<LocationInfoVO> locationList;

	public List<LocationInfoVO> getLocationList() {
		if(locationList == null) {
			locationList = new ArrayList<LocationInfoVO>();
		}
		return locationList;
	}

	public void setLocationList(List<LocationInfoVO> locationList) {
		this.locationList = locationList;
	}
}
