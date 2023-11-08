package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hostel.Student;

public class StudentDao {
	private String jdbcURL="jdbc:mysql://localhost:3306/hostel?useSSL=false";
	private String jdbcUsername="root";
	private String jdbcPassword="root";
	
	private static final String INSERT_STUDENT_SQL="INSERT INTO student"+"(name,age,dep,roomno) VALUES"+"(?,?,?,?);";
	
	private static final String SELECT_STUDENT_BY_ID="select id,name,age,dep,roomno from user where id=?";
	private static final String SELECT_ALL_STUDENT="select*from student";
	private static final String DELETE_STUDENT_SQL="delete from student where id = ?;";
	private static final String UPDATE_STUDENT_SQL="update student set name=?,age=?,dep=?,roomno=? where id=?;";

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
	//create or insert student
	public void insertStudent(Student student) throws SQLException {
		try(Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT_SQL) ){
			preparedStatement.setString(1, student.getName());
			preparedStatement.setInt(2, student.getAge());
			preparedStatement.setString(3, student.getDep());
			preparedStatement.setInt(4, student.getRoomno());
			preparedStatement.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//update student
	public boolean updateStudent(Student student) throws SQLException {
		boolean rowUpdated;
		try(Connection connection = getConnection();
				PreparedStatement preparedStatement1 = connection.prepareStatement(UPDATE_STUDENT_SQL) ){
			preparedStatement1.setString(1, student.getName());
			preparedStatement1.setInt(2, student.getAge());
			preparedStatement1.setString(3, student.getDep());
			preparedStatement1.setInt(4, student.getRoomno());
			preparedStatement1.setInt(5, student.getId());
			
			rowUpdated = preparedStatement1.executeUpdate() > 0;
		}	
			return rowUpdated;
		}
	//select student by id
	public Student selectStudent(int id) {
		Student student = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_BY_ID); ){
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String dep = rs.getString("dep");
				int roomno = rs.getInt("roomno");
				student = new Student(id, name, age, dep, roomno);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return student;
	}
	//select all user
	public List<Student> selectAllStudent() {
		List<Student> student = new ArrayList<>();
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENT); ){
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String dep = rs.getString("dep");
				int roomno = rs.getInt("roomno");
				student.add(new Student(id, name, age, dep, roomno));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return student;}
		
	//delete student
		public boolean delateStudent(int id) throws SQLException {
			boolean rowDeleted;
			try (Connection connection = getConnection();
					PreparedStatement statement = connection.prepareStatement(DELETE_STUDENT_SQL);){
				statement.setInt(1, id);
				rowDeleted = statement.executeUpdate() >0;
			}
			return rowDeleted;
		}
	}
