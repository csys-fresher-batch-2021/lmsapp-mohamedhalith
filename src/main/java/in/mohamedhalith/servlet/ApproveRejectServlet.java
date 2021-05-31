package in.mohamedhalith.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import in.mohamedhalith.exception.ServiceException;
import in.mohamedhalith.exception.ValidationException;
import in.mohamedhalith.service.LeaveRequestService;

/**
 * Servlet implementation class ApproveRejectServlet
 */
@WebServlet("/ApproveRejectServlet")
public class ApproveRejectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String value = request.getParameter("action");
		try {
			int leaveId = Integer.parseInt(request.getParameter("leaveId"));
			int employeeId = Integer.parseInt(request.getParameter("employeeId"));
			boolean output =LeaveRequestService.updateLeaveRequest(value, leaveId, employeeId);
			PrintWriter out = response.getWriter();
			Gson gson = new Gson();
			String json = gson.toJson(output);
			out.print(json);
			out.flush();
		} catch (NumberFormatException | ServiceException | ValidationException | IOException e) {
			e.printStackTrace();
		}
	}
}
