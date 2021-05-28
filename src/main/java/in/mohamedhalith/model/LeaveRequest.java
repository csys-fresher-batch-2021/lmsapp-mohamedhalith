package in.mohamedhalith.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import in.mohamedhalith.exception.ValidationException;
import in.mohamedhalith.util.StringValidator;

public class LeaveRequest {

	private int leaveId;
	private Employee employee;
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


	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
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
		return "LeaveRequest [leaveId=" + leaveId + ", employee=" + employee + ", fromDate=" + fromDate + ", toDate="
				+ toDate + ", duration=" + duration + ", type=" + type + ", reason=" + reason + ", status=" + status
				+ ", cancelledTime=" + cancelledTime + ", reviewedTime=" + reviewedTime + ", appliedTime=" + appliedTime
				+ "]";
	}
	
}
