package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDao;
import hostel.Student;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDao studentDao;
  
    public StudentServlet() {
    	this.studentDao = new StudentDao(); 
      
    }
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getServletPath();
		
		switch (action) {
		case "/new" :
			showNewForm(request, response);
			break;
		case "/insert" :
			try {
				insertStudent(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/delete" :
			try {
				deleteStudent(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/edit" :
			showEditForm(request, response);
			break;
		case"/update" :
			try {
				updateStudent(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			//list
			try {
				listStudent(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break; 
		}
	}
	private void listStudent(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Student> listStudent = (List<Student>) studentDao.selectAllStudent();
		 request.setAttribute("listStudent", listStudent);
		RequestDispatcher dispatcher =request.getRequestDispatcher("student-list.jsp");
		dispatcher.forward(request, response);
	}
	
	
	private void updateStudent(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException{
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String dep = request.getParameter("dep");
		int roomno = Integer.parseInt(request.getParameter("roomno"));
		
		Student student = new Student(name, age, dep, roomno);
		studentDao.updateStudent(student);
		response.sendRedirect("list");
		
	}
	
	
	private void deleteStudent(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException{
		int id = Integer.parseInt(request.getParameter("id"));
		studentDao.delateStudent(id);
		response.sendRedirect("list");
	}
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
		int id = Integer.parseInt(request.getParameter("id"));
		Student existingStudent = studentDao.selectStudent(id);
		RequestDispatcher dispatcher =request.getRequestDispatcher("student-form.jsp");
		request.setAttribute("student", existingStudent);
		dispatcher.forward(request, response);
	}
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
	             throws ServletException, IOException{
		RequestDispatcher dispatcher =request.getRequestDispatcher("student-form.jsp");
		dispatcher.forward(request, response);
	}
	private void insertStudent(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException{
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String dep = request.getParameter("dep");
		int roomno = Integer.parseInt(request.getParameter("roomno"));
		Student newStudent = new Student(name, age, dep, roomno);
		studentDao.insertStudent(newStudent);
		response.sendRedirect("list");
	}
}
