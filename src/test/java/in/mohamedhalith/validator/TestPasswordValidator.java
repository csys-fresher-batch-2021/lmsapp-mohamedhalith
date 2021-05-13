package in.mohamedhalith.validator;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestPasswordValidator {

	@Test
	public void testIsValidPasswordWithEightCharacters() {
		String password = "pass1234";
		boolean result = PasswordValidator.isValidPassword(password);
		assertTrue(result);
	}

	@Test
	public void testIsValidPasswordWithEightCharactersWithoutNumbers() {
		String password = "password";
		boolean result = PasswordValidator.isValidPassword(password);
		assertTrue(result);
	}

	@Test
	public void testIsValidPasswordWithMoreThanEightCharacters() {
		String password = "password123";
		boolean result = PasswordValidator.isValidPassword(password);
		assertTrue(result);
	}

	@Test
	public void testIsValidPasswordWithLessThanEightCharacters() {
		String password = "pass";
		boolean result = PasswordValidator.isValidPassword(password);
		assertFalse(result);
	}

	@Test
	public void testIsValidPasswordWithInvalidPasswordMoreThanEightCharacters() {
		String password = "password@123";
		boolean result = PasswordValidator.isValidPassword(password);
		assertFalse(result);
	}

	@Test
	public void testIsValidPasswordWithInvalidPasswordLessThanEightCharacters() {
		String password = "pass@1";
		boolean result = PasswordValidator.isValidPassword(password);
		assertFalse(result);
	}

	@Test
	public void testIsValidPasswordWithInvalidPasswordWithEightCharacters() {
		String password = "pass@123";
		boolean result = PasswordValidator.isValidPassword(password);
		assertFalse(result);
	}

}
