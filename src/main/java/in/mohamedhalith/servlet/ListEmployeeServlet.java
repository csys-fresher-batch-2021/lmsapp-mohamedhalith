package in.mohamedhalith.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.mohamedhalith.exception.ServiceException;
import in.mohamedhalith.exception.ValidationException;
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
			request.setAttribute("employeeList", employeeList);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("listemployees.jsp");
			requestDispatcher.forward(request, response);
		} catch (ServiceException | ValidationException | ServletException | IOException e) {
			e.printStackTrace();
		}
	}

}