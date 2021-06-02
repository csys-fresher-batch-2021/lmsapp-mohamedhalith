package in.mohamedhalith.validator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;

import in.mohamedhalith.exception.ServiceException;
import in.mohamedhalith.exception.ValidationException;
import in.mohamedhalith.model.LeaveBalance;
import in.mohamedhalith.model.LeaveRequest;
import in.mohamedhalith.service.LeaveBalanceService;
import in.mohamedhalith.service.LeaveRequestService;

public class TestLeaveRequestValidator {

	LeaveRequest leaveRequest = new LeaveRequest();
	LeaveRequest leaveRequest1 = new LeaveRequest();
	
	@Test
	public void testisValidRequestWithUniqueRequests() {
		try {
			LocalDate fromDate = LocalDate.parse("2021-06-01");
			LocalDate toDate = LocalDate.parse("2021-06-03");
			leaveRequest.setFromDate(fromDate);
			leaveRequest.setToDate(toDate);
			List<LeaveRequest> employeeRequest = LeaveRequestService.getEmployeeRequests(2627);
			LeaveRequestValidator.findDuplicateRequest(leaveRequest);
			assertTrue(true);
		} catch (ServiceException | ValidationException e) {
			System.out.println(e.getMessage());
			fail();
		}
	}


	@Test
	public void testFindDuplicationRequestWithUniqueRequests() {
		try {
			LocalDate fromDate = LocalDate.parse("2021-06-01");
			LocalDate toDate = LocalDate.parse("2021-06-03");
			leaveRequest.setFromDate(fromDate);
			leaveRequest.setToDate(toDate);
			List<LeaveRequest> employeeRequest = LeaveRequestService.getEmployeeRequests(2627);
			LeaveRequestValidator.findDuplicateRequest(leaveRequest);
			assertTrue(true);
		} catch (ServiceException | ValidationException e) {
			System.out.println(e.getMessage());
			fail();
		}
	}

	@Test
	public void testFindDuplicationRequestWithDuplicateRequests() {
		try {
			LocalDate fromDate = LocalDate.parse("2021-06-01");
			LocalDate toDate = LocalDate.parse("2021-06-03");
			leaveRequest.setFromDate(fromDate);
			leaveRequest.setToDate(toDate);
			List<LeaveRequest> employeeRequest = LeaveRequestService.getEmployeeRequests(2627);
			LeaveRequestValidator.findDuplicateRequest(leaveRequest);
			employeeRequest.add(leaveRequest);
			leaveRequest1.setFromDate(fromDate);
			leaveRequest1.setToDate(toDate);
			LeaveRequestValidator.findDuplicateRequest(leaveRequest1);
			fail();
		} catch (ServiceException | ValidationException e) {
			assertEquals("Leave request found for mentioned date(s).", e.getMessage());
		}
	}

	// Test cases for isValidDuration

	@Test
	public void testIsValidDurationWithValidDurationForSickLeave() {

		try {
			leaveRequest.setDuration(1);
			leaveRequest.setType("SickLeave");
			LeaveBalance employee = LeaveBalanceService.findLeaveBalance(2627);
			LeaveRequestValidator.isValidDuration(leaveRequest, employee);
			assertTrue(true);
		} catch (ServiceException | ValidationException e) {
			fail();
		}
	}

	@Test
	public void testIsValidDurationWithValidDurationForCasualLeave() {
		try {
			leaveRequest.setDuration(1);
			leaveRequest.setType("CasualLeave");
			LeaveBalance employee = LeaveBalanceService.findLeaveBalance(2627);
			LeaveRequestValidator.isValidDuration(leaveRequest, employee);
			assertTrue(true);
		} catch (ServiceException | ValidationException e) {
			fail();
		}
	}

	@Test
	public void testIsValidDurationWithValidDurationForEarnedLeave() {
		try {
			leaveRequest.setDuration(1);
			leaveRequest.setType("EarnedLeave");
			LeaveBalance employee = LeaveBalanceService.findLeaveBalance(2627);
			LeaveRequestValidator.isValidDuration(leaveRequest, employee);
			assertTrue(true);
		} catch (ServiceException | ValidationException e) {
			fail();
		}
	}

	@Test
	public void testIsValidDurationWithInvalidDurationForSickLeave() {
		try {
			leaveRequest.setDuration(4);
			leaveRequest.setType("SickLeave");
			LeaveBalance employee = LeaveBalanceService.findLeaveBalance(2627);
			LeaveRequestValidator.isValidDuration(leaveRequest, employee);
			fail();
		} catch (ServiceException | ValidationException e) {
			assertEquals("Leave duration exceeds the permitted leave days", e.getMessage());
		}
	}

	@Test
	public void testIsValidDurationWithInvalidDurationForCasualLeave() {

		try {
			leaveRequest.setDuration(4);
			leaveRequest.setType("CasualLeave");
			LeaveBalance employee = LeaveBalanceService.findLeaveBalance(2627);
			LeaveRequestValidator.isValidDuration(leaveRequest, employee);
			fail();
		} catch (ServiceException | ValidationException e) {
			assertEquals("Leave duration exceeds the permitted leave days", e.getMessage());
		}
	}

	@Test
	public void testIsValidDurationWithInvalidDurationForEarnedLeave() {

		try {
			leaveRequest.setDuration(4);
			leaveRequest.setType("EarnedLeave");
			LeaveBalance employee = LeaveBalanceService.findLeaveBalance(2627);
			LeaveRequestValidator.isValidDuration(leaveRequest, employee);
			fail();
		} catch (ServiceException | ValidationException e) {
			assertEquals("Leave duration exceeds the permitted leave days", e.getMessage());
		}
	}
}
