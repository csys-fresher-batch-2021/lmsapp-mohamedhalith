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
import in.mohamedhalith.service.LeaveRequestService;

/**
 * Servlet implementation class CancelRequestServlet
 */
@WebServlet("/CancelRequestServlet")
public class CancelRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("ListWaitingRequestServlet");
		try {
			int employeeId = (Integer) session.getAttribute("employeeId") ;
			int leaveId = Integer.parseInt(request.getParameter("leaveId"));
			boolean isCancelled = LeaveRequestService.cancelLeaveRequest(leaveId,employeeId);
			if(isCancelled) {
				String message = "Cancelled the request";
				request.setAttribute("infoMessage", message);
			}
		} catch (NumberFormatException | ServiceException | ValidationException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage",e.getMessage());
		}finally {
			requestDispatcher.forward(request, response);
		}
	}

}
