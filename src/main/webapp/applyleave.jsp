<%@page import="in.mohamedhalith.model.Employee"%>
<%@page import="in.mohamedhalith.service.EmployeeService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Apply for Leave</title>
</head>
<body>
<%
int employeeId = (Integer) session.getAttribute("employeeId");
Employee employee = EmployeeService.getEmployee(employeeId);
if(employee!=null){
%>
<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
		<div class="row">
			<div class="col-sm">
				<h4>Apply for Leave</h4>
				<jsp:include page="message.jsp"></jsp:include>
				<form action="ApplyLeaveServlet" method="POST">
					<label>Employee Name</label>
					<input type="text" name="employeeName" id="employeeName" value="<%=employee.getName() %>" 
						readonly required/><br/>
						
					<label>Employee Id</label>
					<input type="text" name="employeeId" id="employeeId" value="<%= employee.getEmployeeId() %>"
						readonly required/><br/>
						
					<label>From Date</label>
					<input type="date" name="fromDate" id="fromDate" required /><br/>
					
					<label>To Date</label>
					<input type="date" name="toDate" id="toDate" required oninput="findDuration()"/> <br/>
					
					<label>Leave Duration</label>
					<input type="text" name="duration" id="duration" readonly/><br/>
					
					<label>Type</label>
					<select name="leaveType" required>
					<option value="" disabled selected>-----SELECT LEAVE TYPE-----</option>
					<option value="SickLeave">Sick Leave</option>
					<option value="CasualLeave">Casual Leave</option>
					<option value="EarnedLeave">Earned Leave</option>
					</select><br/>
					
					<label>Reason</label>
					<textarea name="reason" required></textarea><br/>
					<input type="submit">
				</form>
			</div>
			<div class="col-sm">
				<h4>LeaveBalance</h4>
				<jsp:include page="leavebalance.jsp"></jsp:include>
			</div>
		</div>
	</main>
<% } %>
<script type="text/javascript">
let date = new Date().toJSON().substr(0,10);
let fromDate = document.getElementById("fromDate");
let toDate = document.getElementById("toDate");
fromDate.setAttribute("min",date);
fromDate.setAttribute("value",date);
toDate.setAttribute("min",date);
toDate.setAttribute("value",date);
let duration = document.getElementById("duration");
duration.setAttribute("value",1);
fromDate.addEventListener('input',function(){
	let day = fromDate.value;
	toDate.setAttribute("min",day);
	toDate.setAttribute("value",day);
	findDuration();
	});
function findDuration(){
	let endDate = new Date(toDate.value);
	let startDate = new Date(fromDate.value);
	let difference = endDate.getTime() - startDate.getTime();
    difference = difference/(24*3600*1000);
    duration.setAttribute("value",difference+1);
	};
</script>
</body>
</html>