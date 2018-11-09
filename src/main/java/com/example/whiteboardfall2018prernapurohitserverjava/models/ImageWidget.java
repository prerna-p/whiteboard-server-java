package com.example.whiteboardfall2018prernapurohitserverjava.models;

import javax.persistence.Entity;

@Entity
public class ImageWidget extends Widget{
	private String link;

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
}
