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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import in.mohamedhalith.exception.ServiceException;
import in.mohamedhalith.model.LeaveRequest;
import in.mohamedhalith.service.LeaveRequestService;
import in.mohamedhalith.util.LocalDateAdapter;
import in.mohamedhalith.util.LocalDateTimeAdapter;

/**
 * Servlet implementation class ListAllRequestsServlet
 */
@WebServlet("/ListAllRequestsServlet")
public class ListAllRequestsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<LeaveRequest> requestList = LeaveRequestService.getRequestList();
			Gson gson = new GsonBuilder().setPrettyPrinting()
			        .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
			        .registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
			String json = gson.toJson(requestList);
			PrintWriter out = response.getWriter();
			out.print(json);
			out.flush();
		} catch (IOException | ServiceException e) {
			e.printStackTrace();
		}
		
		
	}
}
