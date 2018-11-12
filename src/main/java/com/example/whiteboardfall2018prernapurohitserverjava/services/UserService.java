package com.example.whiteboardfall2018prernapurohitserverjava.services;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.whiteboardfall2018prernapurohitserverjava.models.Course;
import com.example.whiteboardfall2018prernapurohitserverjava.models.Lesson;
import com.example.whiteboardfall2018prernapurohitserverjava.models.Module;
import com.example.whiteboardfall2018prernapurohitserverjava.models.Topic;
import com.example.whiteboardfall2018prernapurohitserverjava.models.User;
import com.example.whiteboardfall2018prernapurohitserverjava.models.Widget;
import com.example.whiteboardfall2018prernapurohitserverjava.repositories.UserRepository;

@RestController
//@CrossOrigin(origins="*")
@CrossOrigin(origins = "http://localhost:3000" , allowCredentials = "true" , allowedHeaders = "*")
public class UserService {
	
	HttpSession newSession;
	static List<User> users = new ArrayList<User>();

	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/api/user")
	public List<User> findAllUsers() {
		return (List<User>)userRepository.findAll();
	}
	
	@GetMapping("/api/user/{id}")
	public User findUserById(@PathVariable("id") int userId) {
		return userRepository.findById(userId).get();
	}
	
	@PostMapping("/api/user")
	public List<User> createUser(@RequestBody User user) {
		userRepository.save(user);
		return (List<User>)userRepository.findAll();
	}
	
	@PostMapping("/api/register")
	public User register(
			@RequestBody User user,
			HttpSession session, HttpServletRequest request) {
		session.setAttribute("currentUser", user);
		newSession=session;
		userRepository.save(user);
		return user;
	}
	
	
	@GetMapping("/api/profile")
	public User profile(HttpSession session, HttpServletRequest request) {
		User currentUser = (User)newSession.getAttribute("currentUser");
		return userRepository.findById(currentUser.getId()).get();

	}
	
	@PostMapping("/api/logout")
	public void logout(HttpSession session) {
		session.invalidate();
	}
	
	@PostMapping("/api/login")
	public User login(
			@RequestBody User credentials,
			HttpSession session) {
		System.out.println(credentials.getUsername());
		System.out.println(credentials.getPassword());
		String username = credentials.getUsername();
		String password = credentials.getPassword();
		if(userRepository.findByUsername(username).size() == 1) {
			User user = userRepository.findByUsername(username).get(0);
			if(user.getPassword().equals(password)) {
				session.setAttribute("currentUser", user);
				newSession = session;
				return user;
			}
			else {
				return null;
			}
		}
		return null;
		
	}

}

/*

	@GetMapping("/api/user")
	public List<User> findAllUsers() {
		return users;
	}
	
	@GetMapping("/api/user/{id}")
	public User findUserById(@PathVariable("id") int userId) {
		System.out.println("in findUserById");
		System.out.println("id" + userId);
		for(User user: users) {
			if(user.getId() == userId) {
				System.out.println("SUCCESS");
				System.out.println("user   "+user);
				return user;
			}
				
		}
		return null;
	}
	
	@PostMapping("/api/user")
	public List<User> createUser(@RequestBody User user) {
		users.add(user);
		return users;
	}
	
	@PostMapping("/api/register")
	public User register(
			@RequestBody User user,
			HttpSession session, HttpServletRequest request) {
		session.setAttribute("currentUser", user);
		newSession=session;
		//System.out.println(user.getUsername());
		
		
		users.add(user);
		return user;
	}
	
	
	@GetMapping("/api/profile")
	public User profile(HttpSession session, HttpServletRequest request) {
		User currentUser = (User)newSession.getAttribute("currentUser");
		return currentUser;
	}
	
	@PostMapping("/api/logout")
	public void logout(HttpSession session) {
		session.invalidate();
	}
	
	@PostMapping("/api/login")
	public User login(
			@RequestBody User credentials,
			HttpSession session) {
	 for (User user : users) {
	  if( user.getUsername().equals(credentials.getUsername())) {
	    session.setAttribute("currentUser", user);
	    newSession = session;
	    return user;
	  }
	  
	 }
	 return null;
	}



*/