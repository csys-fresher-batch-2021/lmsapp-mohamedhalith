<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Request status</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
		<h3>Request Status</h3>
		<table class="table table-bordered">
			<caption>Leave Requests</caption>
			<thead class="table-primary">
				<tr>
					<th id="serial">S.No</th>
					<th id="employeeId">Id</th>
					<th id="employeeName">Name</th>
					<th id="fromDate">From</th>
					<th id="toDate">To</th>
					<th id="duration">Duration</th>
					<th id="status">Status</th>
					<th id="appliedTime">Applied on</th>
				</tr>
			</thead>
			<tbody id="table-body">

			</tbody>
		</table>
	</main>
	<script type="text/javascript">
		function getAllRequests(){
			console.log("Fetching");
			let url = "RequestStatusServlet2";
			fetch(url).then(res => res.json()).then(res=>{
				let requests = res;
				console.log(requests);
				let content ="";
				let serial = 1;
				for(let request of requests){
					console.log(request.fromDate);
					content += 
					"<tr><td>" + serial + "</td><td>" + request.employeeName + "</td><td>" + request.employeeId + "</td><td>" + request.fromDate + "</td><td>" + request.toDate +"</td><td>" + request.duration + "</td><td>" + request.status + "</td><td>" + request.appliedTime + "</td></tr>";
					console.log(content);
					serial++; 
				}
				
				document.querySelector("#table-body").innerHTML = content;
			})
		}
		getAllRequests();
	</script>
</body>
</html>