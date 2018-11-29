package com.example.whiteboardfall2018prernapurohitserverjava.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.whiteboardfall2018prernapurohitserverjava.models.Topic;
import com.example.whiteboardfall2018prernapurohitserverjava.models.Widget;
import com.example.whiteboardfall2018prernapurohitserverjava.repositories.WidgetRepository;


import com.example.whiteboardfall2018prernapurohitserverjava.repositories.TopicRepository;

@RestController
@CrossOrigin(origins = "*" , allowCredentials = "true" , allowedHeaders = "*")
public class WidgetService {
	@Autowired
	UserService userService;
	
	@Autowired
	WidgetRepository widgetRepository;
	
	@Autowired
	TopicRepository topicRepository;
	
/*	@GetMapping("/api/topic/{topicId}/widget")
	public List<Widget> findWidgetsForTopic(
			@PathVariable("topicId") int topicId) {
		return (List<Widget>)
				topicRepository.findById(topicId)
				.get().getWidgets();
	}*/
	
	@GetMapping("/api/widget")
	public List<Widget> findAllWidgets() {
		return (List<Widget>) widgetRepository.findAll();
	}
	
	@GetMapping("/api/widget/{wid}")
	public Widget findWidgetById(@PathVariable("wid") int widgetId) {
		return widgetRepository.findById(widgetId).get();
	}
	
	@PutMapping("/api/widget/{wid}")
	public List<Widget> updateWidget(@PathVariable("wid") int widgetId,
			@RequestBody Widget widget) {
		Widget data = widgetRepository.findById(widgetId).get();
		Topic topic = widgetRepository.findById(widgetId).get().getTopic();
		if(data != null) {
			data.setTitle(widget.getTitle());
			data.setHeadingValue(widget.getHeadingValue());
			data.setOptions(widget.getOptions());
			data.setLink(widget.getLink());
			data.setText(widget.getText());
			data.setWidgetType(widget.getWidgetType());
			widgetRepository.save(data);
			return topic.getWidgets();
		}
		else {
			return null;
		}
	}
	
	@DeleteMapping("/api/widget/{wid}")
	public List<Widget> deleteWidget(@PathVariable("wid") int widgetId){
		Topic topic = widgetRepository.findById(widgetId).get().getTopic();
		widgetRepository.deleteById(widgetId);
		return topic.getWidgets();
	}
}