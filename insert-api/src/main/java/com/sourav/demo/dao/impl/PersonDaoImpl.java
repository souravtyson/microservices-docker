package com.sourav.demo.dao.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sourav.demo.dao.PersonDao;
import com.sourav.demo.model.Person;

@Repository(value = "fakePerson")
public class PersonDaoImpl implements PersonDao {

	private static final String INSERT_PERSON = "INSERT INTO person (id,name) VALUES(?,?)";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int insertPerson(UUID id, Person person) {
		return jdbcTemplate.update(INSERT_PERSON, new Object[] { id, person.getName() });
	}

}
