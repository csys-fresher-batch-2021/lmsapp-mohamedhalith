package in.mohamedhalith.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import in.mohamedhalith.exception.ServiceException;
import in.mohamedhalith.exception.ValidationException;
import in.mohamedhalith.model.LeaveBalance;
import in.mohamedhalith.service.LeaveBalanceService;

/**
 * Servlet implementation class LeaveBalanceServlet
 */
@WebServlet("/LeaveBalanceServlet")
public class LeaveBalanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		int employeeId = (Integer) session.getAttribute("employeeId");
		try {
			LeaveBalance employeeLeaveBalance = LeaveBalanceService.findLeaveBalance(employeeId);
			Gson gson = new Gson();
			String json = gson.toJson(employeeLeaveBalance);
			PrintWriter out = response.getWriter();
			out.print(json);
			out.flush();
		} catch (ServiceException | NullPointerException | ValidationException e) {
			e.printStackTrace();
		}
	}

}
