package com.example.whiteboardfall2018prernapurohitserverjava.models;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
public class Widget {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private int id = (int)(Math.random() * Integer.MAX_VALUE);
	private String title;
	public Widget() {}
	public Widget(String title) {
		this.title = title;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}