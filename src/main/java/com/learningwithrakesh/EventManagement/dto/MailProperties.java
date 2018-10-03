package com.learningwithrakesh.EventManagement.dto;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class MailProperties {
	private Map<String,String> allProperties = new HashMap<>();
	
	
	public MailProperties(){}
	
	public MailProperties withProperty(String key, String value){
		this.allProperties.put(key, value);
		return this;
	}

	public Map<String, String> getAllProperties() {
		return allProperties;
	}
	
	public Properties buildJavaPropeties(){
		Properties props = new Properties();
		Set<String> keySet = allProperties.keySet();
		for(String key : keySet){
			props.setProperty(key,allProperties.get(key));
		}
		return props;
	}
	
	
}
