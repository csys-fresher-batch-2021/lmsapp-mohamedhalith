package in.mohamedhalith.model;

public class Employee {
	private String name;
	private int id;
	private int sickLeave = 2;
	private int casualLeave = 2;
	private int earnLeave = 1;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		if(name==null || name.trim().isEmpty()) {
			throw new IllegalArgumentException("Name cannot be empty or null");
		}
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		if(id<999||id>9999) {
			throw new IllegalArgumentException("Employee Id must be a 4-digit Positive number");
		}
		this.id = id;
	}
	public int getSickLeave() {
		return sickLeave;
	}
	public void setSickLeave(int sickLeave) {
		if(sickLeave <0 || sickLeave >3) {
			throw new IllegalArgumentException("Invalid no. of sickleaves");
		}
		this.sickLeave = sickLeave;
	}
	public int getCasualLeave() {
		return casualLeave;
	}
	public void setCasualLeave(int casualLeave) {
		if(casualLeave <0 || casualLeave >3) {
			throw new IllegalArgumentException("Invalid no. of sickleaves");
		}
		this.casualLeave = casualLeave;
	}
	public int getEarnLeave() {
		return earnLeave;
	}
	public void setEarnLeave(int earnLeave) {
		if(earnLeave <0 || earnLeave >1) {
			throw new IllegalArgumentException("Invalid no. of sickleaves");
		}
		this.earnLeave = earnLeave;
	}
	public String toString() {
		return "Employee [Name" + name + "Id" + id + "SickLeave" + sickLeave 
				+"Casual Leave" + casualLeave +"Earn Leave" + earnLeave +"]";
	}
}
