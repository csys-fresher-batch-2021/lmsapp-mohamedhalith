<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<style>
span{
margin-left : -30px;
}
</style>
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
			pattern="[A-Za-z0-9]{8,}" placeholder="Enter password" required/>
		<span class="fas fa-eye-slash" id="eyefull"></span>
		<br/>
		<input type="radio" value="admin" name="role" id="admin" required/>
		<label for="role">Admin</label>
		<input type="radio" value ="employee" name="role" id="employee" required/>
		<label for="role">Employee</label><br/>
		<button class="btn btn-primary">Submit</button>
		<button class="btn btn-primary">Reset</button>
	</form>
	</main>
	<script type="text/javascript">
	let eyefull = document.getElementById('eyefull');
	let password = document.getElementById("password");
	eyefull.addEventListener('click',function(){
		let type = password.getAttribute('type') === 'password' ?'text' : 'password';
		password.setAttribute('type',type);
		let className = eyefull.getAttribute('class') === 'fas fa-eye' ? 'fas fa-eye-slash' : 'fas fa-eye';
		eyefull.setAttribute('class',className);
	});
	</script>
</body>
</html>