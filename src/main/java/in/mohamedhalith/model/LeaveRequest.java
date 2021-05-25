package in.mohamedhalith.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import in.mohamedhalith.exception.ValidationException;
import in.mohamedhalith.util.StringValidator;

public class LeaveRequest {

	private int leaveId;
	private String employeeName;
	private int employeeId;
	private LocalDate fromDate;
	private LocalDate toDate;
	private int duration;
	private String type;
	private String reason;
	private String status = "Waiting for approval";
	private LocalDateTime cancelledTime;
	private LocalDateTime reviewedTime;
	private LocalDateTime appliedTime;

	public int getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(int leaveId) {
		if (leaveId < 0) {
			throw new IllegalArgumentException("Id cannot be negative");
		}
		this.leaveId = leaveId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) throws ValidationException {
		StringValidator.isValidString(employeeName);
		this.employeeName = employeeName;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		if (employeeId < 1000 || employeeId > 9999) {
			throw new IllegalArgumentException("Employee Id must be a 4-digit Positive number");
		}
		this.employeeId = employeeId;
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) throws ValidationException {
		StringValidator.isValidString(type);
		this.type = type;

	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) throws ValidationException {
		StringValidator.isValidString(reason);
		this.reason = reason;

	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) throws ValidationException {
		StringValidator.isValidString(status);
		this.status = status;

	}

	public LocalDateTime getCancelledTime() {
		return cancelledTime;
	}

	public void setCancelledTime(LocalDateTime cancelledTime) {
		this.cancelledTime = cancelledTime;
	}

	public LocalDateTime getReviewedTime() {
		return reviewedTime;
	}

	public void setReviewedTime(LocalDateTime reviewedTime) {
		this.reviewedTime = reviewedTime;
	}

	public LocalDateTime getAppliedTime() {
		return appliedTime;
	}

	public void setAppliedTime(LocalDateTime appliedTime) {
		this.appliedTime = appliedTime;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		if (duration < 0) {
			throw new IllegalArgumentException("Leave duration cannot be negative");
		}
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "LeaveRequest leaveId " + leaveId + " employeeName " + employeeName + " employeeId " + employeeId
				+ " fromDate " + fromDate + " toDate " + toDate + " type " + type + " reason " + reason + " status "
				+ status + " cancelledTime " + cancelledTime + " appliedTime " + appliedTime;
	}
}
