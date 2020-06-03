package com.software.model;

public class Software {
	private String name;
	private double version;
	private String description;
	
	public Software(String name, double version, String description) {
		this.name = name;
		this.version = version;
		this.description = description;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getVersion() {
		return version;
	}
	
	public void setVersion(double version) {
		this.version = version;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
}
