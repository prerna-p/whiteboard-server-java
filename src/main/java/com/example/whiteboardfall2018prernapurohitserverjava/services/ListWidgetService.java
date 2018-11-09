package com.example.whiteboardfall2018prernapurohitserverjava.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	@PostMapping("/api/topic/{topicId}/widget/list")
	public List<Widget> createListWidget(
			@PathVariable("topicId") int topicId,
			@RequestBody ListWidget listWidget) {
		Topic topic = topicRepository.findById(topicId).get();
		listWidget.setTopic(topic);
		listWidget = listWidgetRepository.save(listWidget);
		return topicRepository.findById(topicId).get().getWidgets();
	}
}
