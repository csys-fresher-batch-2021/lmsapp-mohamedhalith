package in.mohamedhalith.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;

import in.mohamedhalith.exception.DBException;
import in.mohamedhalith.model.Employee;
import in.mohamedhalith.model.LeaveRequest;


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
			List<LeaveRequest> requestList = leaveRequestDA.findAll();
			assertEquals(0, requestList.size());
		} catch (DBException e) {
			fail();
		}
	}

	@Test
	public void testGetEmployeeRequests() {
		try {
			List<LeaveRequest> requestList = leaveRequestDA.findRequestsByEmployeeId(2627);
			for (LeaveRequest leaveRequest : requestList) {
				System.out.println(leaveRequest);
			}
			assertTrue(true);
		} catch (DBException e) {
			fail();
		}
	}

	// Test cases for applyLeaveRequest
	@Test
	public void testApplyRequestWithValidDetailsForSickLeave() {
		
		try {
			Employee employee = new Employee();
			employee.setName("Mohamed");
			employee.setEmployeeId(2627);
			leaveRequest.setEmployee(employee);
			LocalDate fromDate = LocalDate.parse("2022-06-01");
			leaveRequest.setFromDate(fromDate);
			LocalDate toDate = LocalDate.parse("2022-06-02");
			leaveRequest.setDuration(1);
			leaveRequest.setToDate(toDate);
			leaveRequest.setType("SickLeave");
			leaveRequest.setReason("Leave");
			boolean isAdded = leaveRequestDA.save(leaveRequest);
			assertTrue(isAdded);
		} catch (DBException e) {
			fail();
		}

	}

	@Test
	public void testApplyLeaveRequestWithValidDetailsForEarnedLeave() {
		try {
			Employee employee = new Employee();
			employee.setName("Mohamed");
			employee.setEmployeeId(2627);
			leaveRequest.setEmployee(employee);
			LocalDate fromDate = LocalDate.parse("2022-06-01");
			leaveRequest.setFromDate(fromDate);
			LocalDate toDate = LocalDate.parse("2022-06-02");
			leaveRequest.setDuration(1);
			leaveRequest.setToDate(toDate);
			leaveRequest.setType("EarnedLeave");
			leaveRequest.setReason("Leave");
			boolean isAdded = leaveRequestDA.save(leaveRequest);
			assertTrue(isAdded);
		} catch (DBException e) {
			fail();
		}
	}

	@Test
	public void testApplyRequestWithValidDetailsForCasualLeave() {
		try {
			Employee employee = new Employee();
			employee.setName("Mohamed");
			employee.setEmployeeId(2627);
			leaveRequest.setEmployee(employee);
			LocalDate fromDate = LocalDate.parse("2022-06-01");
			leaveRequest.setFromDate(fromDate);
			LocalDate toDate = LocalDate.parse("2022-06-02");
			leaveRequest.setDuration(1);
			leaveRequest.setToDate(toDate);
			leaveRequest.setType("CasualLeave");
			leaveRequest.setReason("Leave");
			boolean isAdded = leaveRequestDA.save(leaveRequest);
			assertTrue(isAdded);
		} catch (DBException e) {
			fail();
		}
	}

}
