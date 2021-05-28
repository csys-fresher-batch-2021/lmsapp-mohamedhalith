package in.mohamedhalith.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import in.mohamedhalith.exception.ValidationException;
import in.mohamedhalith.util.StringValidator;

public class Employee {
	private String name;
	private int id;
	private int employeeId;
	private long mobileNumber;
	private String email;
	private String username;
	private String password;
	private int sickLeave;
	private int casualLeave;
	private int earnedLeave;
	private boolean status;
	private LocalDate joinedDate;
	private LocalDateTime modifiedTime;

	public String getName() {
		return name;
	}

	public void setName(String name) throws ValidationException {
		StringValidator.isValidString(name);
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		if (id < 0) {
			throw new IllegalArgumentException("Invalid id");
		}
		this.id = id;
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

	public long getMobileNumber() {
		return mobileNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setMobileNumber(String mobile) {
		if (mobile.length() != 10) {
			throw new IllegalArgumentException("Invalid mobile number");
		}
		long mobileNo = Long.parseLong(mobile);
		this.mobileNumber = mobileNo;
	}

	public void setEmail(String email) throws ValidationException {

		StringValidator.isValidEmail(email);
		this.email = email;

	}

	public int getSickLeave() {
		return sickLeave;
	}

	public void setSickLeave(int sickLeave) {
		this.sickLeave = sickLeave;
	}

	public int getCasualLeave() {
		return casualLeave;
	}

	public void setCasualLeave(int casualLeave) {
		this.casualLeave = casualLeave;
	}

	public int getEarnedLeave() {
		return earnedLeave;
	}

	public void setEarnedLeave(int earnedLeave) {
		this.earnedLeave = earnedLeave;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) throws ValidationException {

		StringValidator.isValidUsername(username);
		this.username = username;

	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) throws ValidationException {
		StringValidator.isValidPassword(password);
		this.password = password;

	}

	public boolean getStatus() {
		return status;
	}

	public LocalDate getJoinedDate() {
		return joinedDate;
	}

	public LocalDateTime getModifiedTime() {
		return modifiedTime;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void setJoinedDate(LocalDate joinedDate) {
		this.joinedDate = joinedDate;
	}

	public void setModifiedTime(LocalDateTime modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", id=" + id + ", employeeId=" + employeeId + ", mobileNumber=" + mobileNumber
				+ ", email=" + email + ", username=" + username + ", password=" + password + ", sickLeave=" + sickLeave
				+ ", casualLeave=" + casualLeave + ", earnedLeave=" + earnedLeave + ", status=" + status
				+ ", joined_date=" + joinedDate + ", modified_time=" + modifiedTime + "]";
	}
}
