package in.mohamedhalith.validator;

import java.time.LocalDate;
import java.util.List;

import in.mohamedhalith.exception.ServiceException;
import in.mohamedhalith.exception.ValidationException;
import in.mohamedhalith.model.Employee;
import in.mohamedhalith.model.LeaveRequest;
import in.mohamedhalith.service.EmployeeService;
import in.mohamedhalith.service.LeaveRequestService;
import in.mohamedhalith.util.DateTimeValidator;

public class LeaveRequestValidator {

	private LeaveRequestValidator() {
		// Default constructor
	}

	/**
	 * This method checks given request is valid or not. Valid in the sense that
	 * there is no similar or duplicate requests present and the leave request is
	 * not in violation of allowed leaves.
	 * 
	 * @param leaveRequest
	 * @param employee
	 * @param employeeRequests
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public static void isValidRequest(LeaveRequest leaveRequest, int employeeId, List<LeaveRequest> employeeRequests)
			throws ValidationException, ServiceException {
		Employee employee = EmployeeService.findLeaveBalance(employeeId);
		isValidDates(leaveRequest);
		findDuplicateRequest(leaveRequest, employeeRequests);
		isValidDuration(leaveRequest, employee);
	}

	/**
	 * This method is used to check whether the leave dates are valid or not
	 * 
	 * @param leaveRequest
	 * @throws ValidationException
	 */
	public static void isValidDates(LeaveRequest leaveRequest) throws ValidationException {
		DateTimeValidator.isValidDate(leaveRequest.getFromDate());
		DateTimeValidator.isValidDate(leaveRequest.getToDate());
	}

	public static void isValidId(int leaveId) throws ValidationException, ServiceException {
		boolean valid = false;
		List<LeaveRequest> requestList = LeaveRequestService.getRequestList();
		for (LeaveRequest leaveRequest : requestList) {
			if (leaveRequest.getLeaveId() == leaveId) {
				valid = true;
				break;
			}
		}
		if (!valid) {
			throw new ValidationException("Cannot find leave request for given id");
		}

	}

	/**
	 * This method is used to find any duplicate request present in the request
	 * list.
	 * 
	 * @param leaveRequest
	 * @throws ValidationException
	 */
	public static void findDuplicateRequest(LeaveRequest leaveRequest, List<LeaveRequest> employeeRequests)
			throws ValidationException {
		boolean duplicate = false;
		String status = "waiting for approval";
		LocalDate fromDate = leaveRequest.getFromDate();
		LocalDate toDate = leaveRequest.getToDate();
		for (LeaveRequest requestLeave : employeeRequests) {
			LocalDate leaveFromDate = requestLeave.getFromDate();
			LocalDate leaveToDate = requestLeave.getToDate();
			if (status.equalsIgnoreCase(requestLeave.getStatus())
					&& (fromDate.isEqual(leaveFromDate) || toDate.isEqual(leaveToDate)
							|| (fromDate.isAfter(leaveFromDate) && toDate.isBefore(leaveToDate)))) {
				duplicate = true;
				break;
			}
		}
		if (duplicate) {
			throw new ValidationException("Leave request found for mentioned date(s).");
		}
	}

	/**
	 * This method is used to verify whether the applied leave duration is within
	 * the permitted leave days.
	 * 
	 * @param leaveRequest
	 * @param employee
	 * @throws ValidationException
	 */
	public static void isValidDuration(LeaveRequest leaveRequest, Employee employee) throws ValidationException {
		boolean invalid = false;
		int duration = leaveRequest.getDuration();
		switch (leaveRequest.getType()) {
		case "SickLeave":
			if (duration > employee.getSickLeave()) {
				invalid = true;
			}
			break;
		case "CasualLeave":
			if (duration > employee.getCasualLeave()) {
				invalid = true;
			}
			break;
		case "EarnedLeave":
			if (duration > employee.getEarnedLeave()) {
				invalid = true;
			}
			break;
		default:
			throw new ValidationException("Invalid leave type");
		}

		if (invalid) {
			throw new ValidationException("Leave duration exceeds the permitted leave days");
		}
	}
}
