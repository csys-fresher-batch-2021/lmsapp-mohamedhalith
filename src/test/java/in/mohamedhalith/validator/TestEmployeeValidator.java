package in.mohamedhalith.validator;

import static org.junit.Assert.*;

import org.junit.Test;

import in.mohamedhalith.exception.ValidationException;

public class TestEmployeeValidator {

	@Test
	public void testWithValidDetails() {
		String username = "moha2627";
		try {
			EmployeeValidator.isEmployee(username);
			assertTrue(true);
		} catch (ValidationException e) {
			fail();
		}
	}
	
	@Test
	public void testWithIncorrectUsername() {
		String username = "mohamed";
		try {
			EmployeeValidator.isEmployee(username);
			fail();
		} catch (ValidationException e) {
			assertEquals("Employee Not found",e.getMessage());
		}
	}
	
	@Test
	public void testWithInvalidUsername() {
		String username = "moha";
		try {
			EmployeeValidator.isEmployee(username);
			fail();
		} catch (ValidationException e) {
			assertEquals("Invalid username",e.getMessage());
		}
	}

}
