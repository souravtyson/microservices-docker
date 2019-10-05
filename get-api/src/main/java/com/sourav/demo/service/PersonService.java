package com.sourav.demo.service;

import java.util.List;
import java.util.Optional;

import com.sourav.demo.model.Person;

public interface PersonService {
	
	List<Person> getPersonsList();

	Optional<Person> selectPersonById(String id);

}
