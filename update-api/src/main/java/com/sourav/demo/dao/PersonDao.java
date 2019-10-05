package com.sourav.demo.dao;

import java.util.UUID;

import com.sourav.demo.model.Person;

public interface PersonDao {

	void updatePersonById(Person person, UUID id);
}
