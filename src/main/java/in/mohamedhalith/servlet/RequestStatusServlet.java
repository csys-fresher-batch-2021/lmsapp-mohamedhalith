package in.mohamedhalith.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.mohamedhalith.exception.ServiceException;
import in.mohamedhalith.exception.ValidationException;
import in.mohamedhalith.model.LeaveRequest;
import in.mohamedhalith.service.LeaveRequestManager;

/**
 * Servlet implementation class RequestStatusServlet
 */
@WebServlet("/RequestStatusServlet")
public class RequestStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("LOGGEDIN_USERNAME");
		System.out.println(username);
		try {
			List<LeaveRequest> employeeRequests = LeaveRequestManager.getEmployeeRequests(username);
			System.out.println(employeeRequests.size());
			request.setAttribute("employeeRequests", employeeRequests);
			RequestDispatcher dispatcher = request.getRequestDispatcher("viewrequeststatus.jsp");
			dispatcher.forward(request, response);
		} catch (ServiceException | ValidationException e) {
			e.printStackTrace();
		} 
	}

}