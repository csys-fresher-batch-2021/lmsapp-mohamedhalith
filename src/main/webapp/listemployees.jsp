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
		<div>
			<div style="float:left" class="form-group">
			<label for="alphabetical" class="input-group-prepend">Sort by</label>
			<select name="alphabetical" id="alphabetical" onchange="getEmployees()"  class="custom-select">
			<option value="a" selected>A-Z</option>
			<option value="z">Z-A</option>
			</select>
			</div>
			<div style="float:right" class="form-group">
			<input type="text" id="search" name="search" oninput="getEmployees()" placeholder="Search" class="form-control form-rounded">
			</div>
		</div>
		<table class="table table-bordered">
		<caption>List of Employees</caption>
		<thead>
		<tr>
		<th id="serial">S.No</th>
		<th id="id">Id</th>
		<th id="name">Name</th>
		<th id="viewbtn"></th>
		</thead>
		<tbody id="table-body">
		</tbody>
		</table>
	</main>
	<script type="text/javascript">
	function getEmployees(){
		let url = "ListEmployeeServlet";
		let content = "";
		let val = document.querySelector("#alphabetical").value;
		let searchKey = document.querySelector("#search").value.trim();
		fetch(url).then(res => res.json()).then(res =>{
			let employees = res;
			let serial = 1;
			if(val != null){
				employees = sort(employees,val);
			}
			if(search != null){
				employees = search(employees,searchKey);
			}
			for(employee of employees){
				content += "<tr>" +
				"<td>" + serial + "</td>" +
				"<td>" + employee.employeeId + "</td>"+
				"<td>" + employee.name + "</td>"+
				"<td><a class=\"btn btn-info\" href=\"employeeDetails.jsp?employeeId="+employee.employeeId+"\">View</a>"+ 
				"</tr>";
				serial +=1;
			}
			document.querySelector("#table-body").innerHTML = content;
		})
	}
	getEmployees();
	
	function sort(res,value){
		res.sort(function(a,b){
			a = a.name.toLowerCase();
		  	b = b.name.toLowerCase();
		  	  return a < b ? -1 : a > b ? 1 : 0;
		})
		if(value === "a"){
			res = res;
		}else{
			res = res.reverse();
		}
		return res;
	}
	function search(res,value){
		res = res.filter(res => res.name.toLowerCase().includes(value.toLowerCase()) ||
				res.employeeId.toString().includes(value.toLowerCase()));
		return res;
	}
	</script>
</body>
</html>