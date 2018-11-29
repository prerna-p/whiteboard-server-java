package com.example.whiteboardfall2018prernapurohitserverjava.services;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.whiteboardfall2018prernapurohitserverjava.models.Course;
import com.example.whiteboardfall2018prernapurohitserverjava.models.Lesson;
import com.example.whiteboardfall2018prernapurohitserverjava.models.Module;
import com.example.whiteboardfall2018prernapurohitserverjava.models.User;
import com.example.whiteboardfall2018prernapurohitserverjava.models.Widget;
import com.example.whiteboardfall2018prernapurohitserverjava.repositories.LessonRepository;
import com.example.whiteboardfall2018prernapurohitserverjava.repositories.ModuleRepository;


@RestController
@CrossOrigin(origins = "*" , allowCredentials = "true" , allowedHeaders = "*")
public class LessonService {
	@Autowired
	UserService userService;
	@Autowired
	LessonRepository lessonRepository;
	@Autowired
	ModuleRepository moduleRepository;
	@GetMapping("/api/lesson")
	public List<Lesson> findAllLessons() {
		return (List<Lesson>) lessonRepository.findAll();
	}
	int userId,courseId,moduleId;
	
	@GetMapping("/api/course/{cid}/module/{mid}/lesson")
	public List<Lesson> findLessonsForCourseId(
			@PathVariable("cid") int courseId,
			@PathVariable("mid") int moduleId,
			HttpSession session) {
		return moduleRepository.findById(moduleId).get().getLessons();
	}
	
	@GetMapping("/api/module/{mid}/lesson")
	public List<Lesson> findAllLessons(
			@PathVariable("mid") int moduleId,
			HttpSession session) {
		return moduleRepository.findById(moduleId).get().getLessons();
	}
	
	@PostMapping("/api/module/{mid}/lesson")
	public List<Lesson> createLesson(
			@PathVariable("mid") int midId,
			@RequestBody Lesson lesson,
			HttpSession session) {
		
		Module module = moduleRepository.findById(midId).get();
		if(module != null) {
			lesson.setModule(module);
			lessonRepository.save(lesson);
			return module.getLessons();
		}
		return null;
	}
	
	@GetMapping("/api/lesson/{lid}")
	public Lesson findLessonById(
			@PathVariable("lid") int lessonId,
			HttpSession session) {
		return lessonRepository.findById(lessonId).get();
	}
	
	@PutMapping("/api/lesson/{lid}")
	public List<Lesson> updateLesson(
			@PathVariable("lid") int lessonId,
			@RequestBody Lesson newLesson,
			HttpSession session){
		Lesson data = lessonRepository.findById(lessonId).get(); //find lesson
		if(data != null) {
			data.setTitle(newLesson.getTitle()); //update lesson title
			data.setTopics(newLesson.getTopics()); //update topics for lesson
			lessonRepository.save(data);			// save to repo
			return data.getModule().getLessons();  //return all lessons for module
		}
		else {
			return null;
		}
		
	}
	
	@DeleteMapping("/api/lesson/{lid}")
	public List<Lesson> deleteLesson(
			@PathVariable("lid") int lessonId,
			HttpSession session) {
		
		Module moduleForLesson = lessonRepository.findById(lessonId).get().getModule();
		lessonRepository.deleteById(lessonId);
		return moduleForLesson.getLessons();
		
	}
/*	
 * 	@GetMapping("/api/course/{cid}/module/{mid}/lesson")
	public List<Lesson> findLessonsForCourseId(
			@PathVariable("cid") int courseId,
			@PathVariable("mid") int moduleId,
			HttpSession session) {
		User currentUser = (User)session.getAttribute("currentUser");
		this.courseId = courseId;
		for(Course course: currentUser.getCourses()) {
			if(course.getId() == courseId) {
				for(Module module: course.getModules()) {
					if(module.getId() == moduleId) {
						this.moduleId = moduleId;
						return module.getLessons();
					}
				}
			}
		}
		return null;
	}
	
 * 	@GetMapping("/api/module/{mid}/lesson")
	public List<Lesson> findAllLessons(
			@PathVariable("moduleId") int moduleId,
			HttpSession session) {
		User currentUser = (User)session.getAttribute("currentUser");
		List<Lesson> lessons = null;
		for(Course course: currentUser.getCourses()) {
			for(Module module : course.getModules()) {
				if(module.getId() == moduleId) {
					this.moduleId = moduleId;
					lessons = module.getLessons();
				}
			}
		}
		return lessons;
	}
	
 * 	@PostMapping("/api/module/{mid}/lesson")
	public List<Lesson> createLesson(
			@PathVariable("mid") int midId,
			@RequestBody Lesson lesson,
			HttpSession session) {
		User currentUser = (User)session.getAttribute("currentUser");
		List<Lesson> lessons = null;
		for(Course course: currentUser.getCourses()) {
			for(Module module : course.getModules()) {
				if(module.getId() == moduleId) {
					this.moduleId = midId;
					lessons = module.getLessons();
				}
			}
		}
		lessons.add(lesson);
		return lessons;
	}
	
 * 	@GetMapping("/api/lesson/{lid}")
	public Lesson findLessonById(
			@PathVariable("lid") int lessonId,
			HttpSession session) {
		
		User currentUser = (User)session.getAttribute("currentUser");
		for(Course course: currentUser.getCourses()) {
			for(Module module : course.getModules()) {
				for(Lesson lesson : module.getLessons()) {
					if(lesson.getId() == lessonId)
						return lesson;
				}
			}
		}

		return null;
	}
 * 	@PutMapping("/api/lesson/{lid}")
	public List<Lesson> updateLesson(
			@PathVariable("lid") int lessonId,
			@RequestBody Lesson newLesson,
			HttpSession session){
		User currentUser = (User)session.getAttribute("currentUser");
		Lesson old = null;
		List<Lesson> myLessons=null;
		for(Course course: currentUser.getCourses()) {
			for(Module module : course.getModules()) {
				for(Lesson lesson : module.getLessons()) {
					if(lesson.getId() == lessonId) {
						lesson.setTitle(newLesson.getTitle());
						myLessons = module.getLessons();
						return module.getLessons();
					}
						
				}
			}
		}
		
		return myLessons;
		
	}
	
    @DeleteMapping("/api/lesson/{lid}")
	public List<Lesson> deleteLesson(
			@PathVariable("lid") int lessonId,
			HttpSession session) {
		User currentUser = (User)session.getAttribute("currentUser");
		Lesson old = null;
		List<Lesson> myLessons=null;
		for(Course course: currentUser.getCourses()) {
			for(Module module : course.getModules()) {
				for(Lesson lesson : module.getLessons()) {
					if(lesson.getId() == lessonId) {
						old = lesson;
						myLessons = module.getLessons();
					}
						
				}
			}
		}
		myLessons.remove(old);
		return myLessons;
		
	}*/
}