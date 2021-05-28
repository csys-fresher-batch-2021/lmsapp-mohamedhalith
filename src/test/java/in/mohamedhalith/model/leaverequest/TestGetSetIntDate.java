package in.mohamedhalith.model.leaverequest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDate;

import org.junit.Test;

import in.mohamedhalith.model.Employee;
import in.mohamedhalith.model.LeaveRequest;

public class TestGetSetIntDate {
	LeaveRequest leaveRequest = new LeaveRequest();
	Employee employee = new Employee();

	@Test
	public void testGetSetEmployeeIdWithValidId() {
		int id = 2627;
		employee.setEmployeeId(id);
		leaveRequest.setEmployee(employee);
		int checkId = leaveRequest.getEmployee().getEmployeeId();
		assertEquals(2627, checkId);
	}

	@Test
	public void testGetSetEmployeeIdWithNegativeId() {
		try {
			int id = -22;
			employee.setEmployeeId(id);
			fail();
		} catch (RuntimeException e) {
			assertEquals("Employee Id must be a 4-digit Positive number", e.getMessage());
		}
	}

	@Test
	public void testGetSetEmployeeIdWithOneDigitId() {
		try {
			int id = 4;
			employee.setEmployeeId(id);
			fail();
		} catch (RuntimeException e) {
			assertEquals("Employee Id must be a 4-digit Positive number", e.getMessage());
		}
	}

	@Test
	public void testGetSetEmployeeIdWithTwoDigitId() {
		try {
			int id = 22;
			employee.setEmployeeId(id);
			fail();
		} catch (RuntimeException e) {
			assertEquals("Employee Id must be a 4-digit Positive number", e.getMessage());
		}
	}

	@Test
	public void testGetSetEmployeeIdWithThreeDigitId() {
		try {
			int id = 123;
			employee.setEmployeeId(id);
			fail();
		} catch (RuntimeException e) {
			assertEquals("Employee Id must be a 4-digit Positive number", e.getMessage());
		}
	}

	@Test
	public void testGetSetEmployeeIdWithMoreThanFourDigitId() {
		try {
			int id = 123456789;

			employee.setEmployeeId(id);
			fail();
		} catch (RuntimeException e) {
			assertEquals("Employee Id must be a 4-digit Positive number", e.getMessage());
		}
	}

	// Test cases for leaveId
	@Test
	public void testGetSetLeaveIdWithValidId() {
		int leaveId = 1;
		leaveRequest.setLeaveId(leaveId);
		int checkId = leaveRequest.getLeaveId();
		assertEquals(leaveId, checkId);
	}

	@Test
	public void testWithGetSetLeaveIdNegativeId() {
		try {
			int leaveId = -1;
			leaveRequest.setLeaveId(leaveId);
			fail();
		} catch (Exception e) {
			assertEquals("Id cannot be negative", e.getMessage());
		}
	}

	// Test cases for GetSetFromDate
	@Test
	public void testGetSetFromDateWithValidDate() {
		String fromDate = "2021-06-01";
		LocalDate date = LocalDate.parse(fromDate);
		leaveRequest.setFromDate(date);
		LocalDate testDate = leaveRequest.getFromDate();
		assertEquals(date, testDate);
	}

	@Test
	public void testGetSetFromDateWithPastDate() {
		try {
			String fromDate = "2000-06-01";
			LocalDate date = LocalDate.parse(fromDate);
			leaveRequest.setFromDate(date);
			fail();
		} catch (Exception e) {
			assertEquals("Date cannot be past date", e.getMessage());
		}
	}

	// Test cases for GetSetToDate
	@Test
	public void testGetSetToDatewithValidDate() {
		String date = "2021-06-02";
		LocalDate testDate = LocalDate.parse(date);
		leaveRequest.setToDate(testDate);
		assertEquals(testDate, leaveRequest.getToDate());
	}

	@Test
	public void testGetSetToDatewithPastDate() {
		try {
			String date = "2020-06-02";
			LocalDate testDate = LocalDate.parse(date);
			leaveRequest.setToDate(testDate);
			fail();
		} catch (Exception e) {
			assertEquals("Date cannot be past date", e.getMessage());
		}
	}

	// Test cases for GetSetDuration

	@Test
	public void testGetSetDurationWithValidValue() {
		try {
			int duration = 2;
			leaveRequest.setDuration(duration);
			duration = 0;
			leaveRequest.setDuration(duration);
			assertTrue(true);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testGetSetDurationWithNegativeValue() {
		try {
			int duration = -2;
			leaveRequest.setDuration(duration);
			fail();
		} catch (Exception e) {
			assertEquals("Leave duration cannot be negative", e.getMessage());
		}
	}
}
