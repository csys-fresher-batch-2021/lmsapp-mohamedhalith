package in.mohamedhalith.validator;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestUsernameValidator {

	@Test
	public void testIsValidUsernameWithSevenCharacters() {
		String username = "user123";
		boolean result = UsernameValidator.isValidUsername(username);
		assertTrue(result);
	}

	@Test
	public void testIsValidUsernameWithSevenCharactersWithoutNumbers() {
		String username = "usernam";
		boolean result = UsernameValidator.isValidUsername(username);
		assertTrue(result);
	}

	@Test
	public void testIsValidUsernameWithMoreThanSevenCharacters() {
		String username = "username123";
		boolean result = UsernameValidator.isValidUsername(username);
		assertTrue(result);
	}

	@Test
	public void testIsValidUsernameWithLessThanSevenCharacters() {
		String username = "user";
		boolean result = UsernameValidator.isValidUsername(username);
		assertFalse(result);
	}

	@Test
	public void testIsValidUsernameWithInvalidUsernameMoreThanSeventCharacters() {
		String username = "username@123";
		boolean result = UsernameValidator.isValidUsername(username);
		assertFalse(result);
	}

	@Test
	public void testIsValidUsernameWithInvalidUsernameLessThanSevenCharacters() {
		String username = "user@1";
		boolean result = UsernameValidator.isValidUsername(username);
		assertFalse(result);
	}

	@Test
	public void testIsValidUsernameWithInvalidUsernameWithSevenCharacters() {
		String username = "user@123";
		boolean result = UsernameValidator.isValidUsername(username);
		assertFalse(result);
	}

}
