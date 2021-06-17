/**
 * This method is used to obtain the remaining leave balance of employee
 */
function getLeaveBalance() {
	let url = "LeaveBalanceServlet";
	let content = "";
	fetch(url).then(res => res.json()).then(res => {
		let leaveBalance = res;
		console.log(leaveBalance);
		console.log(leaveBalance.employee.name);
		content = "<tr><td>" +
			leaveBalance.employee.name + "</td><td>" +
			leaveBalance.employee.employeeId + "</td><td>" +
			leaveBalance.sickLeave + "</td><td>" +
			leaveBalance.casualLeave + "</td><td>" +
			leaveBalance.earnedLeave + "</td></tr>"
		console.log(content);
		document.querySelector("#table-body").innerHTML = content;
	})
}
getLeaveBalance();