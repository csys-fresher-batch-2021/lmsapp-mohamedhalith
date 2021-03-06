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
import in.mohamedhalith.service.LeaveRequestService;

/**
 * Servlet implementation class ListWaitingRequestServlet
 */
@WebServlet("/ListWaitingRequestServlet")
public class ListWaitingRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int employeeId = (Integer) session.getAttribute("employeeId");
		String infoMessage = (String) request.getAttribute("infoMessage");
		String errorMessage = (String) request.getAttribute("errorMessage");
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("cancelrequest.jsp");

		if(infoMessage != null) {
			request.setAttribute("infoMessage", infoMessage);
		}
		if(errorMessage != null) {
			request.setAttribute("errorMessage", errorMessage);
		}
		try {
			List<LeaveRequest> requestList = LeaveRequestService.getUnapprovedRequest(employeeId);
			request.setAttribute("waitingRequests", requestList);
			requestDispatcher.forward(request, response);
		} catch (ServiceException | ValidationException e) {
			e.printStackTrace();
		}
	}

}
