package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.demo.dao.Employee;
import com.example.demo.entity.EmployeeDAO;

@Controller
public class MainController {

	@Autowired
	private EmployeeDAO employeeDAO;
	
	public String index() {
		Iterable<Employee> records = employeeDAO.findAll();
		
		StringBuilder sb = new StringBuilder();
		records.forEach(emp -> sb.append(emp.getFirstName() + " " + emp.getLastName() + "<br>"));

		return sb.toString();
	}
}
