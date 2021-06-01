package in.mohamedhalith.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import in.mohamedhalith.exception.DBException;
import in.mohamedhalith.exception.ValidationException;
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
		int employeeId = 2627 ;
		try {
			Employee employee = employeeDAO.findByEmployeeId(employeeId);
			assertTrue(true);
		} catch (DBException e) {
			fail();
		}
	}
	
	@Test 
	public void testGetEmployeeWithInvalidEmployee() {
		int employeeId = 262722;
		try {
			Employee employee = employeeDAO.findByEmployeeId(employeeId);
			fail();
		} catch (DBException e) {
			assertEquals("Failed to get employee",e.getMessage());
		}
	}
	
	// Test cases for getEmployeeList

	@Test
	public void testGetEmployeeList() {
		try {
			List<Employee> employeeList = employeeDAO.findAll();
			assertEquals(2,employeeList.size());
		} catch (DBException e) {
			fail();
		}
	}
}
