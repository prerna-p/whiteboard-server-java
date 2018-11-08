package com.example.whiteboardfall2018prernapurohitserverjava.models;

public class Topic {
	private int id = User.autoIncrement++;//(int)(Math.random() * Integer.MAX_VALUE);
	private String title;
	public Topic() {}
	public Topic(String title) {
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
