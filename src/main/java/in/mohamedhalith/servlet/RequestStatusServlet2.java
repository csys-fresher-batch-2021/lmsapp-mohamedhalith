package in.mohamedhalith.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import in.mohamedhalith.exception.ServiceException;
import in.mohamedhalith.exception.ValidationException;
import in.mohamedhalith.model.LeaveRequest;
import in.mohamedhalith.service.LeaveRequestService;
import in.mohamedhalith.util.LocalDateAdapter;
import in.mohamedhalith.util.LocalDateTimeAdapter;

/**
 * Servlet implementation class RequestStatusServlet2
 */
@WebServlet("/RequestStatusServlet2")
public class RequestStatusServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("LOGGEDIN_USERNAME");
		try {
			List<LeaveRequest> employeeRequests = LeaveRequestService.getEmployeeRequests(username);
			Gson gson = new GsonBuilder().setPrettyPrinting()
			        .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
			        .registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
			String json = gson.toJson(employeeRequests);
			PrintWriter out = response.getWriter();
			out.print(json);
			out.flush();
		} catch (ServiceException | ValidationException | IOException e) {
			e.printStackTrace();
		}
	}
}
