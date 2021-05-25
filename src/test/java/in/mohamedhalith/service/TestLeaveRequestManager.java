package in.mohamedhalith.service;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.Test;

import in.mohamedhalith.exception.ServiceException;
import in.mohamedhalith.exception.ValidationException;
import in.mohamedhalith.model.LeaveRequest;

public class TestLeaveRequestManager {
	LeaveRequest leaveRequest = new LeaveRequest();

	@Test
	public void testGetRequestList() {
		try {
			List<LeaveRequest> requestList = LeaveRequestService.getRequestList();
			assertEquals(0, requestList.size());
		} catch (ServiceException e) {
			fail();
		}
	}

	// Test cases for getEmployeeRequest
	@Test
	public void testGetEmployeeRequestsWithValidEmployee() {
		try {
			List<LeaveRequest> requestList = LeaveRequestService.getEmployeeRequests("moha2627");
			assertEquals(0, requestList.size());
		} catch (ServiceException | ValidationException e) {
			fail();
		}
	}
	@Test
	public void testGetEmployeeRequestsWithInvalidEmployee() {
		try {
			LeaveRequestService.getEmployeeRequests("mohamed");
			fail();
		} catch (ServiceException | ValidationException e) {
			assertEquals("No employee is found for given details",e.getMessage());
		}
	}
}
