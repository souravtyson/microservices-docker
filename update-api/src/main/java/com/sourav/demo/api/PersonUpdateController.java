package com.sourav.demo.api;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sourav.demo.model.Person;
import com.sourav.demo.service.PersonService;

@RestController
@RequestMapping("api/v1")
public class PersonUpdateController {
	
	@Autowired
	private PersonService personService;
	
	@PostMapping(path = "person/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String updatePerson(@RequestBody Person person, @PathVariable UUID id) {
		return personService.updatePersonById(person,id);
	}
}
