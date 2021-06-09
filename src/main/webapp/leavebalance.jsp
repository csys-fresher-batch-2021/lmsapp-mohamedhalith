<%@page import="in.mohamedhalith.model.LeaveBalance"%>
<%@page import="in.mohamedhalith.model.Employee"%>
<%@page import="in.mohamedhalith.service.EmployeeService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Leave Balance</title>
</head>
<body>
	<main class="container-fluid">
		<table class="table table-bordered">
			<caption>Remaining Leaves</caption>
			<thead class="table-primary">
				<tr>
					<th id="name">Name</th>
					<th id="employeeid">Id</th>
					<th id="sickleave">Sick Leave</th>
					<th id="casualleave">Casual Leave</th>
					<th id="earnedleave">Earned Leave</th>
				</tr>
			</thead>
			<tbody id="table-body">
				
			</tbody>
		</table>
	</main>
	<script type="text/javascript">
	function getLeaveBalance(){
		let url = "LeaveBalanceServlet";
		let content = "";
		fetch(url).then(res => res.json()).then(res =>{
			let leaveBalance = res;
			console.log(leaveBalance);
			console.log(leaveBalance.employee.name);
			content = "<tr>"+
			"<td>"+leaveBalance.employee.name+"</td>"+
			"<td>"+leaveBalance.employee.employeeId+"</td>"+
			"<td>"+leaveBalance.sickLeave+"</td>"+
			"<td>"+leaveBalance.casualLeave+"</td>"+
			"<td>"+leaveBalance.earnedLeave+"</td>"+
			"</tr>"
			console.log(content);
			document.querySelector("#table-body").innerHTML = content;
		})
	}
	getLeaveBalance();
	</script>
</body>
</html>