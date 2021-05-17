package in.mohamedhalith.model.employee;

import static org.junit.Assert.*;

import org.junit.Test;

import in.mohamedhalith.model.Employee;

public class TestGetSetCasualLeave {

	Employee employee = new Employee();

	@Test
	public void testWithValidNumberOfCasualLeave() {
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
	public void testWithNegativeNumberOfCasualLeaves() {
		try {
			int casualLeave = -1;
			employee.setCasualLeave(casualLeave);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid no. of casual leaves", e.getMessage());
		}
	}

	@Test
	public void testWithExcessNumberOfCasualLeaves() {
		try {
			int casualLeave = 4;
			employee.setCasualLeave(casualLeave);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid no. of casual leaves", e.getMessage());
		}
	}

}
