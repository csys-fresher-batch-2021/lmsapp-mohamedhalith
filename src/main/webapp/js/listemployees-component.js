/**
 * This method is used to get list of employees
 */
function getEmployees() {
	let url = "ListEmployeeServlet";
	let content = "";
	let val = document.querySelector("#alphabetical").value;
	let searchKey = document.querySelector("#search").value.trim();
	fetch(url).then(res => res.json()).then(res => {
		let employees = res;
		let serial = 1;
		if (val != null) {
			employees = sort(employees, val);
		}
		if (search != null) {
			employees = search(employees, searchKey);
		}
		for (employee of employees) {
			content += "<tr>" +
				"<td>" + serial + "</td>" +
				"<td>" + employee.employeeId + "</td>" +
				"<td>" + employee.name + "</td>" +
				"<td><a class=\"btn btn-info\" href=\"employeeDetails.jsp?employeeId=" + employee.employeeId + "\">View</a></td>" +
				"</tr>";
			serial += 1;
		}
		document.querySelector("#table-body").innerHTML = content;
	})
}
getEmployees();

/**
This method is used to sort the list of employees alphabetically
 */
function sort(res, value) {
	res.sort(function(a, b) {
		a = a.name.toLowerCase();
		b = b.name.toLowerCase();
		return a < b ? -1 : a > b ? 1 : 0;
	})
	if (value === "a") {
		res = res;
	} else {
		res = res.reverse();
	}
	return res;
}
/**
This method is used to search for an employee by name or employee id
 */
function search(res, value) {
	res = res.filter(res => res.name.toLowerCase().includes(value.toLowerCase()) ||
		res.employeeId.toString().includes(value.toLowerCase()));
	return res;
}