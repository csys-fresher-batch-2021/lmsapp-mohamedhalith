<%@page import="java.time.LocalDate"%>
<%@page import="in.mohamedhalith.service.LeaveRequestManager"%>
<%@page import="in.mohamedhalith.model.LeaveRequest"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Leave request applied</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<h3>Request Status</h3>
<%
List<LeaveRequest> employeeRequests = (List<LeaveRequest>) request.getAttribute("employeeRequests");
%>
	<main class="container-fluid">
		<table class="table table-bordered">
			<thead class="table-primary">
				<tr>
					<th>S.No</th>
					<th>Id</th>
					<th>Name</th>
					<th>From</th>
					<th>To</th>
					<th>Duration</th>
					<th>Status</th>
					<th>Applied on</th>
				</tr>
			</thead>
			<tbody>
				<%
				int serial = 1;
				for(LeaveRequest leaveRequest : employeeRequests){
					if(!leaveRequest.getStatus().equals("Cancelled")){
				%>
				<tr>
					<td><%= serial%></td>
					<td><%=leaveRequest.getEmployeeId() %></td>
					<td><%= leaveRequest.getEmployeeName() %></td>
					<td><%= leaveRequest.getFromDate() %></td>
					<td><%= leaveRequest.getToDate() %></td>
					<td><%= leaveRequest.getDuration() %></td>
					<td><%= leaveRequest.getStatus() %></td>
					<%
					LocalDate date = leaveRequest.getAppliedTime().toLocalDate();
					%>
					<td><%= date %></td>
				</tr>
				<%
					}
					serial+=1;
				}
				%>
			</tbody>
		</table>
	</main>
</body>
</html>