package in.mohamedhalith.model.employee;

import static org.junit.Assert.*;

import org.junit.Test;

import in.mohamedhalith.model.Employee;

public class TestGetSetUsername {
	Employee employee = new Employee();
	
	@Test
	public void testWithValidUsername() {
		String username = "username";
		employee.setUsername(username);
		String checkUsername = employee.getUsername();
		assertEquals(username,checkUsername);
	}
	
	@Test
	public void testWithEmptyUsername() {
		try {
			String username="";
			employee.setUsername(username);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid Username",e.getMessage());
		}
	}
	
	@Test
	public void testWithWhiteSpaces() {
		try {
			String username="   ";
			employee.setUsername(username);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid Username",e.getMessage());
		}
	}
	
	@Test
	public void testWithNull() {
		try {
			String username=null;
			employee.setUsername(username);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid Username",e.getMessage());
		}
	}

}
