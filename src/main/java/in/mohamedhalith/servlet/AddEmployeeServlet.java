package in.mohamedhalith.servlet;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.mohamedhalith.exception.ServiceException;
import in.mohamedhalith.exception.ValidationException;
import in.mohamedhalith.model.Employee;
import in.mohamedhalith.service.EmployeeService;

/**
 * Servlet implementation class AddEmployeeServlet
 */
@WebServlet("/AddEmployeeServlet")
public class AddEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name= request.getParameter("name");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password  = request.getParameter("password");
		try {
			int employeeId = Integer.parseInt(request.getParameter("employeeId"));
			long mobileNumber = Long.parseLong(request.getParameter("mobileNumber"));
			LocalDate joinedDate = LocalDate.parse(request.getParameter("joinedDate"));
			
			Employee employee = new Employee();
			employee.setName(name);
			employee.setEmail(email);
			employee.setUsername(username);
			employee.setPassword(password);
			employee.setEmployeeId(employeeId);
			employee.setMobileNumber(mobileNumber);
			employee.setJoinedDate(joinedDate);
			
			boolean isAdded = EmployeeService.addEmployee(employee);
			if(isAdded) {
				String message = "Employee Added Successfully";
				response.sendRedirect("addemployee.jsp?infoMessage="+message);
			}
		} catch (NumberFormatException | ValidationException | ServiceException | IOException e) {
			e.printStackTrace();
			response.sendRedirect("addemployee.jsp?errorMessage="+e.getMessage());
		}
		
	}

}
