package in.mohamedhalith.model.employee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import in.mohamedhalith.model.Employee;

public class TestGetSetInt {
	Employee employee = new Employee();

	// Test cases for GetSetId
	@Test
	public void testGetSetIdWithValidId() {
		int id = 2627;
		employee.setId(id);
		int checkId = employee.getId();
		assertEquals(2627, checkId);
	}

	@Test
	public void testGetSetIdWithNegativeId() {
		try {
			int id = -22;
			employee.setId(id);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Employee Id must be a 4-digit Positive number", e.getMessage());
		}
	}

	@Test
	public void testGetSetIdWithOneDigitId() {
		try {
			int id = 4;
			employee.setId(id);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Employee Id must be a 4-digit Positive number", e.getMessage());
		}
	}

	@Test
	public void testGetSetIdWithTwoDigitId() {
		try {
			int id = 22;
			employee.setId(id);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Employee Id must be a 4-digit Positive number", e.getMessage());
		}
	}

	@Test
	public void testGetSetIdWithThreeDigitId() {
		try {
			int id = 123;
			employee.setId(id);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Employee Id must be a 4-digit Positive number", e.getMessage());
		}
	}

	@Test
	public void testGetSetIdWithMoreThanFourDigitId() {
		try {
			int id = 123456789;
			employee.setId(id);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Employee Id must be a 4-digit Positive number", e.getMessage());
		}
	}

	// Test cases for GetSetSickLeave
	@Test
	public void testGetSetSickLeaveWithValidNumberOfSickLeave() {
		int sickLeave = 3;
		employee.setSickLeave(sickLeave);
		int checkSickLeave = employee.getSickLeave();
		assertEquals(checkSickLeave, sickLeave);
		sickLeave = 2;
		employee.setSickLeave(sickLeave);
		checkSickLeave = employee.getSickLeave();
		assertEquals(checkSickLeave, sickLeave);
		sickLeave = 1;
		employee.setSickLeave(sickLeave);
		checkSickLeave = employee.getSickLeave();
		assertEquals(checkSickLeave, sickLeave);
		sickLeave = 0;
		employee.setSickLeave(sickLeave);
		checkSickLeave = employee.getSickLeave();
		assertEquals(checkSickLeave, sickLeave);
	}

	@Test
	public void testGetSetSickLeaveWithNegativeNumberOfSickLeaves() {
		try {
			int sickLeave = -1;
			employee.setSickLeave(sickLeave);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid no. of sick leaves", e.getMessage());
		}
	}

	@Test
	public void testGetSetSickLeaveWithExcessNumberOfSickLeaves() {
		try {
			int sickLeave = 4;
			employee.setSickLeave(sickLeave);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid no. of sick leaves", e.getMessage());
		}
	}

	// Test cases for GetSetCasualLeave
	@Test
	public void testGetSetCasualLeaveWithValidNumberOfCasualLeave() {
		int casualLeave = 3;
		employee.setCasualLeave(casualLeave);
		int checkCasualLeave = employee.getCasualLeave();
		assertEquals(checkCasualLeave, casualLeave);
		casualLeave = 2;
		employee.setCasualLeave(casualLeave);
		checkCasualLeave = employee.getCasualLeave();
		assertEquals(checkCasualLeave, casualLeave);
		casualLeave = 1;
		employee.setCasualLeave(casualLeave);
		checkCasualLeave = employee.getCasualLeave();
		assertEquals(checkCasualLeave, casualLeave);
		casualLeave = 0;
		employee.setCasualLeave(casualLeave);
		checkCasualLeave = employee.getCasualLeave();
		assertEquals(checkCasualLeave, casualLeave);
	}

	@Test
	public void testGetSetCasualLeaveWithNegativeNumberOfCasualLeaves() {
		try {
			int casualLeave = -1;
			employee.setCasualLeave(casualLeave);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid no. of casual leaves", e.getMessage());
		}
	}

	@Test
	public void testGetSetCasualLeaveWithExcessNumberOfCasualLeaves() {
		try {
			int casualLeave = 4;
			employee.setCasualLeave(casualLeave);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid no. of casual leaves", e.getMessage());
		}
	}

	// Test cases for GetSetEarnedLeave
	@Test
	public void testGetSetEarnedLeaveWithValidNumberOfEarnedLeave() {
		int earnedLeave = 1;
		employee.setEarnedLeave(earnedLeave);
		int checkEarnedLeave = employee.getEarnedLeave();
		assertEquals(checkEarnedLeave, earnedLeave);
		earnedLeave = 0;
		employee.setEarnedLeave(earnedLeave);
		checkEarnedLeave = employee.getEarnedLeave();
		assertEquals(checkEarnedLeave, earnedLeave);
	}

	@Test
	public void testGetSetEarnedLeaveWithNegativeNumberOfEarnedLeaves() {
		try {
			int earnedLeave = -1;
			employee.setEarnedLeave(earnedLeave);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid no. of earned leaves", e.getMessage());
		}
	}

	@Test
	public void testGetSetEarnedLeaveWithExcessNumberOfEarnedLeaves() {
		try {
			int earnedLeave = 4;
			employee.setEarnedLeave(earnedLeave);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid no. of earned leaves", e.getMessage());
		}
	}
}
