package com.sourav.transform.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sourav.transform.model.Employee;

@RestController
public class ConversionController {

	@RequestMapping(value = "/xmltoobject", method = RequestMethod.POST, consumes = { "application/xml" }, produces = {
			"text/plain" })
	public String convertToObject(@RequestBody Employee emp) {
		String details = emp.getDesignation() +"  "+emp.getEmpId();
		return details;
    }
}