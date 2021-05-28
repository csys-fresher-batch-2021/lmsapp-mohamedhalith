package in.mohamedhalith.servlet;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.mohamedhalith.exception.ServiceException;
import in.mohamedhalith.exception.ValidationException;
import in.mohamedhalith.model.Employee;
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
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("applyleave.jsp");
		// Obtaining the parameters from the form
		try {
			String employeeName = request.getParameter("employeeName");
			int employeeId = Integer.parseInt(request.getParameter("employeeId"));
			LocalDate fromDate = LocalDate.parse(request.getParameter("fromDate"));
			LocalDate toDate = LocalDate.parse(request.getParameter("toDate"));
			int duration = Integer.parseInt(request.getParameter("duration"));
			String type = request.getParameter("leaveType");
			String reason = request.getParameter("reason");

			// Converting the parameters to Leave Request
			LeaveRequest leaveRequest = new LeaveRequest();
			leaveRequest.setFromDate(fromDate);
			leaveRequest.setToDate(toDate);
			leaveRequest.setType(type);
			leaveRequest.setReason(reason);
			leaveRequest.setDuration(duration);
			Employee employee = new Employee();
			employee.setName(employeeName);
			employee.setEmployeeId(employeeId);
			leaveRequest.setEmployee(employee);

			// Sending to the backend manager
			boolean isApplied = LeaveRequestService.applyLeaveRequest(leaveRequest, employeeId);
			if(isApplied) {
				String message = "Leave Applied Successfully!";
				request.setAttribute("infoMessage", message);
			}
			
		} catch (RuntimeException | ServiceException | ValidationException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", e.getMessage());
		}finally {
			requestDispatcher.forward(request, response);
		}
	}

}
