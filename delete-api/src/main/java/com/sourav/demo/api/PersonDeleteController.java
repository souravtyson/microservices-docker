package com.sourav.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sourav.demo.service.PersonService;

@RestController
@RequestMapping("api/v1")
public class PersonDeleteController {
	
	@Autowired
	private PersonService personService;
	
	@DeleteMapping(path="person/{id}", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public String deletePerson(@PathVariable String id) {
		return personService.deletePersonById(id);
	}
}
