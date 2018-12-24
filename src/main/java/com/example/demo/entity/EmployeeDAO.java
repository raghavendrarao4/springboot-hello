package com.example.demo.entity;

import java.util.Date;
import java.util.List;

import com.example.demo.dao.Employee;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDAO extends CrudRepository<Employee, Long> {

	public List<Employee> findByFirstNameLike(String firstName);
	public List<Employee> findByDateOfBirthGreaterThan(Date date);
}
