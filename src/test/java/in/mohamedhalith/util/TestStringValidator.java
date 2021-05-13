package in.mohamedhalith.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestStringValidator {

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

}
