package com.clinicalsapi.dto;

public class ClinicalDataRequest {
	private String componenetName;
	private String componentValue;
	private int id;
	public String getComponenetName() {
		return componenetName;
	}
	public void setComponenetName(String componenetName) {
		this.componenetName = componenetName;
	}
	public String getComponentValue() {
		return componentValue;
	}
	public void setComponentValue(String componentValue) {
		this.componentValue = componentValue;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
