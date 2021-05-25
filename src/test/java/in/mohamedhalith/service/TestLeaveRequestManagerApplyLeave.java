package in.mohamedhalith.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDate;

import org.junit.Test;

import in.mohamedhalith.exception.ServiceException;
import in.mohamedhalith.exception.ValidationException;
import in.mohamedhalith.model.LeaveRequest;

public class TestLeaveRequestManagerApplyLeave {

	LeaveRequest leaveRequest = new LeaveRequest();

	@Test
	public void testApplyRequestWithValidDetailsForSickLeave() {
		try {
			leaveRequest.setEmployeeName("Mohamed");
			leaveRequest.setEmployeeId(2627);
			LocalDate fromDate = LocalDate.parse("2021-05-21");
			leaveRequest.setFromDate(fromDate);
			LocalDate toDate = LocalDate.parse("2021-06-02");
			leaveRequest.setDuration(1);
			leaveRequest.setToDate(toDate);
			leaveRequest.setType("SickLeave");
			leaveRequest.setReason("Leave");
			boolean isApplied = LeaveRequestService.applyLeaveRequest(leaveRequest, "moha2627");
			assertTrue(isApplied);
		} catch (ServiceException | ValidationException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testApplyRequestWithValidDetailsForEarnedLeave() {
		try {
			leaveRequest.setEmployeeName("Mohamed");
			leaveRequest.setEmployeeId(2627);
			LocalDate fromDate = LocalDate.parse("2022-07-01");
			leaveRequest.setFromDate(fromDate);
			LocalDate toDate = LocalDate.parse("2022-07-02");
			leaveRequest.setDuration(1);
			leaveRequest.setToDate(toDate);
			leaveRequest.setType("EarnedLeave");
			leaveRequest.setReason("Leave");
			boolean isApplied = LeaveRequestService.applyLeaveRequest(leaveRequest, "moha2627");
			assertTrue(isApplied);
		} catch (ServiceException | ValidationException e) {
			fail();
		}
	}

	@Test
	public void testApplyRequestWithValidDetailsForCasualLeave() {
		try {
			leaveRequest.setEmployeeName("Mohamed");
			leaveRequest.setEmployeeId(2627);
			LocalDate fromDate = LocalDate.parse("2022-09-01");
			leaveRequest.setFromDate(fromDate);
			LocalDate toDate = LocalDate.parse("2022-12-01");
			leaveRequest.setToDate(toDate);
			leaveRequest.setDuration(1);
			leaveRequest.setType("CasualLeave");
			leaveRequest.setReason("Leave");
			boolean isApplied = LeaveRequestService.applyLeaveRequest(leaveRequest, "moha2627");
			assertTrue(isApplied);
		} catch (ServiceException | ValidationException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testApplyRequestWithInvalidEmployee() {
		try {
			leaveRequest.setEmployeeName("Mohamed");
			leaveRequest.setEmployeeId(2627);
			LocalDate fromDate = LocalDate.parse("2022-06-01");
			leaveRequest.setFromDate(fromDate);
			LocalDate toDate = LocalDate.parse("2022-01-01");
			leaveRequest.setToDate(toDate);
			leaveRequest.setType("SickLeave");
			leaveRequest.setReason("Leave");
			boolean isApplied = LeaveRequestService.applyLeaveRequest(leaveRequest, "moha");
			fail();
		} catch (ServiceException | ValidationException e) {
			assertEquals("No employee is found for given details", e.getMessage());
		}
	}

	@Test
	public void testApplyRequestWithDuplicateRequest() {
		try {
			LocalDate fromDate = LocalDate.parse("2022-06-01");
			leaveRequest.setFromDate(fromDate);
			LocalDate toDate = LocalDate.parse("2022-01-01");
			leaveRequest.setToDate(toDate);
			leaveRequest.setType("SickLeave");
			leaveRequest.setReason("Leave");
			LeaveRequestService.applyLeaveRequest(leaveRequest, "moha2627");
			LeaveRequest leaveRequest1 = new LeaveRequest();
			LeaveRequestService.applyLeaveRequest(leaveRequest, "moha2627");
			fail();
		} catch (ServiceException | ValidationException e) {
			assertEquals("Leave request found for mentioned date(s).", e.getMessage());
		}

	}

	@Test
	public void testApplyRequestTillZeroLeave() {
		try {
			leaveRequest.setType("SickLeave");
			LocalDate fromDate = LocalDate.parse("2022-06-15");
			leaveRequest.setFromDate(fromDate);
			LocalDate toDate = LocalDate.parse("2022-01-21");
			leaveRequest.setToDate(toDate);
			leaveRequest.setDuration(1);
			boolean isApplied = LeaveRequestService.applyLeaveRequest(leaveRequest, "hali2628");
			assertTrue(isApplied);
			LeaveRequest leaveRequest1 = new LeaveRequest();
			leaveRequest1.setType("SickLeave");
			fromDate = LocalDate.parse("2022-06-11");
			leaveRequest1.setFromDate(fromDate);
			toDate = LocalDate.parse("2022-01-12");
			leaveRequest1.setToDate(toDate);
			leaveRequest1.setDuration(1);
			isApplied = LeaveRequestService.applyLeaveRequest(leaveRequest1, "hali2628");
			assertTrue(isApplied);
			LeaveRequest leaveRequest2 = new LeaveRequest();
			leaveRequest2.setType("SickLeave");
			fromDate = LocalDate.parse("2022-06-23");
			leaveRequest2.setFromDate(fromDate);
			toDate = LocalDate.parse("2022-01-22");
			leaveRequest2.setToDate(toDate);
			leaveRequest2.setDuration(1);
			isApplied = LeaveRequestService.applyLeaveRequest(leaveRequest2, "hali2628");
			fail();
		} catch (ServiceException | ValidationException e) {
			assertEquals("Leave duration exceeds the permitted leave days", e.getMessage());
		}
	}
}
