package com.sourav.demo.service;

import java.util.UUID;

import com.sourav.demo.model.Person;

public interface PersonService {

	String updatePersonById(Person person, UUID id);
}
