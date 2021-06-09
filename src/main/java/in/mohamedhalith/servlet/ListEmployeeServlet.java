package in.mohamedhalith.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import in.mohamedhalith.exception.ServiceException;
import in.mohamedhalith.model.Employee;
import in.mohamedhalith.service.EmployeeService;

/**
 * Servlet implementation class ListEmployeeServlet
 */
@WebServlet("/ListEmployeeServlet")
public class ListEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Employee> employeeList = EmployeeService.getEmployeeList();
			Gson gson = new Gson();
			String json = gson.toJson(employeeList);
			PrintWriter out = response.getWriter();
			out.print(json);
			out.flush();
		} catch (ServiceException | IOException e) {
			e.printStackTrace();
		}
	}

}
