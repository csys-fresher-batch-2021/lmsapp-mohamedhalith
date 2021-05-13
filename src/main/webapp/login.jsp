<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
	<h3>Login</h3>
	<jsp:include page="message.jsp"></jsp:include>
	<form action="LoginServlet" method="POST">
		<label>Username</label> 
		<input type="text" name="username"  id="username" placeholder="Enter username" 
			pattern="[A-Za-z0-9]{7,}" autofocus required/><br/> 
		<label>Password</label>
		<input type="password" name="password" id="password" 
			pattern="[A-Za-z0-9]{8,}" placeholder="Enter password" required/><br/>
		<input type="radio" value="admin" name="role" id="admin" required/>
		<label for="role">Admin</label>
		<input type="radio" value ="employee" name="role" id="employee" required/>
		<label for="role">Employee</label><br/>
		<button class="btn btn-primary">Submit</button>
		<button class="btn btn-primary">Reset</button>
	</form>
	</main>
</body>
</html>