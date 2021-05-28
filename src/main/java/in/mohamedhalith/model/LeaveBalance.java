package in.mohamedhalith.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class LeaveBalance implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private Employee employee;
	private int sickLeave;
	private int casualLeave;
	private int earnedLeave;
	private LocalDateTime modifiedTime;
	
	public int getId() {
		return id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public LocalDateTime getModifiedTime() {
		return modifiedTime;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public void setModifiedTime(LocalDateTime modifiedTime) {
		this.modifiedTime = modifiedTime;
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

	@Override
	public String toString() {
		return "LeaveBalance [id=" + id + ", employee=" + employee + ", sickLeave=" + sickLeave + ", casualLeave="
				+ casualLeave + ", earnedLeave=" + earnedLeave + ", modifiedTime=" + modifiedTime + "]";
	}
}
