package in.mohamedhalith.validator;

import java.time.LocalDate;
import java.util.List;

import in.mohamedhalith.exception.ValidationException;
import in.mohamedhalith.model.Employee;
import in.mohamedhalith.model.LeaveRequest;

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
	 */
	public static void isValidRequest(LeaveRequest leaveRequest, Employee employee, List<LeaveRequest> employeeRequests)
			throws ValidationException {
		findDuplicateRequest(leaveRequest, employeeRequests);
		isValidDuration(leaveRequest, employee);
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
		LocalDate fromDate = leaveRequest.getFromDate();
		LocalDate toDate = leaveRequest.getToDate();
		for (LeaveRequest requestLeave : employeeRequests) {
			if (fromDate.isEqual(requestLeave.getFromDate()) || toDate.isEqual(requestLeave.getToDate())) {
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
