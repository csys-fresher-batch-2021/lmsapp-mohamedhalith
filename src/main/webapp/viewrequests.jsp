<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Approve/Reject</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
		<h3>Leave Requests</h3>
		<input type="radio" name="filter" id="all" onclick="getRequests(null)" />
		<label for="all">All</label>
		 <input type="radio" name="filter" id="pending"	onclick="getRequests('waiting for approval')" /> 
		<label for="pending">Pending</label>
		<input type="radio" name="filter" id="cancelled" onclick="getRequests('cancelled')" />
		<label for=cancelled>Cancelled</label>
		<input type="radio" name="filter" id="approved" onclick="getRequests('approved')" />
		<label for="approved">Approved</label> 
		<input type="radio" name="filter" id="rejected" onclick="getRequests('rejected')" /> 
		<label for="rejected">Rejected</label> <br />
		<span style="color: green" id="message"></span>
		<table id="requestTable" class="table table-bordered">
			<caption>All leave requests</caption>
			<thead id="table-head">
				<tr>
					<th id="serial">S.No</th>
					<th id="employeeId">Emp. Id</th>
					<th id="name">Name</th>
					<th id="fromDate">From Date</th>
					<th id="toDate">To Date</th>
					<th id="duration">Duration</th>
					<th id="reason">Reason</th>
					<th id="status">Status</th>
					<th id="appliedTime">Applied at</th>
					<th colspan = "2"id="action"></th>
				</tr>
			</thead>
			<tbody id="table-body">

			</tbody>
		</table>
	</main>
	<script type="text/javascript">

function getRequests(arg){
let url="ListAllRequestsServlet";
/**
 * This function fetches data from the servlet and displays the response from the servlet
 */
fetch(url).then(res=> res.json()).then(res=>{
	let requests = res;
	let serial = 1;
	let content = "";
		for(let request of requests){
			if(arg === null || request.status === arg){
			content += "<tr><td>" + serial + "</td>"
			+"<td>" + request.employee.employeeId + "</td>"
			+"<td>" + request.employee.name+"</td>"
			+"<td>" + request.fromDate+"</td>"
			+"<td>" + request.toDate+"</td>"
			+"<td>" + request.duration+"</td>"
			+"<td>" + request.reason+"</td>"
			+"<td>" + request.status+"</td>"
			+"<td>" + request.appliedTime+"</td>"
			serial +=1;
			}
			
			if(arg==='waiting for approval' && arg === request.status){
				content +=
				"<td><button class=\"btn btn-success\" onclick=\"update('approve',"+request.leaveId+","+request.employee.employeeId+")\">Approve</button></td>"
				+"<td><button class=\"btn btn-danger\" onclick=\"update('reject',"+request.leaveId+","+request.employee.employeeId+")\">Reject</button></td></tr>"
			}else{
				content += "</tr>";
			}
		}
	document.querySelector("#table-body").innerHTML = content;
});
}
getRequests(null);
/**
 * This function is used to perform the action indicated by the user
 */
function update(key,leaveId,employeeId){
	const params = "?action="+key+"&leaveId="+leaveId+"&employeeId="+employeeId;
	let url = "ApproveRejectServlet" + params;
	fetch(url).then(res=> res.json()).then(res=>{
		let message = res;
		if(message && key === 'approve'){
			message = "Approved leave request";
		}else if(message && key === 'reject') {
			message = "Rejected leave Request";
		}else{
			message = "Failed to perform selected action";
			document.querySelector("#message").setAttribute("style","color:red");
		}
		document.querySelector("#message").innerHTML = message;
		getRequests('waiting for approval');
	});
}
</script>
</body>
</html>