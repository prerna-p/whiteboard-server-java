package com.example.whiteboardfall2018prernapurohitserverjava.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.whiteboardfall2018prernapurohitserverjava.models.HeadingWidget;
import com.example.whiteboardfall2018prernapurohitserverjava.models.Topic;
import com.example.whiteboardfall2018prernapurohitserverjava.models.Widget;
import com.example.whiteboardfall2018prernapurohitserverjava.repositories.HeadingWidgetRepository;
import com.example.whiteboardfall2018prernapurohitserverjava.repositories.TopicRepository;

@RestController
@CrossOrigin(origins = "http://localhost:3000" , allowCredentials = "true" , allowedHeaders = "*")
public class HeadingWidgetService {

	
	@Autowired
	TopicRepository topicRepository;
	@Autowired
	HeadingWidgetRepository headingWidgetRepository;
	
	@PostMapping("/api/topic/{topicId}/heading/widget")
	public List<Widget> createHeadingWidget(
			@PathVariable("topicId") int topicId,
			@RequestBody HeadingWidget headingWidget) {
		Topic topic = topicRepository.findById(topicId).get();
		headingWidget.setTopic(topic);
		headingWidget = headingWidgetRepository.save(headingWidget);
		return topicRepository.findById(topicId).get().getWidgets();
	}
	
	@GetMapping("/api/topic/{tid}/heading/widget")
	public List<Widget> findAllWidgetsForTopic(
			@PathVariable("tid") int topicId)
	{
		return(List<Widget>) topicRepository.findById(topicId).get().getWidgets();
	}
	
	@GetMapping("/api/heading/widget/{wid}")
	public HeadingWidget findWidgetById(@PathVariable("wid") int widgetId) {
		return headingWidgetRepository.findById(widgetId).get();
	}
	
	@GetMapping("/api/heading/widget")
	public List<HeadingWidget> findAllWidgets(@PathVariable("wid") int widgetId) {
		return (List<HeadingWidget>)headingWidgetRepository.findAll();
	}
	
	@PutMapping("/api/heading/widget/{wid}")
	public List<Widget> updateWidget(@PathVariable("wid") int widgetId,
			@RequestBody Widget widget) {
		HeadingWidget data = headingWidgetRepository.findById(widgetId).get();
		Topic topic = headingWidgetRepository.findById(widgetId).get().getTopic();
		if(data != null) {
			data.setTitle(widget.getTitle());
			data.setText(widget.getText());
			data.setHeadingValue(widget.getHeadingValue());
			headingWidgetRepository.save(data);
			return topic.getWidgets();
		}
		else {
			return null;
		}
	}
	
	@DeleteMapping("/api/heading/widget/{wid}")
	public List<Widget> deleteWidget(@PathVariable("wid") int widgetId){
		Topic topic = headingWidgetRepository.findById(widgetId).get().getTopic();
		headingWidgetRepository.deleteById(widgetId);
		return topic.getWidgets();
	}
}
