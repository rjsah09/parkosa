package com.parkosa.dto;

public class getLocationDTO {
	private int id;
	private String name;
	
	public getLocationDTO(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
 	
}
