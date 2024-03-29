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

import com.example.whiteboardfall2018prernapurohitserverjava.models.ListWidget;
import com.example.whiteboardfall2018prernapurohitserverjava.models.Topic;
import com.example.whiteboardfall2018prernapurohitserverjava.models.Widget;
import com.example.whiteboardfall2018prernapurohitserverjava.repositories.ListWidgetRepository;
import com.example.whiteboardfall2018prernapurohitserverjava.repositories.TopicRepository;

@RestController
@CrossOrigin(origins = "http://localhost:3000" , allowCredentials = "true" , allowedHeaders = "*")
public class ListWidgetService {
	@Autowired
	TopicRepository topicRepository;
	@Autowired
	ListWidgetRepository listWidgetRepository;
	
	@PostMapping("/api/topic/{topicId}/list/widget")
	public List<Widget> createListWidget(
			@PathVariable("topicId") int topicId,
			@RequestBody ListWidget listWidget) {
		Topic topic = topicRepository.findById(topicId).get();
		listWidget.setTopic(topic);
		listWidget = listWidgetRepository.save(listWidget);
		return topicRepository.findById(topicId).get().getWidgets();
	}
	
	@GetMapping("/api/topic/{tid}/list/widget")
	public List<Widget> findAllWidgetsForTopic(
			@PathVariable("tid") int topicId)
	{
		return(List<Widget>) topicRepository.findById(topicId).get().getWidgets();
	}
	
	@GetMapping("/api/list/widget/{wid}")
	public ListWidget findWidgetById(@PathVariable("wid") int widgetId) {
		return listWidgetRepository.findById(widgetId).get();
	}
	
	@GetMapping("/api/list/widget")
	public List<ListWidget> findAllWidgets(@PathVariable("wid") int widgetId) {
		return (List<ListWidget>)listWidgetRepository.findAll();
	}
	
	@PutMapping("/api/list/widget/{wid}")
	public List<Widget> updateWidget(@PathVariable("wid") int widgetId,
			@RequestBody Widget widget) {
		ListWidget data = listWidgetRepository.findById(widgetId).get();
		Topic topic = listWidgetRepository.findById(widgetId).get().getTopic();
		if(data != null) {
			data.setTitle(widget.getTitle());
			data.setText(widget.getText());
			data.setOptions(widget.getOptions());
			listWidgetRepository.save(data);
			return topic.getWidgets();
		}
		else {
			return null;
		}
	}
	
	@DeleteMapping("/api/list/widget/{wid}")
	public List<Widget> deleteWidget(@PathVariable("wid") int widgetId){
		Topic topic = listWidgetRepository.findById(widgetId).get().getTopic();
		listWidgetRepository.deleteById(widgetId);
		return topic.getWidgets();
	}
}
