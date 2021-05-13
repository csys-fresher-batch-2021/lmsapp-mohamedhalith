package in.mohamedhalith.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.mohamedhalith.validator.LoginValidator;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		String message;
		try {
			boolean admin = LoginValidator.adminVerification(username, password, role);
			boolean employee = LoginValidator.employeeVerification(username, password, role);
			if (admin) {
				message = "Admin Logged In Successfully";
				response.sendRedirect("login.jsp?infoMessage=" + message);
			} else if (employee) {
				message = "Employee Logged In Successfully";
				response.sendRedirect("login.jsp?infoMessage=" + message);
			} else {
				message = "Invalid Credentials";
				response.sendRedirect("login.jsp?infoMessage=" + message);
			}
		} catch (Exception e) {
			message = e.getMessage();
			response.sendRedirect("login.jsp?errorMessage=" + message);
		}
	}

}
