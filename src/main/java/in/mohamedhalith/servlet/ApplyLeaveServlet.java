package in.mohamedhalith.servlet;

import java.io.IOException;
import java.time.LocalDate;

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
 * Servlet implementation class ApplyLeaveServlet
 */
@WebServlet("/ApplyLeaveServlet")
public class ApplyLeaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		final String errorRedirect = "applyleave.jsp?errorMessage=";
		// Obtaining the parameters from the form
		try {
			String username = (String) session.getAttribute("LOGGEDIN_USERNAME");
			String employeeName = request.getParameter("employeeName");
			int employeeId = Integer.parseInt(request.getParameter("employeeId"));
			LocalDate fromDate = LocalDate.parse(request.getParameter("fromDate"));
			LocalDate toDate = LocalDate.parse(request.getParameter("toDate"));
			int duration = Integer.parseInt(request.getParameter("duration"));
			String type = request.getParameter("leaveType");
			String reason = request.getParameter("reason");

			// Converting the parameters to Leave Request
			LeaveRequest leaveRequest = new LeaveRequest();
			leaveRequest.setEmployeeName(employeeName);
			leaveRequest.setEmployeeId(employeeId);
			leaveRequest.setFromDate(fromDate);
			leaveRequest.setToDate(toDate);
			leaveRequest.setType(type);
			leaveRequest.setReason(reason);
			leaveRequest.setDuration(duration);

			// Sending to the backend manager
			boolean isApplied = LeaveRequestService.applyLeaveRequest(leaveRequest, username);
			if(isApplied) {
				String message = "Leave Applied Successfully!";
				response.sendRedirect("applyleave.jsp?infoMessage=" + message);
			}
			
		} catch (RuntimeException | ServiceException | ValidationException | IOException e) {
			e.printStackTrace();
			response.sendRedirect(errorRedirect + e.getMessage());
		}

	}

}
