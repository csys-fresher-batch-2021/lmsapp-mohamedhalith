package in.mohamedhalith.service;

import java.util.ArrayList;
import java.util.List;

import in.mohamedhalith.model.Employee;

public class EmployeeManager {
	
	private EmployeeManager() {
		//Default Constructor
	}

	private static List<Employee> employeeList = new ArrayList<>();
	
	static {
		Employee employee1 = new Employee("Mohamed",2627);
		employeeList.add(employee1);
		
		Employee employee2 = new Employee("Halith",2728);
		employeeList.add(employee2);
	}
	
	/**
	 * This method is used to return the list of employees
	 * @return List<Employee>
	 */
	public static List<Employee> getEmployeeList() {
		return employeeList;
	}
	
}
