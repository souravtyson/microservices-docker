package com.sourav.demo.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sourav.demo.dao.PersonDao;
import com.sourav.demo.model.Person;

@Repository(value = "fakePerson")
public class PersonDaoImpl implements PersonDao {

	private static List<Person> db = new ArrayList<>();

	private static final String INSERT_PERSON = "INSERT INTO person (id,name) VALUES(?,?)";
	private static final String SELECT_ALLPERSONS = "SELECT id, name FROM person";
	private static final String SELECT_PERSONBYID = "SELECT id, name FROM person where id = ?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Person> findPersons() {
		return jdbcTemplate.query(SELECT_ALLPERSONS, new BeanPropertyRowMapper(Person.class));
	}

	@Override
	public Optional<Person> selectPersonById(UUID id) {
		try {
			Person person = jdbcTemplate.queryForObject(SELECT_PERSONBYID, new Object[] { id },
					(rs, rowNum) -> new Person(UUID.fromString(rs.getString("id")), rs.getString("name")));
			return Optional.ofNullable(person);
		} catch (EmptyResultDataAccessException ex) {
			return null;
		}
	}

}
