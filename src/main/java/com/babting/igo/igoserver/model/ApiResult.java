package com.babting.igo.igoserver.model;

import java.io.Serializable;

public class ApiResult implements Serializable {
	public static final String STATUS_SUCCESS = "0";
	public static final String STATUS_NETWORK_UNAVAILABLE = "1";
	public static final String API_ERROR = "2";
	private String resultCode;

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
}
