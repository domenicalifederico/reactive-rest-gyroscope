package com.example.reactive.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "orientations")
public class OrientationData {
	
	@Id
	private String id;
	private String device;
	
	public void setId(String id) {
		this.id = id;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setZ(int z) {
		this.z = z;
	}
	public String getId() {
		return id;
	}
	public String getDevice() {
		return device;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getZ() {
		return z;
	}
	private int x;
	private int y;
	private int z;
	
}
