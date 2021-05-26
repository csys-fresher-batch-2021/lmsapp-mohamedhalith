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
int employeeId = (Integer) session.getAttribute("employeeId");
Employee employee = EmployeeService.getEmployee(employeeId);
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
					<td><%=employee.getName() %></td>
					<td><%=employee.getEmployeeId() %></td>
					<td><%=employee.getSickLeave() %></td>
					<td><%=employee.getCasualLeave() %></td>
					<td><%=employee.getEarnedLeave() %></td>
				</tr>
			</tbody>
		</table>
	</main>
</body>
</html>