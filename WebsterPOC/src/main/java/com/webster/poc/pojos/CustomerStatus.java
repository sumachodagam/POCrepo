package com.webster.poc.pojos;

import java.io.Serializable;

public class CustomerStatus implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6134752166572286304L;
	
	private int customerId;
	private String status;
	
	public CustomerStatus() {
	}
	
	public CustomerStatus(int customerId, String status) {
		this.customerId = customerId;
		this.status= status;
	}
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return String.format("CustomerStatus{customerId=%s, status=%s}", getCustomerId(), getStatus());
	}
	
}
