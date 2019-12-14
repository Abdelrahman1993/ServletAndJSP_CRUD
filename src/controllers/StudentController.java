package controllers;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import models.Student;
import models.StudentUtility;

/**
 * Servlet implementation class Hello
 */
@WebServlet("/StudentController")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
	@Resource(name = "jdbc/studentcrud")
	private DataSource dataSource;
	private StudentUtility studentUtility;

	@Override
	public void init() throws ServletException {
		super.init();
		try {
			studentUtility =new StudentUtility(dataSource);
			
		}catch(Exception exc){
			throw new ServletException();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		//a.equals(b)
		String method = request.getParameter("method");
		if(method != null && method.equals("delete")) {
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				studentUtility.deleteStudent(id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect("StudentController");
			return;
		}
		if(method != null && method.equals("update")) {
			Student student = null;
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				student = studentUtility.getStudent(id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RequestDispatcher dispacher = request.getRequestDispatcher("/updateStudent.jsp");
			request.setAttribute("student", student);
			dispacher.forward(request, response);
			return;
		}
		
		
			
		
		List<Student> students = null;
		try {
			students = studentUtility.getStudents();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		RequestDispatcher dispacher = request.getRequestDispatcher("/StudentView.jsp");
		request.setAttribute("students", students);
		dispacher.forward(request, response);
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		String method = request.getParameter("update");
		
		if(method != null && method.equals("update")) {
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			Student student = new Student(id, name, email, password);
			studentUtility.updateStudent(student);
			response.sendRedirect("StudentController");
			return;
		}
		
		Student student = new Student(request.getParameter("name"),request.getParameter("email"),request.getParameter("password"));
		//out.print(student);
		try {
			studentUtility.addStudent(student);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//response.sendRedirect("http://localhost:8080/StudentCRUD/StudentController"); 
		response.sendRedirect("StudentController");
		
	}


	
	

}
