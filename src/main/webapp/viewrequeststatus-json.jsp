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
	<script src="js/viewrequest-json-component.js">	</script>
</body>
</html>