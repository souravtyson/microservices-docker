package com.sourav.demo.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sourav.demo.dao.PersonDao;
import com.sourav.demo.model.Person;
import com.sourav.demo.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService{

	@Autowired
	@Qualifier(value = "fakePerson")
	private PersonDao personDao;
	
	@Override
	public List<Person> getPersonsList() {
		return personDao.findPersons();
	}

	@Override
	public Optional<Person> selectPersonById(String id) {
		return personDao.selectPersonById(UUID.fromString(id));
	}
}
