package in.mohamedhalith.model.employee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import in.mohamedhalith.model.Employee;
import in.mohamedhalith.model.LeaveBalance;

public class TestGetSetInt {
	Employee employee = new Employee();
	LeaveBalance leavebalance = new LeaveBalance();

	// Test cases for GetSetId
	@Test
	public void testGetSetIdWithValidId() {
		int id = 2627;
		employee.setEmployeeId(id);
		int checkId = employee.getEmployeeId();
		assertEquals(2627, checkId);
	}

	@Test
	public void testGetSetIdWithNegativeId() {
		try {
			int id = -22;
			employee.setEmployeeId(id);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Employee Id must be a 4-digit Positive number", e.getMessage());
		}
	}

	@Test
	public void testGetSetIdWithOneDigitId() {
		try {
			int id = 4;
			employee.setEmployeeId(id);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Employee Id must be a 4-digit Positive number", e.getMessage());
		}
	}

	@Test
	public void testGetSetIdWithTwoDigitId() {
		try {
			int id = 22;
			employee.setEmployeeId(id);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Employee Id must be a 4-digit Positive number", e.getMessage());
		}
	}

	@Test
	public void testGetSetIdWithThreeDigitId() {
		try {
			int id = 123;
			employee.setEmployeeId(id);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Employee Id must be a 4-digit Positive number", e.getMessage());
		}
	}

	@Test
	public void testGetSetIdWithMoreThanFourDigitId() {
		try {
			int id = 123456789;
			employee.setEmployeeId(id);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Employee Id must be a 4-digit Positive number", e.getMessage());
		}
	}

	// Test cases for GetSetSickLeave
	@Test
	public void testGetSetSickLeaveWithValidNumberOfSickLeave() {
		int sickLeave = 3;
		leavebalance.setSickLeave(sickLeave);
		int checkSickLeave = leavebalance.getSickLeave();
		assertEquals(checkSickLeave, sickLeave);
		sickLeave = 2;
		leavebalance.setSickLeave(sickLeave);
		checkSickLeave = leavebalance.getSickLeave();
		assertEquals(checkSickLeave, sickLeave);
		sickLeave = 1;
		leavebalance.setSickLeave(sickLeave);
		checkSickLeave = leavebalance.getSickLeave();
		assertEquals(checkSickLeave, sickLeave);
		sickLeave = 0;
		leavebalance.setSickLeave(sickLeave);
		checkSickLeave = leavebalance.getSickLeave();
		assertEquals(checkSickLeave, sickLeave);
	}

	@Test
	public void testGetSetSickLeaveWithNegativeNumberOfSickLeaves() {
		try {
			int sickLeave = -1;
			leavebalance.setSickLeave(sickLeave);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid no. of sick leaves", e.getMessage());
		}
	}

	@Test
	public void testGetSetSickLeaveWithExcessNumberOfSickLeaves() {
		try {
			int sickLeave = 4;
			leavebalance.setSickLeave(sickLeave);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid no. of sick leaves", e.getMessage());
		}
	}

	// Test cases for GetSetCasualLeave
	@Test
	public void testGetSetCasualLeaveWithValidNumberOfCasualLeave() {
		int casualLeave = 3;
		leavebalance.setCasualLeave(casualLeave);
		int checkCasualLeave = leavebalance.getCasualLeave();
		assertEquals(checkCasualLeave, casualLeave);
		casualLeave = 2;
		leavebalance.setCasualLeave(casualLeave);
		checkCasualLeave = leavebalance.getCasualLeave();
		assertEquals(checkCasualLeave, casualLeave);
		casualLeave = 1;
		leavebalance.setCasualLeave(casualLeave);
		checkCasualLeave = leavebalance.getCasualLeave();
		assertEquals(checkCasualLeave, casualLeave);
		casualLeave = 0;
		leavebalance.setCasualLeave(casualLeave);
		checkCasualLeave = leavebalance.getCasualLeave();
		assertEquals(checkCasualLeave, casualLeave);
	}

	@Test
	public void testGetSetCasualLeaveWithNegativeNumberOfCasualLeaves() {
		try {
			int casualLeave = -1;
			leavebalance.setCasualLeave(casualLeave);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid no. of casual leaves", e.getMessage());
		}
	}

	@Test
	public void testGetSetCasualLeaveWithExcessNumberOfCasualLeaves() {
		try {
			int casualLeave = 4;
			leavebalance.setCasualLeave(casualLeave);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid no. of casual leaves", e.getMessage());
		}
	}

	// Test cases for GetSetEarnedLeave
	@Test
	public void testGetSetEarnedLeaveWithValidNumberOfEarnedLeave() {
		int earnedLeave = 1;
		leavebalance.setEarnedLeave(earnedLeave);
		int checkEarnedLeave = leavebalance.getEarnedLeave();
		assertEquals(checkEarnedLeave, earnedLeave);
		earnedLeave = 0;
		leavebalance.setEarnedLeave(earnedLeave);
		checkEarnedLeave = leavebalance.getEarnedLeave();
		assertEquals(checkEarnedLeave, earnedLeave);
	}

	@Test
	public void testGetSetEarnedLeaveWithNegativeNumberOfEarnedLeaves() {
		try {
			int earnedLeave = -1;
			leavebalance.setEarnedLeave(earnedLeave);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid no. of earned leaves", e.getMessage());
		}
	}

	@Test
	public void testGetSetEarnedLeaveWithExcessNumberOfEarnedLeaves() {
		try {
			int earnedLeave = 4;
			leavebalance.setEarnedLeave(earnedLeave);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid no. of earned leaves", e.getMessage());
		}
	}
}
