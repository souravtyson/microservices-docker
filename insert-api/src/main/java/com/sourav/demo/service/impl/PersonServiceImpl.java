package com.sourav.demo.service.impl;

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
	
	public String addPerson(Person person) {
		if(personDao.insertPerson(person) == 1) {
			return "Success";
		}
		else {
			return "Failure";
		}
	}
}
