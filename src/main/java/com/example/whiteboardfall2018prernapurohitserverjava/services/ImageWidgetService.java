package com.example.whiteboardfall2018prernapurohitserverjava.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.whiteboardfall2018prernapurohitserverjava.models.ImageWidget;

import com.example.whiteboardfall2018prernapurohitserverjava.models.Topic;
import com.example.whiteboardfall2018prernapurohitserverjava.models.Widget;
import com.example.whiteboardfall2018prernapurohitserverjava.repositories.ImageWidgetRepository;
import com.example.whiteboardfall2018prernapurohitserverjava.repositories.TopicRepository;
@RestController
@CrossOrigin(origins = "http://localhost:3000" , allowCredentials = "true" , allowedHeaders = "*")
public class ImageWidgetService {
	
	@Autowired
	TopicRepository topicRepository;
	@Autowired
	ImageWidgetRepository imageWidgetRepository;
	
	@PostMapping("/api/topic/{topicId}/image/widget")
	public List<Widget> createImageWidget(
			@PathVariable("topicId") int topicId,
			@RequestBody ImageWidget imageWidget) {
		Topic topic = topicRepository.findById(topicId).get();
		imageWidget.setTopic(topic);
		imageWidget = imageWidgetRepository.save(imageWidget);
		return topicRepository.findById(topicId).get().getWidgets();
	}
	
	@GetMapping("/api/topic/{tid}/image/widget")
	public List<Widget> findAllWidgetsForTopic(
			@PathVariable("tid") int topicId)
	{
		return(List<Widget>) topicRepository.findById(topicId).get().getWidgets();
	}
	
	@GetMapping("/api/image/widget/{wid}")
	public ImageWidget findWidgetById(@PathVariable("wid") int widgetId) {
		return imageWidgetRepository.findById(widgetId).get();
	}
	
	@GetMapping("/api/image/widget")
	public List<ImageWidget> findAllWidgets(@PathVariable("wid") int widgetId) {
		return (List<ImageWidget>)imageWidgetRepository.findAll();
	}
	
	@PutMapping("/api/image/widget/{wid}")
	public List<Widget> updateWidget(@PathVariable("wid") int widgetId,
			@RequestBody Widget widget) {
		ImageWidget data = imageWidgetRepository.findById(widgetId).get();
		Topic topic = imageWidgetRepository.findById(widgetId).get().getTopic();
		if(data != null) {
			data.setTitle(widget.getTitle());
			data.setText(widget.getText());
			data.setLink(widget.getLink());
			imageWidgetRepository.save(data);
			return topic.getWidgets();
		}
		else {
			return null;
		}
	}
	
	@DeleteMapping("/api/image/widget/{wid}")
	public List<Widget> deleteWidget(@PathVariable("wid") int widgetId){
		Topic topic = imageWidgetRepository.findById(widgetId).get().getTopic();
		imageWidgetRepository.deleteById(widgetId);
		return topic.getWidgets();
	}

}
