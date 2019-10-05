package com.sourav.demo.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sourav.demo.model.Person;
import com.sourav.demo.service.PersonService;

@RestController
@RequestMapping("api/v1")
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	@GetMapping(path="allperson", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Person> getAllPerson() {
		return personService.getPersonsList();
	}
	
	@GetMapping(path = "person/{id}", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Person> getPersonByid(@PathVariable String id) {
		return personService.selectPersonById(id);
	}
}
