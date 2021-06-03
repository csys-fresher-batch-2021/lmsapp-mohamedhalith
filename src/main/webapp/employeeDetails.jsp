<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Employee Details</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
		<h3>Employee Details</h3>
		<table class="table table-bordered">
			<caption>Employee's details</caption>
			<thead class="table table-primary">
				<tr>
					<th id="category">Category</th>
					<th id="details">Details</th>
				</tr>
			</thead>
			<tbody id="table-body">
			</tbody>
		</table>
		<button onclick="confirmAction()" class="btn btn-danger">Remove</button>
	</main>
	<script type="text/javascript">
		var params = window.location.search;
		function getDetails(){
			let url = "EmployeeDetailsServlet" + params;
			let content = "";
			fetch(url).then(res=>res.json()).then(res=>{
				let employee = res;
				console.log(employee);
				content += "<tr><th>Name</th><td>"+employee.name+"</td></tr>"+
				"<tr><th>Employee Id</th><td>"+employee.employeeId+"</td></tr>"+
				"<tr><th>Mobile Number</th><td>"+employee.mobileNumber+"</td></tr>"+
				"<tr><th>Email</th><td>"+employee.email+"</td></tr>";
				document.querySelector("#table-body").innerHTML = content;
			});
		}
		getDetails();
		function confirmAction(){
			let answer = window.confirm("Do you want to remove this employee");
			console.log(answer);
			if(answer){
				removeEmployee();
			}else{
				console.log("Cancelled the operation");
			}
		}
		function removeEmployee(){
			let url = "RemoveEmployeeServlet" + params;
			fetch(url).then(res=>res.json()).then(res=>{
				let result = res;
				if(result){
					alert("Successfully removed");
					window.location.replace("ListEmployeeServlet");
				}else{
					alert("Failed to remove");
				}
			}).catch(err=>{
				console.log(err.data);
			});
		}
	</script>
</body>
</html>