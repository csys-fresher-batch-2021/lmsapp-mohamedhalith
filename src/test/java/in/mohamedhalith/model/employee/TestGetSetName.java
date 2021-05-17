package in.mohamedhalith.model.employee;

import static org.junit.Assert.*;

import org.junit.Test;

import in.mohamedhalith.model.Employee;

public class TestGetSetName {
	Employee employee = new Employee();
	@Test
	public void testWithValidName() {
		String name = "Mohamed";
		employee.setName(name);
		String checkName = employee.getName();
		assertEquals(name,checkName);
	}
	
	@Test 
	public void testWithEmptyName() {
		try {
			String name = "";
			employee.setName(name);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Name cannot be empty or null",e.getMessage());
		}
	}
	@Test 
	public void testWithWhiteSpace() {
		try {
			String name = " ";
			employee.setName(name);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Name cannot be empty or null",e.getMessage());
		}
	}
	
	@Test 
	public void testWithNull() {
		try {
			String name = " ";
			employee.setName(name);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Name cannot be empty or null",e.getMessage());
		}
	}
}
