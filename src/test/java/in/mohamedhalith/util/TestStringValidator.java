package in.mohamedhalith.util;

import static org.junit.Assert.*;

import org.junit.Test;

import in.mohamedhalith.exception.ValidationException;

public class TestStringValidator {

	/**
	 * Test cases for isValidStringMethod
	 */
	@Test
	public void testIsValidStringWithEmptyString() {
		try {
			String word = "";
			StringValidator.isValidString(word);
			fail();
		} catch (ValidationException e) {
			assertEquals("String should not be null or empty", e.getMessage());
		}
	}

	@Test
	public void testIsValidStringWithWhiteSpaces() {
		try {
			String word = "   ";
			StringValidator.isValidString(word);
			fail();
		} catch (ValidationException e) {
			assertEquals("String should not be null or empty",e.getMessage());
		}
	}

	@Test
	public void testIsValidStringWithValidString() {
		try {
			String word = "mohamed";
			StringValidator.isValidString(word);
			assertTrue(true);
		} catch (ValidationException e) {
			fail();
		}
	}

	/**
	 * Test cases for isValidPassword method
	 */
	@Test
	public void testIsValidPasswordWithEightCharacters() {
		try {
			String password = "pass1234";
			StringValidator.isValidPassword(password);
			assertTrue(true);
		} catch (ValidationException e) {
			fail();
		}
	}

	@Test
	public void testIsValidPasswordWithEightCharactersWithoutNumbers() {
		try {
			String password = "password";
			StringValidator.isValidPassword(password);
			assertTrue(true);
		} catch (ValidationException e) {
			fail();
		}
	}

	@Test
	public void testIsValidPasswordWithMoreThanEightCharacters() {
		try {
			String password = "password123";
			StringValidator.isValidPassword(password);
			assertTrue(true);
		} catch (ValidationException e) {
			fail();
		}
	}

	@Test
	public void testIsValidPasswordWithLessThanEightCharacters() {
		try {
			String password = "pass";
			StringValidator.isValidPassword(password);
			fail();
		} catch (ValidationException e) {
			assertEquals("Given password is invalid", e.getMessage());
		}
	}

	@Test
	public void testIsValidPasswordWithInvalidPasswordMoreThanEightCharacters() {
		try {
			String password = "password@123";
			StringValidator.isValidPassword(password);
			fail();
		} catch (ValidationException e) {
			assertEquals("Given password is invalid", e.getMessage());
		}
	}

	@Test
	public void testIsValidPasswordWithInvalidPasswordLessThanEightCharacters() {
		try {
			String password = "pass@1";
			StringValidator.isValidPassword(password);
			fail();
		} catch (ValidationException e) {
			assertEquals("Given password is invalid", e.getMessage());
		}
	}

	@Test
	public void testIsValidPasswordWithInvalidPasswordWithEightCharacters() {
		try {
			String password = "pass@123";
			StringValidator.isValidPassword(password);
			fail();
		} catch (ValidationException e) {
			assertEquals("Given password is invalid", e.getMessage());
		}
	}

	/**
	 * Test cases for isValidUsername
	 */
	@Test
	public void testIsValidUsernameWithSevenCharacters() {
		try {
			String username = "user123";
			StringValidator.isValidUsername(username);
			assertTrue(true);
		} catch (ValidationException e) {
			fail();
		}
	}

	@Test
	public void testIsValidUsernameWithSevenCharactersWithoutNumbers() {
		try {
			String username = "usernam";
			StringValidator.isValidUsername(username);
			assertTrue(true);
		} catch (ValidationException e) {
			fail();
		}
	}

	@Test
	public void testIsValidUsernameWithMoreThanSevenCharacters() {
		try {
			String username = "username123";
			StringValidator.isValidUsername(username);
			assertTrue(true);
		} catch (ValidationException e) {
			fail();
		}
	}

	@Test
	public void testIsValidUsernameWithLessThanSevenCharacters() {
		try {
			String username = "user";
			StringValidator.isValidUsername(username);
			fail();
		} catch (ValidationException e) {
			assertEquals("Invalid username", e.getMessage());
		}
	}

	@Test
	public void testIsValidUsernameWithInvalidUsernameMoreThanSeventCharacters() {
		try {
			String username = "username@123";
			StringValidator.isValidUsername(username);
			fail();
		} catch (ValidationException e) {
			assertEquals("Invalid username", e.getMessage());
		}
	}

	@Test
	public void testIsValidUsernameWithInvalidUsernameLessThanSevenCharacters() {
		try {
			String username = "user@1";
			StringValidator.isValidUsername(username);
			fail();
		} catch (ValidationException e) {
			assertEquals("Invalid username", e.getMessage());
		}
	}

	@Test
	public void testIsValidUsernameWithInvalidUsernameWithSevenCharacters() {
		try {
			String username = "user@123";
			StringValidator.isValidUsername(username);
			fail();
		} catch (ValidationException e) {
			assertEquals("Invalid username", e.getMessage());
		}
	}
}
