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

import com.example.whiteboardfall2018prernapurohitserverjava.models.ParagraphWidget;
import com.example.whiteboardfall2018prernapurohitserverjava.models.Topic;
import com.example.whiteboardfall2018prernapurohitserverjava.models.Widget;
import com.example.whiteboardfall2018prernapurohitserverjava.repositories.ParagraphWidgetRepository;
import com.example.whiteboardfall2018prernapurohitserverjava.repositories.TopicRepository;

@RestController
@CrossOrigin(origins = "http://localhost:3000" , allowCredentials = "true" , allowedHeaders = "*")
public class ParagraphWidgetService {
	TopicRepository topicRepository;
	@Autowired
	ParagraphWidgetRepository paragraphWidgetRepository;
	
	@PostMapping("/api/topic/{topicId}/paragraph/widget")
	public List<Widget> createParagraphWidget(
			@PathVariable("topicId") int topicId,
			@RequestBody ParagraphWidget paragraphWidget) {
		Topic topic = topicRepository.findById(topicId).get();
		paragraphWidget.setTopic(topic);
		paragraphWidget = paragraphWidgetRepository.save(paragraphWidget);
		return topicRepository.findById(topicId).get().getWidgets();
	}
	
	@GetMapping("/api/topic/{tid}/paragraph/widget")
	public List<Widget> findAllWidgetsForTopic(
			@PathVariable("tid") int topicId)
	{
		return(List<Widget>) topicRepository.findById(topicId).get().getWidgets();
	}
	
	@GetMapping("/api/paragraph/widget/{wid}")
	public ParagraphWidget findWidgetById(@PathVariable("wid") int widgetId) {
		return paragraphWidgetRepository.findById(widgetId).get();
	}
	
	@GetMapping("/api/paragraph/widget")
	public List<ParagraphWidget> findAllWidgets(@PathVariable("wid") int widgetId) {
		return (List<ParagraphWidget>)paragraphWidgetRepository.findAll();
	}
	
	@PutMapping("/api/paragraph/widget/{wid}")
	public List<Widget> updateWidget(@PathVariable("wid") int widgetId,
			@RequestBody Widget widget) {
		ParagraphWidget data = paragraphWidgetRepository.findById(widgetId).get();
		Topic topic = paragraphWidgetRepository.findById(widgetId).get().getTopic();
		if(data != null) {
			data.setTitle(widget.getTitle());
			data.setText(widget.getText());
			paragraphWidgetRepository.save(data);
			return topic.getWidgets();
		}
		else {
			return null;
		}
	}
	
	@DeleteMapping("/api/paragraph/widget/{wid}")
	public List<Widget> deleteWidget(@PathVariable("wid") int widgetId){
		Topic topic = paragraphWidgetRepository.findById(widgetId).get().getTopic();
		paragraphWidgetRepository.deleteById(widgetId);
		return topic.getWidgets();
	}
}
