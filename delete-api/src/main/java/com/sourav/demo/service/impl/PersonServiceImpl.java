package com.sourav.demo.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sourav.demo.dao.PersonDao;
import com.sourav.demo.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService{

	@Autowired
	@Qualifier(value = "fakePerson")
	private PersonDao personDao;

	@Override
	public String deletePersonById(String id) {
		if(personDao.deletePersonById(UUID.fromString(id)) > 0) 
			return "All record deleted";
		else
			return "record not present";
	}
}
