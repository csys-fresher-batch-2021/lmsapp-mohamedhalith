package in.mohamedhalith.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import in.mohamedhalith.exception.ServiceException;
import in.mohamedhalith.exception.ValidationException;
import in.mohamedhalith.model.Employee;

public class TestEmployeeManager {

	@Test
	public void testGetEmployeeList() {
		try {
			List<Employee> employeeList = EmployeeManager.getEmployeeList();
			assertEquals(2, employeeList.size());
		} catch (ServiceException | ValidationException e) {
			fail();
		}
	}

	// Test cases for GetEmployee
	@Test
	public void testGetEmployeeWithFirstEmployee() {
		try {
			String username = "moha2627";
			Employee employee = EmployeeManager.getEmployee(username);
			assertEquals("Mohamed", employee.getName());
			assertEquals("2627moha", employee.getPassword());
			assertEquals(2, employee.getSickLeave());
			assertEquals(2, employee.getCasualLeave());
			assertEquals(1, employee.getEarnedLeave());
		} catch (ServiceException | ValidationException e) {
			fail();
		}
	}

	@Test
	public void testGetEmployeeWithSecondEmployee() {
		try {
			String username = "hali2628";
			Employee employee = EmployeeManager.getEmployee(username);
			assertEquals("Halith", employee.getName());
			assertEquals("2628hali", employee.getPassword());
			assertEquals(2, employee.getSickLeave());
			assertEquals(2, employee.getCasualLeave());
			assertEquals(1, employee.getEarnedLeave());
		} catch (ServiceException | ValidationException e) {
			fail();
		}
	}

	@Test
	public void testGetEmployeeWithInvalidEmployee() {
		try {
			String username = "halith";
			EmployeeManager.getEmployee(username);
			fail();
		} catch (ServiceException | ValidationException e) {
			assertEquals("Invalid username", e.getMessage());
		}
	}
}
