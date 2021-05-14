<%@page import="in.mohamedhalith.model.Employee"%>
<%@page import="in.mohamedhalith.service.EmployeeManager"%>
<link rel="stylesheet" href="assets/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/fontawesome.min.css">
<link rel="stylesheet" href="assets/css/style.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
<header>
<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
  <a class="navbar-brand" href="index.jsp">Leave Management System</a>
  <button class="navbar-toggler d-lg-none" type="button" data-toggle="collapse" data-target="#collapsibleNavId" aria-controls="collapsibleNavId"
      aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="collapsibleNavId">
    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
      <li class="nav-item active">
        <a class="nav-link" href="index.jsp">Home <span class="sr-only">(current)</span></a>
      </li>
      <%
      String loggedInUsername = (String)session.getAttribute("LOGGEDIN_USERNAME");
      String role = (String)session.getAttribute("ROLE");
      if(loggedInUsername!=null && role != null && role.equalsIgnoreCase("admin")){
      %>
      <li class="nav-item">
        <a class="nav-link" href="listemployees.jsp">Employees</a>
      </li>
      <%}else if(loggedInUsername != null && role != null && role.equalsIgnoreCase("employee")){%>
      <li class="nav-item">
        <a class="nav-link" href="viewbalance.jsp">Leave Balance</a>
      </li>
      <%} %>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="dropdownId" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Dropdown</a>
        <div class="dropdown-menu" aria-labelledby="dropdownId">
          <a class="dropdown-item" href="#">Action 1</a>
          <a class="dropdown-item" href="#">Action 2</a>
        </div>
      </li>
    </ul>
    <%
    if(loggedInUsername == null){
    %>
     <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
      <li class="nav-item active">
        <a class="nav-link" href="login.jsp">Login</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Register</a>
      </li>
      </ul>
   <% }else if(role != null && role.equalsIgnoreCase("employee")){
	   Employee employee = EmployeeManager.getEmployee(loggedInUsername);
	   if(employee!=null){
	%>
	  <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
	     <li class="nav-item active">
	       <a class="nav-link" href="login.jsp">Welcome <%=employee.getName() %></a>
	     </li>
	     <li class="nav-item">
	       <a class="nav-link" href="LogoutServlet">Logout</a>
	     </li>
      </ul>
      <% }
      }else if(role != null && role.equalsIgnoreCase("admin")){ %>
      <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
	     <li class="nav-item active">
	       <a class="nav-link" href="login.jsp">Welcome Admin</a>
	     </li>
	     <li class="nav-item">
	       <a class="nav-link" href="LogoutServlet">Logout</a>
	     </li>
      </ul>
      <% } %>
      
  </div>
</nav>
</header>