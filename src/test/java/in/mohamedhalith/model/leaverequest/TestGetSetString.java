package in.mohamedhalith.model.leaverequest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.Test;

import in.mohamedhalith.model.LeaveRequest;

public class TestGetSetString {
	LeaveRequest leaveRequest = new LeaveRequest();

	@Test
	public void testGetSetNameWithValidName() {
		String name = "Mohamed";
		leaveRequest.setEmployeeName(name);
		String checkName = leaveRequest.getEmployeeName();
		assertEquals(name, checkName);
	}

	@Test
	public void testGetSetNameWithEmptyName() {
		try {
			String name = "";
			leaveRequest.setEmployeeName(name);
			fail();
		} catch (Exception e) {
			assertEquals("Employee name cannot be empty or null", e.getMessage());
		}
	}

	@Test
	public void testGetSetNameWithWhiteSpaces() {
		try {
			String name = "     ";
			leaveRequest.setEmployeeName(name);
			fail();
		} catch (Exception e) {
			assertEquals("Employee name cannot be empty or null", e.getMessage());
		}
	}

	// Test cases for GetSetReason
	@Test
	public void testGetSetReasonWithValidReason() {
		String reason = "Mohamed";
		leaveRequest.setReason(reason);
		String checkReason = leaveRequest.getReason();
		assertEquals(reason, checkReason);
	}

	@Test
	public void testGetSetReasonWithEmptyReason() {
		try {
			String reason = "";
			leaveRequest.setReason(reason);
			fail();
		} catch (Exception e) {
			assertEquals("Reason should not be null or empty", e.getMessage());
		}
	}

	@Test
	public void testGetSetReasonWithWhiteSpaces() {
		try {
			String reason = "     ";
			leaveRequest.setReason(reason);
			fail();
		} catch (Exception e) {
			assertEquals("Reason should not be null or empty", e.getMessage());
		}
	}

	// Test cases for GetSetStatus
	@Test
	public void testGetSetStatusWithValidStatus() {
		String status = "Mohamed";
		leaveRequest.setStatus(status);
		String checkStatus = leaveRequest.getStatus();
		assertEquals(status, checkStatus);
	}

	@Test
	public void testGetSetStatusWithEmptyStatus() {
		try {
			String status = "";
			leaveRequest.setStatus(status);
			fail();
		} catch (Exception e) {
			assertEquals("Invalid leave status", e.getMessage());
		}
	}

	@Test
	public void testGetSetStatusWithWhiteSpaces() {
		try {
			String status = "     ";
			leaveRequest.setStatus(status);
			fail();
		} catch (Exception e) {
			assertEquals("Invalid leave status", e.getMessage());
		}
	}

	// Test cases for GetSetType
	@Test
	public void testGetSetTypeWithValidType() {
		String type = "Mohamed";
		leaveRequest.setType(type);
		String checkType = leaveRequest.getType();
		assertEquals(type, checkType);
	}

	@Test
	public void testGetSetTypeWithEmptyType() {
		try {
			String type = "";
			leaveRequest.setType(type);
			fail();
		} catch (Exception e) {
			assertEquals("Invalid Leave type", e.getMessage());
		}
	}

	@Test
	public void testGetSetTypeWithWhiteSpaces() {
		try {
			String type = "     ";
			leaveRequest.setType(type);
			fail();
		} catch (Exception e) {
			assertEquals("Invalid Leave type", e.getMessage());
		}
	}

	// Test cases for ToString
	@Test
	public void testToString() {
		leaveRequest.setEmployeeName("Mohamed");
		leaveRequest.setEmployeeId(2627);
		LocalDate fromDate = LocalDate.parse("2022-06-01");
		leaveRequest.setFromDate(fromDate);
		LocalDate toDate = LocalDate.parse("2022-01-01");
		leaveRequest.setToDate(toDate);
		leaveRequest.setType("SickLeave");
		leaveRequest.setStatus("Approved");
		leaveRequest.setLeaveId(1);
		leaveRequest.setReason("Leave");
		leaveRequest.setAppliedTime(LocalDateTime.now());
		leaveRequest.setCancelledTime(LocalDateTime.now());
		LocalDateTime now = leaveRequest.getAppliedTime();
		now = leaveRequest.getCancelledTime();

		assertEquals("LeaveRequest leaveId 1 employeeName Mohamed employeeId 2627 "
				+ "fromDate 2022-06-01 toDate 2022-01-01 type SickLeave reason Leave"
				+ " status Approved cancelledTime " + now + " appliedTime " + now, leaveRequest.toString());
	}
}
