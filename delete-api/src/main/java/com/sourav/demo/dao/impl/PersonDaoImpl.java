package com.sourav.demo.dao.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sourav.demo.dao.PersonDao;

@Repository(value = "fakePerson")
public class PersonDaoImpl implements PersonDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private static final String DELETE_QUERY="DELETE FROM person WHERE id=?";
	
	@Override
	public int deletePersonById(UUID id) {
		return jdbcTemplate.update(DELETE_QUERY, new Object[] {id});
	}

}
