package in.mohamedhalith.model.employee;

import static org.junit.Assert.*;

import org.junit.Test;

import in.mohamedhalith.model.Employee;

public class TestGetSetPassword {
	Employee employee = new Employee();

	@Test
	public void testWithValidPassword() {
		String password = "pass1234";
		employee.setUsername(password);
		String checkPassword = employee.getUsername();
		assertEquals(password, checkPassword);
	}
	
	@Test
	public void testWithEmptyPassword() {
		try {
			String password = "";
			employee.setPassword(password);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid Password",e.getMessage());
		}
	}
	
	@Test
	public void testWithWhiteSpaces() {
		try {
			String password = "";
			employee.setPassword(password);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid Password",e.getMessage());
		}
	}
	
	@Test
	public void testWithNull() {
		try {
			String password = null;
			employee.setPassword(password);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid Password",e.getMessage());
		}
	}

}
