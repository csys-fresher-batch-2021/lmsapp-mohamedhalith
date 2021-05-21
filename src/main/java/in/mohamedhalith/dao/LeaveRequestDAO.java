package in.mohamedhalith.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import in.mohamedhalith.exception.DBException;
import in.mohamedhalith.model.Employee;
import in.mohamedhalith.model.LeaveRequest;

public class LeaveRequestDAO {

	private static final List<LeaveRequest> requestList = new ArrayList<>();

	private LeaveRequestDAO() {
		// Default Constructor
	}

	private static LeaveRequestDAO instance = new LeaveRequestDAO();

	/**
	 * This method is used to obtain the instance of the class LeaveDAO.
	 * 
	 * @return LeaveRequestDAO
	 */
	public static LeaveRequestDAO getInstance() {
		return instance;
	}

	/**
	 * This method is used to obtain the list of leave request from the static
	 * memory.
	 * 
	 * Returns list of leave requests.
	 * 
	 * @return List<LeaveRequest>
	 */
	public List<LeaveRequest> getRequestList() {
		return requestList;
	}

	/**
	 * This method is used to obtain the leave requests applied by a particular
	 * employee.
	 * 
	 * Return a list of leave request of an employee
	 * 
	 * @param employee
	 * @return
	 */
	public List<LeaveRequest> getEmployeeRequests(Employee employee) {
		int id = employee.getId();
		List<LeaveRequest> employeeRequests = new ArrayList<>();
		for (LeaveRequest leaveRequest : requestList) {
			if (leaveRequest.getEmployeeId() == id) {
				employeeRequests.add(leaveRequest);
			}
		}
		return employeeRequests;
	}

	/**
	 * This method is used to add the leave request applied by the employee to the
	 * list.
	 * 
	 * Returns a string with appropriate message.
	 * 
	 * @param leaveRequest
	 * @param employee
	 * @return String
	 * @throws DBException
	 */
	public String applyLeaveRequest(LeaveRequest leaveRequest, Employee employee) throws DBException {
		String message = "Failed to add";
		String type = leaveRequest.getType();
		int duration = leaveRequest.getDuration();
		int remainingLeaves = -1;
		boolean isApplied = false;
		try {
			// Reducing the no of leaves of respective type
			switch (type) {
			case "SickLeave":
				int sickLeave = employee.getSickLeave();
				if (sickLeave != 0 && sickLeave >= duration) {
					sickLeave -= duration;
					employee.setSickLeave(sickLeave);
					remainingLeaves = sickLeave;
					isApplied = true;
				}
				break;
			case "CasualLeave":
				int casualLeave = employee.getCasualLeave();
				if (casualLeave != 0 && casualLeave >= duration) {
					casualLeave -= duration;
					employee.setCasualLeave(casualLeave);
					remainingLeaves = casualLeave;
					isApplied = true;
				}
				break;
			case "EarnedLeave":
				int earnedLeave = employee.getEarnedLeave();
				if (earnedLeave != 0 && earnedLeave >= duration) {
					earnedLeave -= duration;
					employee.setEarnedLeave(earnedLeave);
					remainingLeaves = earnedLeave;
					isApplied = true;
				}
				break;
			default:
				message = "Invalid leave type";
			}
			// If blocks is executes only if the leave request is applied, otherwise else
			// block is executed
			if (isApplied && remainingLeaves != -1) {
				leaveRequest.setLeaveId(requestList.size() + 1);
				leaveRequest.setAppliedTime(LocalDateTime.now());
				requestList.add(leaveRequest);
				message = "Successfully Applied!... You have " + remainingLeaves + " remaining " + type + "s";
			}
		} catch (Exception e) {
			throw new DBException(e, e.getMessage());
		}
		return message;
	}
}
