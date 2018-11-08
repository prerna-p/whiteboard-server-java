package com.example.whiteboardfall2018prernapurohitserverjava.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.whiteboardfall2018prernapurohitserverjava.models.Course;
import com.example.whiteboardfall2018prernapurohitserverjava.models.Lesson;
import com.example.whiteboardfall2018prernapurohitserverjava.models.Module;
import com.example.whiteboardfall2018prernapurohitserverjava.models.Topic;
import com.example.whiteboardfall2018prernapurohitserverjava.models.User;
import com.example.whiteboardfall2018prernapurohitserverjava.models.Widget;
import com.example.whiteboardfall2018serverjava.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins="*")
public class WidgetService {
	@Autowired
	UserService userService;
	
	@Autowired
	WidgetRepository widgetRepository;
	@GetMapping("/api/widget")
	public List<Widget> findAllWidgets() {
		return (List<Widget>) widgetRepository.findAll();
	}
	
	
	@GetMapping("/api/user/{userId}/course/{courseId}/module/{moduleId}/lesson/{lessonId}/topic/{topicId}/widget")
	public List<Widget> findWidgetForTopic(
			@PathVariable("userId") int userId,
			@PathVariable("courseId") int courseId,
			@PathVariable("moduleId") int moduleId,
			@PathVariable("lessonId") int lessonId,
			@PathVariable("topicId") int topicId) {
		User user = userService.findUserById(userId);
		for(Course course: user.getCourses()) {
			if(course.getId() == courseId) {
				for(Module module: course.getModules()) {
					if(module.getId() == moduleId) {
						for(Lesson lesson: module.getLessons()) {
							if(lesson.getId() == lessonId) {
								for(Topic topic: lesson.getTopics()) {
									if(topic.getId() == topicId) {
										return topic.getWidgets();
									}
								}
							}
						}
					}
				}
			}
		}
		return null;
	}
}