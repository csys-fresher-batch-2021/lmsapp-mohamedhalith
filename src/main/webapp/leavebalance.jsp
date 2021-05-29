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
<%
LeaveBalance employeeLeaveBalance = (LeaveBalance) session.getAttribute("leavebalance");
%>
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
			<tbody>
				<tr>
					<td><%=employeeLeaveBalance.getEmployee().getName() %></td>
					<td><%=employeeLeaveBalance.getEmployee().getEmployeeId() %></td>
					<td><%=employeeLeaveBalance.getSickLeave() %></td>
					<td><%=employeeLeaveBalance.getCasualLeave() %></td>
					<td><%=employeeLeaveBalance.getEarnedLeave() %></td>
				</tr>
			</tbody>
		</table>
	</main>
</body>
</html>