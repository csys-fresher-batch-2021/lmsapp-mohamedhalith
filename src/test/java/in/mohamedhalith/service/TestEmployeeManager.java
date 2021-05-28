package in.mohamedhalith.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import in.mohamedhalith.exception.ServiceException;
import in.mohamedhalith.exception.ValidationException;
import in.mohamedhalith.model.Employee;

public class TestEmployeeManager {

	@Test
	public void testGetEmployeeList() {
		try {
			List<Employee> employeeList = EmployeeService.getEmployeeList();
			assertEquals(2, employeeList.size());
		} catch (ServiceException | ValidationException e) {
			fail();
		}
	}

	// Test cases for GetEmployee
	@Test
	public void testGetEmployeeWithFirstEmployee() {
		try {
			int employeeId = 2627;
			Employee employee = EmployeeService.getEmployee(employeeId);
			assertEquals("Mohamed", employee.getName());
			assertEquals("2627moha", employee.getPassword());
		} catch (ServiceException | ValidationException e) {
			fail();
		}
	}

	@Test
	public void testGetEmployeeWithSecondEmployee() {
		try {
			int employeeId = 2627;
			Employee employee = EmployeeService.getEmployee(employeeId);
			assertEquals("Halith", employee.getName());
			assertEquals("2628hali", employee.getPassword());
		} catch (ServiceException | ValidationException e) {
			fail();
		}
	}

	@Test
	public void testGetEmployeeWithInvalidEmployee() {
		try {
			int employeeId = 26726;
			Employee employee = EmployeeService.getEmployee(employeeId);
			fail();
		} catch (ServiceException | ValidationException e) {
			assertEquals("Invalid username", e.getMessage());
		}
	}
}
