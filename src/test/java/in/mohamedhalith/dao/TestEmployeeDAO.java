package in.mohamedhalith.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import in.mohamedhalith.exception.DBException;
import in.mohamedhalith.model.Employee;

public class TestEmployeeDAO {
	
	EmployeeDAO employeeDAO = EmployeeDAO.getInstance();
	@Test
	public void testgetInstance() {
		EmployeeDAO employeeDAO = null;
		employeeDAO = EmployeeDAO.getInstance();
		if(employeeDAO == null) {
			fail();
		}
	}
	
	@Test 
	public void testGetEmployeeWithValidEmployee() {
		String username = "moha2627";
		try {
			Employee employee = employeeDAO.getEmployee(username);
			assertTrue(true);
		} catch (DBException e) {
			fail();
		}
	}
	
	@Test 
	public void testGetEmployeeWithInvalidEmployee() {
		String username = "mohamed";
		try {
			Employee employee = employeeDAO.getEmployee(username);
			fail();
		} catch (DBException e) {
			assertEquals("Employee Not found",e.getMessage());
		}
	}
	
	// Test cases for getEmployeeList

	@Test
	public void testGetEmployeeList() {
		List<Employee> employeeList = employeeDAO.getEmployeeList();
		assertEquals(2,employeeList.size());
	}
}
