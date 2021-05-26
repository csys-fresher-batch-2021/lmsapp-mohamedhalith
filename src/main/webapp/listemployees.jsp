<%@page import="in.mohamedhalith.service.EmployeeService"%>
<%@page import="in.mohamedhalith.model.Employee"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Employees</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
		<h3>List Employees</h3>
		<table class="table table-bordered">
		<caption>List of Employees</caption>
		<thead>
		<tr>
		<th id="serial">S.No</th>
		<th id="id">Id</th>
		<th id="name">Name</th>
		</thead>
		<tbody>
		<%
		int serial = 1;
				List<Employee> employeeList = (List<Employee>) request.getAttribute("employeeList");
				if(employeeList != null){
				for(Employee employee : employeeList){
		%>
		<tr>
		<td><%=serial%></td>
		<td><%=employee.getEmployeeId() %></td>
		<td><%=employee.getName() %></td>
		<%
				}
		serial++;
		}
		%>
		</tbody>
		</table>
	</main>
</body>
</html>