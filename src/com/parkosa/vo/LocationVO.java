package com.parkosa.vo;

public class LocationVO {
	
	private int id;
	private String name;
	private int upperId;
	
	public LocationVO(int id, String name, int upperId) {
		super();
		this.id = id;
		this.name = name;
		this.upperId = upperId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUpperId() {
		return upperId;
	}

	public void setUpperId(int upperId) {
		this.upperId = upperId;
	}
	
	
}
