package in.mohamedhalith.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import in.mohamedhalith.exception.ServiceException;
import in.mohamedhalith.exception.ValidationException;
import in.mohamedhalith.model.LeaveRequest;
import in.mohamedhalith.service.LeaveRequestService;

/**
 * Servlet implementation class RequestStatusServlet2
 */
@WebServlet("/RequestStatusServlet2")
public class RequestStatusServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("LOGGEDIN_USERNAME");
		try {
			List<LeaveRequest> employeeRequests = LeaveRequestService.getEmployeeRequests(username);
			Gson gson = new Gson();
			String json = gson.toJson(employeeRequests);
			System.out.println(json);
			PrintWriter out = response.getWriter();
			out.print(json);
			out.flush();
		} catch (ServiceException | ValidationException | IOException e) {
			e.printStackTrace();
		}
	}
}
