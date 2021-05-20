package in.mohamedhalith.validator;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;

import in.mohamedhalith.exception.ServiceException;
import in.mohamedhalith.exception.ValidationException;
import in.mohamedhalith.model.Employee;
import in.mohamedhalith.model.LeaveRequest;
import in.mohamedhalith.service.EmployeeManager;
import in.mohamedhalith.service.LeaveRequestManager;

public class TestLeaveRequestValidator {
	
	LeaveRequest leaveRequest = new LeaveRequest();
	LeaveRequest leaveRequest1 = new LeaveRequest();
	
	@Test
	public void testFindDuplicationRequestWithUniqueRequests() {
		try {
			LocalDate fromDate = LocalDate.parse("2021-06-01");
			LocalDate toDate = LocalDate.parse("2021-06-03");
			leaveRequest.setFromDate(fromDate);
			leaveRequest.setToDate(toDate);
			List<LeaveRequest> employeeRequest = LeaveRequestManager.getEmployeeRequests("moha2627");
			LeaveRequestValidator.findDuplicateRequest(leaveRequest, employeeRequest);
			assertTrue(true);
		} catch (ServiceException | ValidationException e) {
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
			List<LeaveRequest> employeeRequest = LeaveRequestManager.getEmployeeRequests("moha2627");
			LeaveRequestValidator.findDuplicateRequest(leaveRequest, employeeRequest);
			employeeRequest.add(leaveRequest);
			leaveRequest1.setFromDate(fromDate);
			leaveRequest1.setToDate(toDate);
			LeaveRequestValidator.findDuplicateRequest(leaveRequest1, employeeRequest);
			fail();			
		} catch (ServiceException | ValidationException e) {
			assertEquals("Leave request found for mentioned date(s).",e.getMessage());
		}
	}
	
	// Test cases for isValidDuration
	
	@Test
	public void testIsValidDurationWithValidDurationForSickLeave() {
		leaveRequest.setDuration(1);
		leaveRequest.setType("SickLeave");
		try {
			Employee employee = EmployeeManager.getEmployee("moha2627");
			LeaveRequestValidator.isValidDuration(leaveRequest, employee);
			assertTrue(true);
		} catch (ServiceException | ValidationException e) {
			fail();
		}
	}
	
	@Test
	public void testIsValidDurationWithValidDurationForCasualLeave() {
		leaveRequest.setDuration(1);
		leaveRequest.setType("CasualLeave");
		try {
			Employee employee = EmployeeManager.getEmployee("moha2627");
			LeaveRequestValidator.isValidDuration(leaveRequest, employee);
			assertTrue(true);
		} catch (ServiceException | ValidationException e) {
			fail();
		}
	}
	@Test
	public void testIsValidDurationWithValidDurationForEarnedLeave() {
		leaveRequest.setDuration(1);
		leaveRequest.setType("EarnedLeave");
		try {
			Employee employee = EmployeeManager.getEmployee("moha2627");
			LeaveRequestValidator.isValidDuration(leaveRequest, employee);
			assertTrue(true);
		} catch (ServiceException | ValidationException e) {
			fail();
		}
	}
	
	@Test
	public void testIsValidDurationWithInvalidDurationForSickLeave() {
		leaveRequest.setDuration(4);
		leaveRequest.setType("SickLeave");
		try {
			Employee employee = EmployeeManager.getEmployee("moha2627");
			LeaveRequestValidator.isValidDuration(leaveRequest, employee);
			fail();
		} catch (ServiceException | ValidationException e) {
			assertEquals("Leave duration exceeds the permitted leave days",e.getMessage());
		}
	}
	@Test
	public void testIsValidDurationWithInvalidDurationForCasualLeave() {
		leaveRequest.setDuration(4);
		leaveRequest.setType("CasualLeave");
		try {
			Employee employee = EmployeeManager.getEmployee("moha2627");
			LeaveRequestValidator.isValidDuration(leaveRequest, employee);
			fail();
		} catch (ServiceException | ValidationException e) {
			assertEquals("Leave duration exceeds the permitted leave days",e.getMessage());
		}
	}@Test
	public void testIsValidDurationWithInvalidDurationForEarnedLeave() {
		leaveRequest.setDuration(4);
		leaveRequest.setType("EarnedLeave");
		try {
			Employee employee = EmployeeManager.getEmployee("moha2627");
			LeaveRequestValidator.isValidDuration(leaveRequest, employee);
			fail();
		} catch (ServiceException | ValidationException e) {
			assertEquals("Leave duration exceeds the permitted leave days",e.getMessage());
		}
	}
}
