package in.mohamedhalith.model.employee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import in.mohamedhalith.exception.ValidationException;
import in.mohamedhalith.model.Employee;

public class TestGetSetString {
	Employee employee = new Employee();

	// Test cases for GetSetName
	@Test
	public void testGetSetNameWithValidName() {
		try {
			String name = "Mohamed";
			employee.setName(name);
			String checkName = employee.getName();
			assertEquals(name, checkName);
		} catch (ValidationException e) {
			fail();
		}
	}

	@Test
	public void testGetSetNameWithEmptyName() {
		try {
			String name = "";
			employee.setName(name);
			fail();
		} catch (IllegalArgumentException | ValidationException e) {
			assertEquals("String should not be null or empty", e.getMessage());
		}
	}

	@Test
	public void testGetSetNameWithWhiteSpace() {
		try {
			String name = " ";
			employee.setName(name);
			fail();
		} catch (IllegalArgumentException | ValidationException e) {
			assertEquals("String should not be null or empty", e.getMessage());
		}
	}

	@Test
	public void testGetSetNameWithNull() {
		try {
			String name = " ";
			employee.setName(name);
			fail();
		} catch (IllegalArgumentException | ValidationException e) {
			assertEquals("String should not be null or empty", e.getMessage());
		}
	}

	// Test cases for GetSetPassword
	@Test
	public void testGetSetPasswordWithValidPassword() {
		try {
			String password = "pass1234";
			employee.setPassword(password);
			String checkPassword = employee.getPassword();
			assertEquals(password, checkPassword);
		} catch (ValidationException e) {
			fail();
		}
	}

	@Test
	public void testGetSetPasswordWithEmptyPassword() {
		try {
			String password = "";
			employee.setPassword(password);
			fail();
		} catch (IllegalArgumentException | ValidationException e) {
			assertEquals("String should not be null or empty", e.getMessage());
		}
	}

	@Test
	public void testGetSetPasswordWithWhiteSpaces() {
		try {
			String password = "";
			employee.setPassword(password);
			fail();
		} catch (IllegalArgumentException | ValidationException e) {
			assertEquals("String should not be null or empty", e.getMessage());
		}
	}

	@Test
	public void testGetSetPasswordWithNull() {
		try {
			String password = null;
			employee.setPassword(password);
			fail();
		} catch (IllegalArgumentException | ValidationException e) {
			assertEquals("String should not be null or empty", e.getMessage());
		}
	}

	// Test cases for GetSetUsername
	@Test
	public void testGetSetUsernameWithValidUsername() {
		try {
			String username = "username";
			employee.setUsername(username);
			String checkUsername = employee.getUsername();
			assertEquals(username, checkUsername);
		} catch (ValidationException e) {
			fail();
		}
	}

	@Test
	public void testGetSetUsernameWithEmptyUsername() {
		try {
			String username = "";
			employee.setUsername(username);
			fail();
		} catch (IllegalArgumentException | ValidationException e) {
			assertEquals("String should not be null or empty", e.getMessage());
		}
	}

	@Test
	public void testGetSetUsernameWithWhiteSpaces() {
		try {
			String username = "   ";
			employee.setUsername(username);
			fail();
		} catch (IllegalArgumentException | ValidationException e) {
			assertEquals("String should not be null or empty", e.getMessage());
		}
	}

	@Test
	public void testGetSetUsernameWithNull() {
		try {
			String username = null;
			employee.setUsername(username);
			fail();
		} catch (IllegalArgumentException | ValidationException e) {
			assertEquals("String should not be null or empty", e.getMessage());
		}
	}

	// Test cases for toString
	@Test
	public void testToString() {
		try {
			employee.setUsername("mohamed");
			employee.setPassword("password");
			employee.setName("Mohamed");
			employee.setId(2627);
			String result = employee.toString();
			assertEquals("Employee [Name Mohamed Id 2627 Username mohamed Password password]",result);
		} catch (ValidationException e) {
			fail();
		}
	}
}
