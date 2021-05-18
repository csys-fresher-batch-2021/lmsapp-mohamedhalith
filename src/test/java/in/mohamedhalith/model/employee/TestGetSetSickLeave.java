package in.mohamedhalith.model.employee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import in.mohamedhalith.model.Employee;

public class TestGetSetSickLeave {
	Employee employee = new Employee();

	@Test
	public void testWithValidNumberOfSickLeave() {
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
	public void testWithNegativeNumberOfSickLeaves() {
		try {
			int sickLeave = -1;
			employee.setSickLeave(sickLeave);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid no. of sick leaves", e.getMessage());
		}
	}

	@Test
	public void testWithExcessNumberOfSickLeaves() {
		try {
			int sickLeave = 4;
			employee.setSickLeave(sickLeave);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid no. of sick leaves", e.getMessage());
		}
	}

}
