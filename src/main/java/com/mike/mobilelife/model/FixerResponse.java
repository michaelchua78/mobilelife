package com.mike.mobilelife.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FixerResponse {
	@JsonProperty("success")
	private boolean success;
	
	@JsonProperty("timestamp")
	private long timestamp;
	
	@JsonProperty("date")
	private String date;
	
	@JsonProperty("rates")
	private Map<String, Double> rates;
	
	public boolean getSuccess() {
		return this.success;
	}
	
	public long getTimestamp() {
		return this.timestamp;
	}
	
	public String getDate() {
		return this.date;
	}
	
	public Map<String, Double> getRates() {
		return this.rates;
	}
}