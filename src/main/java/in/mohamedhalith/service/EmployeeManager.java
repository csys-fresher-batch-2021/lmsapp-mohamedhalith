package in.mohamedhalith.service;

import java.util.ArrayList;
import java.util.List;

import in.mohamedhalith.model.Employee;

public class EmployeeManager {

	private static List<Employee> employeeList = new ArrayList<Employee>();
	
	static {
		Employee employee1 = new Employee();
		employee1.setName("Mohamed");
		employee1.setId(2627);
		employeeList.add(employee1);
		
		Employee employee2 = new Employee();
		employee2.setName("Halith");
		employee2.setId(2628);
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
