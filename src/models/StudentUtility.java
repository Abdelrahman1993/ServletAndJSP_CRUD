package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

public class StudentUtility {

	private DataSource dataSource;
	
	public StudentUtility(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<Student> getStudents() throws SQLException{
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		List <Student> students= new ArrayList<>();
		
		try {
			con = dataSource.getConnection();
			st = con.createStatement();
			String sql = "SELECT * FROM students";
			rs = st.executeQuery(sql);
			while(rs.next()) {
				students.add( new Student(rs.getInt("id"),rs.getString("name"), rs.getString("email") ));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			con.close();
			st.close();
			rs.close();
			
		}
		
		return students;
		
	}
	
	public void addStudent(Student student) throws SQLException {
		Connection con = null;
		PreparedStatement preStat = null;
		
		try {
			con = dataSource.getConnection();
			String sql = "INSERT INTO students (name, email, password) VALUES (?, ?, ?)";
			preStat = con.prepareStatement(sql);
			preStat.setString(1, student.getName());
			preStat.setString(2, student.getEmail());
			preStat.setString(3, student.getPassword());
			preStat.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			con.close();
			preStat.close();
		}
		
	}
	public void deleteStudent(int id) throws SQLException {
		Connection con = null;
		PreparedStatement preStat = null;
		
		try {
			con = dataSource.getConnection();
			String sql = "DELETE FROM students where id = ?";
			preStat = con.prepareStatement(sql);
			preStat.setInt(1, id);
			preStat.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			con.close();
			preStat.close();
		}
		
	}
	
	public void updateStudent(Student student) {
		Connection con = null;
		PreparedStatement preStat = null;
		try {
			con = dataSource.getConnection();
			String sql = "UPDATE students SET name=?, email=?, password=? WHERE id=?";
			preStat = con.prepareStatement(sql);
			preStat.setString(1, student.getName());
			preStat.setString(2, student.getEmail());
			preStat.setString(3, student.getPassword());
			preStat.setInt(4, student.getId());
			preStat.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				con.close();
				preStat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	public Student getStudent(int id) throws SQLException{
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		Student student=null;
		
		try {
			con = dataSource.getConnection();
			st = con.createStatement();
			String sql = "SELECT * FROM students WHERE id = "+id+"";
			rs = st.executeQuery(sql);
			while(rs.next()) {
				student = new Student(rs.getInt("id"),rs.getString("name"), rs.getString("email"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			con.close();
			st.close();
			rs.close();
			
		}
		
		return student;
		
	}
	
	

}
