package in.mohamedhalith.dao;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;

import in.mohamedhalith.exception.DBException;
import in.mohamedhalith.exception.ServiceException;
import in.mohamedhalith.exception.ValidationException;
import in.mohamedhalith.model.Employee;
import in.mohamedhalith.model.LeaveRequest;
import in.mohamedhalith.service.EmployeeManager;


public class TestLeaveRequestDAO {

	LeaveRequestDAO leaveRequestDA = LeaveRequestDAO.getInstance();
	LeaveRequest leaveRequest = new LeaveRequest();

	@Test
	public void testGetInstance() {
		LeaveRequestDAO leaveRequestDAO = null;
		leaveRequestDAO = LeaveRequestDAO.getInstance();
		if (leaveRequestDAO == null) {
			fail();
		}
	}

	@Test
	public void testGetRequestList() {
		try {
			List<LeaveRequest> requestList = leaveRequestDA.getRequestList();
			assertEquals(0, requestList.size());
		} catch (DBException e) {
			fail();
		}
	}

	@Test
	public void testGetEmployeeRequests() {
		try {
			Employee employee = EmployeeManager.getEmployee("moha2627");
			List<LeaveRequest> requestList = leaveRequestDA.getEmployeeRequests(employee);
			for (LeaveRequest leaveRequest : requestList) {
				System.out.println(leaveRequest);
			}
			assertTrue(true);
		} catch (ServiceException | ValidationException | DBException e) {
			fail();
		}
	}

	// Test cases for applyLeaveRequest
	@Test
	public void testApplyRequestWithValidDetailsForSickLeave() {
		
		try {
			leaveRequest.setEmployeeName("Mohamed");
			leaveRequest.setEmployeeId(2627);
			LocalDate fromDate = LocalDate.parse("2022-06-01");
			leaveRequest.setFromDate(fromDate);
			LocalDate toDate = LocalDate.parse("2022-06-02");
			leaveRequest.setDuration(1);
			leaveRequest.setToDate(toDate);
			leaveRequest.setType("SickLeave");
			leaveRequest.setReason("Leave");
			Employee employee = EmployeeManager.getEmployee("moha2627");
			String message = leaveRequestDA.applyLeaveRequest(leaveRequest);
			assertEquals("Successfully Applied!... You have 1 remaining SickLeaves", message);
		} catch (ServiceException | DBException | ValidationException e) {
			fail();
		}

	}

	@Test
	public void testApplyLeaveRequestWithValidDetailsForEarnedLeave() {
		try {
			leaveRequest.setEmployeeName("Mohamed");
			leaveRequest.setEmployeeId(2627);
			LocalDate fromDate = LocalDate.parse("2022-06-01");
			leaveRequest.setFromDate(fromDate);
			LocalDate toDate = LocalDate.parse("2022-06-02");
			leaveRequest.setDuration(1);
			leaveRequest.setToDate(toDate);
			leaveRequest.setType("EarnedLeave");
			leaveRequest.setReason("Leave");
			Employee employee = EmployeeManager.getEmployee("moha2627");
			String message = leaveRequestDA.applyLeaveRequest(leaveRequest);
			assertEquals("Successfully Applied!... You have 1 remaining EarnedLeaves", message);
		} catch (ServiceException | DBException | ValidationException e) {
			fail();
		}
	}

	@Test
	public void testApplyRequestWithValidDetailsForCasualLeave() {
		try {
			leaveRequest.setEmployeeName("Mohamed");
			leaveRequest.setEmployeeId(2627);
			LocalDate fromDate = LocalDate.parse("2022-06-01");
			leaveRequest.setFromDate(fromDate);
			LocalDate toDate = LocalDate.parse("2022-06-02");
			leaveRequest.setDuration(1);
			leaveRequest.setToDate(toDate);
			leaveRequest.setType("CasualLeave");
			leaveRequest.setReason("Leave");
			Employee employee = EmployeeManager.getEmployee("moha2627");
			String message = leaveRequestDA.applyLeaveRequest(leaveRequest);
			assertEquals("Successfully Applied!... You have 1 remaining CasualLeaves", message);
		} catch (ServiceException | DBException | ValidationException e) {
			fail();
		}
	}

}
