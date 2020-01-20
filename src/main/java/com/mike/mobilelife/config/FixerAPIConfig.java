package com.mike.mobilelife.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "fixer")
public class FixerAPIConfig {	
	private String access_key;
	
	public String getAccessKey() {
		return this.access_key;
	}
	
	public void setAccessKey(String value) {
		this.access_key = value;
	}
}
