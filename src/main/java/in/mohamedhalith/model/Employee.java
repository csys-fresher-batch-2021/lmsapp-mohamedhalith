package in.mohamedhalith.model;

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
	private int sickLeave = 2;
	private int casualLeave = 2;
	private int earnedLeave = 1;

	// 2 Argument Constructor
	public Employee(String name, int id) throws ValidationException {
		setName(name);
		setId(id);
	}

	// Default Constructor
	public Employee() {

	}

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
		long mobileNumber = Long.parseLong(mobile);
		this.mobileNumber = mobileNumber;
	}

	public void setEmail(String email) throws ValidationException {

		StringValidator.isValidEmail(email);
		this.email = email;

	}

	public int getSickLeave() {
		return sickLeave;
	}

	public void setSickLeave(int sickLeave) {
		if (sickLeave < 0 || sickLeave > 3) {
			throw new IllegalArgumentException("Invalid no. of sick leaves");
		}
		this.sickLeave = sickLeave;
	}

	public int getCasualLeave() {
		return casualLeave;
	}

	public void setCasualLeave(int casualLeave) {
		if (casualLeave < 0 || casualLeave > 3) {
			throw new IllegalArgumentException("Invalid no. of casual leaves");
		}
		this.casualLeave = casualLeave;
	}

	public int getEarnedLeave() {
		return earnedLeave;
	}

	public void setEarnedLeave(int earnedLeave) {
		if (earnedLeave < 0 || earnedLeave > 1) {
			throw new IllegalArgumentException("Invalid no. of earned leaves");
		}
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

	@Override
	public String toString() {
		return "Employee [Name " + name + " Id " + id + " Username " + username + " Password " + password
				+ " Sick Leave " + sickLeave + " Casual Leave " + casualLeave + " Earned Leave " + earnedLeave + "]";
	}
}
