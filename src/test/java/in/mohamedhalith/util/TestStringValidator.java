package in.mohamedhalith.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestStringValidator {

	/**
	 * Test cases for isValidStringMethod
	 */
	@Test
	public void testIsValidStringWithEmptyString() {
		String word = "";
		boolean result = StringValidator.isValidString(word);
		assertFalse(result);
	}

	@Test
	public void testIsValidStringWithWhiteSpaces() {
		String word = "   ";
		boolean result = StringValidator.isValidString(word);
		assertFalse(result);
	}

	@Test
	public void testIsValidStringWithValidString() {
		String word = "mohamed";
		boolean result = StringValidator.isValidString(word);
		assertTrue(result);
	}

	/**
	 * Test cases for isValidPassword method
	 */
	@Test
	public void testIsValidPasswordWithEightCharacters() {
		String password = "pass1234";
		boolean result = StringValidator.isValidPassword(password);
		assertTrue(result);
	}

	@Test
	public void testIsValidPasswordWithEightCharactersWithoutNumbers() {
		String password = "password";
		boolean result = StringValidator.isValidPassword(password);
		assertTrue(result);
	}

	@Test
	public void testIsValidPasswordWithMoreThanEightCharacters() {
		String password = "password123";
		boolean result = StringValidator.isValidPassword(password);
		assertTrue(result);
	}

	@Test
	public void testIsValidPasswordWithLessThanEightCharacters() {
		String password = "pass";
		boolean result = StringValidator.isValidPassword(password);
		assertFalse(result);
	}

	@Test
	public void testIsValidPasswordWithInvalidPasswordMoreThanEightCharacters() {
		String password = "password@123";
		boolean result = StringValidator.isValidPassword(password);
		assertFalse(result);
	}

	@Test
	public void testIsValidPasswordWithInvalidPasswordLessThanEightCharacters() {
		String password = "pass@1";
		boolean result = StringValidator.isValidPassword(password);
		assertFalse(result);
	}

	@Test
	public void testIsValidPasswordWithInvalidPasswordWithEightCharacters() {
		String password = "pass@123";
		boolean result = StringValidator.isValidPassword(password);
		assertFalse(result);
	}

	/**
	 * Test cases for isValidUsername
	 */
	@Test
	public void testIsValidUsernameWithSevenCharacters() {
		String username = "user123";
		boolean result = StringValidator.isValidUsername(username);
		assertTrue(result);
	}

	@Test
	public void testIsValidUsernameWithSevenCharactersWithoutNumbers() {
		String username = "usernam";
		boolean result = StringValidator.isValidUsername(username);
		assertTrue(result);
	}

	@Test
	public void testIsValidUsernameWithMoreThanSevenCharacters() {
		String username = "username123";
		boolean result = StringValidator.isValidUsername(username);
		assertTrue(result);
	}

	@Test
	public void testIsValidUsernameWithLessThanSevenCharacters() {
		String username = "user";
		boolean result = StringValidator.isValidUsername(username);
		assertFalse(result);
	}

	@Test
	public void testIsValidUsernameWithInvalidUsernameMoreThanSeventCharacters() {
		String username = "username@123";
		boolean result = StringValidator.isValidUsername(username);
		assertFalse(result);
	}

	@Test
	public void testIsValidUsernameWithInvalidUsernameLessThanSevenCharacters() {
		String username = "user@1";
		boolean result = StringValidator.isValidUsername(username);
		assertFalse(result);
	}

	@Test
	public void testIsValidUsernameWithInvalidUsernameWithSevenCharacters() {
		String username = "user@123";
		boolean result = StringValidator.isValidUsername(username);
		assertFalse(result);
	}
}
