package in.mohamedhalith.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.mohamedhalith.exception.ServiceException;
import in.mohamedhalith.exception.ValidationException;
import in.mohamedhalith.model.LeaveBalance;
import in.mohamedhalith.service.EmployeeService;

/**
 * Servlet implementation class LeaveBalanceServlet
 */
@WebServlet("/LeaveBalanceServlet")
public class LeaveBalanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int employeeId = (Integer) session.getAttribute("employeeId");
		RequestDispatcher dispatcher = null;
		String key = request.getParameter("redirect");
		try {
			LeaveBalance employeeLeaveBalance = EmployeeService.findLeaveBalance(employeeId);
			session.setAttribute("leavebalance", employeeLeaveBalance);
			if(key.equalsIgnoreCase("view")) {
				dispatcher = request.getRequestDispatcher("viewbalance.jsp");
			}else if(key.equalsIgnoreCase("apply")) {
				dispatcher = request.getRequestDispatcher("applyleave.jsp");				
			}
			if(dispatcher != null) {
				dispatcher.forward(request, response);
			}
		} catch (ServiceException |NullPointerException | ValidationException e) {
			e.printStackTrace();
		}
		
	}

}
