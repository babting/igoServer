package com.babting.igo.igoserver.model.jdo;

import java.util.List;

public class LocationPhoto {
	private String placeName;
	private Double latitude;
	private Double longitude;
	private List<PhotoInfo> photoInfoList;
	
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public List<PhotoInfo> getPhotoInfoList() {
		return photoInfoList;
	}
	public void setPhotoInfoList(List<PhotoInfo> photoInfoList) {
		this.photoInfoList = photoInfoList;
	}
}
