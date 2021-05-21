package in.mohamedhalith.util;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import in.mohamedhalith.exception.ValidationException;

public class TestDateTimeValidator {

	@Test
	public void testIsValidWithValidDate() {
		LocalDate date = LocalDate.parse("2022-06-01");
		try {
			DateTimeValidator.isValidDate(date);
			assertTrue(true);
		} catch (ValidationException e) {
			fail();
		}
	}
	
	@Test
	public void testIsValidWithInvalidDate() {
		LocalDate date = LocalDate.parse("2000-06-01");
		try {
			DateTimeValidator.isValidDate(date);
			fail();
		} catch (ValidationException e) {
			assertEquals("Date cannot be past",e.getMessage());
		}
	}

}
