package in.mohamedhalith.model.employee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import in.mohamedhalith.model.Employee;

public class TestGetSetId {
	Employee employee = new Employee();
	
	@Test
	public void testWithValidId() {
		int id = 2627;
		employee.setId(id);
		int checkId = employee.getId();
		assertEquals(2627,checkId);
	}
	
	@Test
	public void testWithNegativeId() {
		try {
			int id = -22;
			employee.setId(id);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Employee Id must be a 4-digit Positive number",e.getMessage());
		}
	}
	
	@Test
	public void testWithOneDigitId() {
		try {
			int id = 4;
			employee.setId(id);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Employee Id must be a 4-digit Positive number",e.getMessage());
		}
	}
	
	@Test
	public void testWithTwoDigitId() {
		try {
			int id = 22;
			employee.setId(id);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Employee Id must be a 4-digit Positive number",e.getMessage());
		}
	}
	
	
	@Test
	public void testWithThreeDigitId() {
		try {
			int id = 123;
			employee.setId(id);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Employee Id must be a 4-digit Positive number",e.getMessage());
		}
	}
	
	@Test
	public void testWithMoreThanFourDigitId() {
		try {
			int id = 123456789;
			employee.setId(id);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Employee Id must be a 4-digit Positive number",e.getMessage());
		}
	}
}
