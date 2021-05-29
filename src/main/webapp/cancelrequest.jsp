<%@page import="in.mohamedhalith.model.LeaveRequest"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Cancel leave request</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
		<h3>Cancel a leave</h3>
		<jsp:include page="message.jsp"></jsp:include>
		<table class="table table-bordered">
		<caption>Table of unapproved requests</caption>
			<thead class="table table-primary">
				<tr>
					<th id="serial">S.No</th>
					<th id ="id">Id</th>
					<th id="name">Name</th>
					<th id="fromDate">From Date</th>
					<th id="toDate">To Date</th>
					<th id="reason">Reason</th>
					<th id="type">Type</th>
					<th id="status">Status</th>
					<th id="cancel"></th>
				</tr>
			</thead>
			<tbody>
				<%
				int serial = 1;
				List<LeaveRequest> requestList = (List<LeaveRequest>) request.getAttribute("waitingRequests");
				if(requestList == null){
				%>
				<tr><td style="text-align:center" colspan="9"> No waiting requests to cancel </td></tr>
				<%
				}else{
				for(LeaveRequest leaveRequest : requestList){
				%>
				<tr>
				<td><%= serial %>
				<td><%=leaveRequest.getEmployee().getEmployeeId() %></td>
				<td><%=leaveRequest.getEmployee().getName() %></td>
				<td><%=leaveRequest.getFromDate() %></td>
				<td><%=leaveRequest.getToDate() %></td>
				<td><%=leaveRequest.getReason()%></td>
				<td><%=leaveRequest.getType()%></td>
				<td><%=leaveRequest.getStatus() %></td>
				<td class="btn btn-danger"><a href = "CancelRequestServlet?leaveId=<%=leaveRequest.getLeaveId() %>">Cancel</a></td>
				</tr>
				<%
					serial+=1;
					}
				}
				%>
			</tbody>
		</table>
	</main>
</body>
</html>