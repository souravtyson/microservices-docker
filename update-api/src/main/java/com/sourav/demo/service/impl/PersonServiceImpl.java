package com.sourav.demo.service.impl;

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
	public String updatePersonById(Person person, UUID id) {
		personDao.updatePersonById(person,id);
		return null;
	}
}
