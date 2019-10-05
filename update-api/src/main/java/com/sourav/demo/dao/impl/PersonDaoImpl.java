package com.sourav.demo.dao.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sourav.demo.dao.PersonDao;
import com.sourav.demo.model.Person;

@Repository(value = "fakePerson")
public class PersonDaoImpl implements PersonDao {

	private static final String UPDATE_PERSON = "UPDATE person SET name = ? where id = ?";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void updatePersonById(Person person, UUID id) {
		jdbcTemplate.update(UPDATE_PERSON, new Object[] {person.getName(),id});
		
	}

}
