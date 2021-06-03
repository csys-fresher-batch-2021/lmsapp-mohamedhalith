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
import in.mohamedhalith.service.EmployeeService;

/**
 * Servlet implementation class RemoveEmployeeServlet
 */
@WebServlet("/RemoveEmployeeServlet")
public class RemoveEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int employeeId = Integer.parseInt(request.getParameter("employeeId"));
			boolean result = EmployeeService.removeEmployee(employeeId);
			Gson gson = new Gson();
			String json = gson.toJson(result);
			PrintWriter out = response.getWriter();
			out.print(json);
			out.flush();
		} catch (NumberFormatException | ValidationException | ServiceException e) {
			e.printStackTrace();
		}
	}
}
