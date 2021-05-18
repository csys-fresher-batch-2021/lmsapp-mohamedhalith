package in.mohamedhalith.model.employee;

import static org.junit.Assert.*;

import org.junit.Test;

import in.mohamedhalith.model.Employee;

public class TestGetSetEarnedLeave {

	Employee employee = new Employee();

	@Test
	public void testWithValidNumberOfEarnedLeave() {
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
	public void testWithNegativeNumberOfEarnedLeaves() {
		try {
			int earnedLeave = -1;
			employee.setEarnedLeave(earnedLeave);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid no. of earned leaves", e.getMessage());
		}
	}

	@Test
	public void testWithExcessNumberOfEarnedLeaves() {
		try {
			int earnedLeave = 4;
			employee.setEarnedLeave(earnedLeave);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid no. of earned leaves", e.getMessage());
		}
	}

}
