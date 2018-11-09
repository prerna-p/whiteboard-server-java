package com.example.whiteboardfall2018prernapurohitserverjava.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.whiteboardfall2018prernapurohitserverjava.models.User;

public interface UserRepository 
extends CrudRepository<User, Integer> { 
	
}
