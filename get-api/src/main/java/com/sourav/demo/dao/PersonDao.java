package com.sourav.demo.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.sourav.demo.model.Person;

public interface PersonDao {

	List<Person> findPersons();

	Optional<Person> selectPersonById(UUID id);
}
