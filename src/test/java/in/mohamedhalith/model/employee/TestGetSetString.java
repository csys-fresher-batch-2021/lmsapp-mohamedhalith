package in.mohamedhalith.model.employee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import in.mohamedhalith.model.Employee;

public class TestGetSetString {
	Employee employee = new Employee();

	// Test cases for GetSetName
	@Test
	public void testGetSetNameWithValidName() {
		String name = "Mohamed";
		employee.setName(name);
		String checkName = employee.getName();
		assertEquals(name, checkName);
	}

	@Test
	public void testGetSetNameWithEmptyName() {
		try {
			String name = "";
			employee.setName(name);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Name cannot be empty or null", e.getMessage());
		}
	}

	@Test
	public void testGetSetNameWithWhiteSpace() {
		try {
			String name = " ";
			employee.setName(name);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Name cannot be empty or null", e.getMessage());
		}
	}

	@Test
	public void testGetSetNameWithNull() {
		try {
			String name = " ";
			employee.setName(name);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Name cannot be empty or null", e.getMessage());
		}
	}

	// Test cases for GetSetPassword
	@Test
	public void testGetSetPasswordWithValidPassword() {
		String password = "pass1234";
		employee.setPassword(password);
		String checkPassword = employee.getPassword();
		assertEquals(password, checkPassword);
	}

	@Test
	public void testGetSetPasswordWithEmptyPassword() {
		try {
			String password = "";
			employee.setPassword(password);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid Password", e.getMessage());
		}
	}

	@Test
	public void testGetSetPasswordWithWhiteSpaces() {
		try {
			String password = "";
			employee.setPassword(password);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid Password", e.getMessage());
		}
	}

	@Test
	public void testGetSetPasswordWithNull() {
		try {
			String password = null;
			employee.setPassword(password);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid Password", e.getMessage());
		}
	}

	// Test cases for GetSetUsername
	@Test
	public void testGetSetUsernameWithValidUsername() {
		String username = "username";
		employee.setUsername(username);
		String checkUsername = employee.getUsername();
		assertEquals(username, checkUsername);
	}

	@Test
	public void testGetSetUsernameWithEmptyUsername() {
		try {
			String username = "";
			employee.setUsername(username);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid Username", e.getMessage());
		}
	}

	@Test
	public void testGetSetUsernameWithWhiteSpaces() {
		try {
			String username = "   ";
			employee.setUsername(username);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid Username", e.getMessage());
		}
	}

	@Test
	public void testGetSetUsernameWithNull() {
		try {
			String username = null;
			employee.setUsername(username);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid Username", e.getMessage());
		}
	}

	// Test cases for toString
	@Test
	public void testToString() {
		employee.setUsername("mohamed");
		employee.setPassword("password");
		employee.setName("Mohamed");
		employee.setId(2627);
		employee.setSickLeave(3);
		employee.setCasualLeave(3);
		employee.setEarnedLeave(1);
		String result = employee.toString();
		assertEquals(result, "Employee [Name Mohamed Id 2627 Username mohamed Password password"
				+ " Sick Leave 3 Casual Leave 3 Earned Leave 1]");
	}
}
