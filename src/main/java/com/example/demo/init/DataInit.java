package com.example.demo.init;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.example.demo.dao.Employee;
import com.example.demo.entity.EmployeeDAO;

@Component
public class DataInit implements ApplicationRunner {

	private EmployeeDAO employeeDAO;
	
	private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	public DataInit(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		long count = this.employeeDAO.count();
		
		if(count == 0) {
			Employee empOne = new Employee();
			Employee empTwo = new Employee();
			
			empOne.setFirstName("Sachin");
			empOne.setLastName("Tendulkar");
			
			Date dobOne = df.parse("1990-10-10");
			empOne.setDateOfBirth(dobOne);
			
			empTwo.setFirstName("Sanjay");
			empTwo.setLastName("Manjrekar");
			
			Date dobTwo = df.parse("1980-01-01");
			empTwo.setDateOfBirth(dobTwo);

			employeeDAO.save(empOne);
			employeeDAO.save(empTwo);
		}
	}

}
