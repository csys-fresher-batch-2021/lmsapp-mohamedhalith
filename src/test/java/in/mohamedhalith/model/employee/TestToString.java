package in.mohamedhalith.model.employee;

import static org.junit.Assert.*;

import org.junit.Test;

import in.mohamedhalith.model.Employee;

public class TestToString {
	Employee employee = new Employee();
	@Test
	public void testToString() {
		employee.setUsername("mohamed");
		employee.setPassword("password");
		employee.setName("Mohamed");
		employee.setId(2627);
		employee.setSickLeave(3);
		employee.setCasualLeave(3);
		employee.setEarnedLeave(1);
		String result = employee.toString();
		assertEquals(result, "Employee [Name Mohamed Id 2627 Username mohamed Password password"
				+ " Sick Leave 3 Casual Leave 3 Earned Leave 1]");
	}

}
