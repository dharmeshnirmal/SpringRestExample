package com.dharmesh.spring.model;

import java.io.Serializable;

public class GeoLocation implements Serializable{
	
	private static final long serialVersionUID = 6225871323974192797L;
	
	public GeoLocation(float lat, float lng) {
		super();
		this.lat = lat;
		this.lng = lng;
	}
	
	public GeoLocation() {
		super();
	}
	
	private float lat;
	private float lng;
	public float getLat() {
		return lat;
	}
	public void setLat(float lat) {
		this.lat = lat;
	}
	public float getLng() {
		return lng;
	}
	public void setLng(float lng) {
		this.lng = lng;
	}

	@Override
	public String toString() {
		return "GeoLocation [lat=" + lat + ", lng=" + lng + "]";
	}
	
	
	
}
