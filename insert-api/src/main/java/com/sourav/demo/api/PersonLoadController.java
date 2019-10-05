package com.sourav.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sourav.demo.model.Person;
import com.sourav.demo.service.PersonService;

@RestController
@RequestMapping("api/v1")
public class PersonLoadController {
	
	@Autowired
	private PersonService personService;
	
	@PostMapping(path = "person", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String addPerson(@RequestBody Person person) {
		return personService.addPerson(person);
	}
}
