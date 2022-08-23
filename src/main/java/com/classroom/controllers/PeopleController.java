package com.classroom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.classroom.entity.People;
import com.classroom.services.PeopleService;

@RestController
@RequestMapping("/")
public class PeopleController {

	@Autowired
	PeopleService service;
	
	@GetMapping("/people/{idpeople}")
	public ResponseEntity<People> consultPeopleById(@PathVariable("idpeople") long idpeople){
		return ResponseEntity.status(HttpStatus.OK).body(service.consultPeopleById(idpeople));
	}
	
	@PostMapping("/people")
	public ResponseEntity<People> savePeople(@RequestBody People people){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.savePeople(people));
	}
}
