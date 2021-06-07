<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Add employee</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<jsp:include page="message.jsp"></jsp:include>
	<main class="container-fluid">
		<h3>Add Employee</h3>
		<form action="AddEmployeeServlet" method="POST">
		<label for="name">Employee Name</label>
		<input type="text" name = "name" id="name" placeholder="Employee name" pattern="[A-Za-z][A-Za-z ]{2,100}" 
			onkeyup="setUsername()" autofocus required/><br/>
		<label for="employeeId">Employee Id</label>
		<input type="text" name="employeeId" id="employeeId" placeholder="Employee ID" pattern="[0-9]{4}"
			 onkeyup="setUsername()" required/><br/>
		<label for="mobileNumber">Mobile Number</label>
		<span>+91 - </span>
		<input type="text" name="mobileNumber" id="mobileNumber" placeholder="Mobile Number" pattern="[0-9]{10}"
			 required/><br/>
		<label for="email">Email</label>
		<input type="email" name="email" id="email" placeholder="Email" required/><br/>
		<label for="username">Username</label>
		<input type="text" name="username" id="username" placeholder="Username" pattern="[A-Za-z0-9]+" minlength="7"
			 required/><br/>
		<label for="password">Password</label>
		<input type="text" name="password" id="password" placeholder="Password" pattern="[A-Za-z0-9]+" minlength="8"
			 required/><br/>
		<label for="joinedDate">Joined Date</label>
		<input type="date" name="joinedDate" id="joinedDate"/><br/>
		<input type="submit" class="btn btn-success" value="Add"/>
		</form>
	</main>
	<script type="text/javascript">
	/**
	* This function sets the value of the joined date field also add boundary condition 
	* to the joined date field. Limits maximum to present day and minimum to 30 days
	* from present day. In other words, admin cannot add an employee joined before a month
	*/
	function setDate(){
		let joinedDate = document.getElementById("joinedDate");
		let today = new Date();
		let date = today.toJSON().substr(0,10);
		joinedDate.value = date;
		joinedDate.max = date;
		let pastDate = new Date();
		pastDate.setDate(pastDate.getDate() - 30);
		joinedDate.min = pastDate.toJSON().substr(0,10);
	}
	// Function call
	setDate();
	/**
	* This function generates default username and password to the employee and sets it to 
	* the fields username and password fields.
	*/
	function setUsername(){
		let name = document.getElementById("name").value.toLocaleLowerCase();
		let firstFour="";
		if(name.length >= 4){
		firstFour = name.substr(0,4);
		}else{
		let length = 4 - name.length;
		while(length){
		firstFour = firstFour+"a";
		length = length-1;
		}
		firstFour = name + firstFour;
		}
		let employeeId = document.getElementById("employeeId").value.toLocaleLowerCase();
		let secondFour="";
		if(employeeId.length >= 4){
		secondFour = employeeId.substr(0,4);
		}else{
		let length = 4 - employeeId.length;
		while(length){
		secondFour = secondFour+ length;
		length = length-1;
		}
		secondFour = employeeId + secondFour;
		}
		let username = firstFour + secondFour;
		document.querySelector("#username").value = username;
		let password = secondFour + firstFour;
		document.querySelector("#password").value = password;
		};
	</script>
</body>
</html>