package in.mohamedhalith.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import in.mohamedhalith.model.Employee;
import in.mohamedhalith.service.EmployeeManager;

public class TestDisplayEmployees {

	@Test
	public void testGetEmployeeList() {
		List<Employee> employeeList = EmployeeManager.getEmployeeList();
		assertEquals(2,employeeList.size());
	}

}
