package com.dharmesh.spring.model;

import java.io.Serializable;

public class ATM implements Serializable{

	private static final long serialVersionUID = -7788619177798333712L;	
	
	public ATM(Address address, int distance, String type) {
		super();
		this.address = address;
		this.distance = distance;
		this.type = type;
	}
	public ATM() {
		super();
	}
	private Address address;
	private int distance;
	private String type;
	
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "ATM [address=" + address + ", distance=" + distance + ", type=" + type + "]";
	}
	
	
	
	
	
	
}
