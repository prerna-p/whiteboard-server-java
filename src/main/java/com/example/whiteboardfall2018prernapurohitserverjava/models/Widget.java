package com.example.whiteboardfall2018prernapurohitserverjava.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
//@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Widget {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String title; //widget title
	private String text; //for widget body text
	private int headingValue; //for heading size
	private String options; //list type
	private String link; //for image
	//private int widgetsOrder; //for move-up move-down
	private String widgetType = "HEADING"; //for widget type
	
/*	public int getWidgetsOrder() {
		return widgetsOrder;
	}
	public void setWidgetsOrder(int widgetsOrder) {
		this.widgetsOrder = widgetsOrder;
	}*/
	public String getWidgetType() {
		return widgetType;
	}
	public void setWidgetType(String widgetType) {
		this.widgetType = widgetType;
	}
	public String getOptions() {
		return options;
	}
	public void setOptions(String options) {
		this.options = options;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	@ManyToOne()
	@JsonIgnore
	private Topic topic;
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	public int getHeadingValue() {
		return headingValue;
	}
	public void setHeadingValue(int headingValue) {
		
	}
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
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
