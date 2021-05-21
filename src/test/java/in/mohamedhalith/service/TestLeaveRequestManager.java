package in.mohamedhalith.service;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.Test;

import in.mohamedhalith.exception.ServiceException;
import in.mohamedhalith.model.LeaveRequest;

public class TestLeaveRequestManager {
	LeaveRequest leaveRequest = new LeaveRequest();

	@Test
	public void testGetRequestList() {
		List<LeaveRequest> requestList = LeaveRequestManager.getRequestList();
		assertEquals(0, requestList.size());
	}

	// Test cases for getEmployeeRequest
	@Test
	public void testGetEmployeeRequestsWithValidEmployee() {
		try {
			List<LeaveRequest> requestList = LeaveRequestManager.getEmployeeRequests("moha2627");
			assertEquals(0, requestList.size());
		} catch (ServiceException e) {
			fail();
		}
	}
	@Test
	public void testGetEmployeeRequestsWithInvalidEmployee() {
		try {
			LeaveRequestManager.getEmployeeRequests("mohamed");
			fail();
		} catch (ServiceException e) {
			assertEquals("No employee is found for given details",e.getMessage());
		}
	}
}
