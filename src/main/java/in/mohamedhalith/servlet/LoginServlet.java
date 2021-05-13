package in.mohamedhalith.servlet;

import java.io.IOException;
import java.io.PrintWriter;

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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		PrintWriter output = response.getWriter();
		try {
			boolean admin = LoginValidator.adminVerification(username, password, role);
			boolean employee = LoginValidator.employeeVerification(username, password, role);
			if (admin) {
				output.println("Successfully Logged In");
				output.println("Welcome Admin");
			} else if (employee) {
				output.println("Successfully Logged In");
				output.println("Welcome "+username);
			} else {
				String message = "Invalid Credentials";
				response.sendRedirect("login.jsp?infoMessage=" + message);
			}
		} catch (Exception e) {
			e.printStackTrace();
			String message = e.getMessage();
			response.sendRedirect("login.jsp?errorMessage=" + message);
		}
	}

}
