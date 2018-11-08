package com.example.whiteboardfall2018prernapurohitserverjava.models;

import java.util.ArrayList;
import java.util.List;

public class Topic {
	private int id = User.autoIncrement++;//(int)(Math.random() * Integer.MAX_VALUE);
	private String title;
	private List<Widget> widgets = new ArrayList<Widget>();
	
	public List<Widget> getWidgets() {
		return widgets;
	}
	
	public void setWidgets(List<Widget> widgets) {
		this.widgets = widgets;
	}
	
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
