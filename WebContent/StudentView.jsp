<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import="java.util.*, models.Student"  %>
<!DOCTYPE html>
<html>
<head>
<style>
table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

td, th {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}

tr:nth-child(even) {
  background-color: #dddddd;
}
</style>
</head>
<body>


<%
	List<Student> students = new ArrayList<Student>();
	students = (List<Student>) request.getAttribute("students");
%>

<h2>HTML Table</h2>
<br/><br/>
<a href="addStudent.jsp">Add Student</a>

<table>
  
  <tr>
    <th>ID</th>
    <th>Name</th>
    <th>Email</th>
    <th>Options</th>
  </tr>
  
  <% for(Student student : students){ %>
  	<tr>
	    <td><%= student.getId() %></td>
	    <td><%= student.getName() %></td>
	    <td><%= student.getEmail() %></td>
	    <td>
	    	<a href="StudentController?id=<%= student.getId() %>&method=delete">Delete</a> | 
	    	<a href="StudentController?id=<%= student.getId() %>&method=update">Update</a>
	    </td>
	 
  	</tr>
  <% } %>

  
  </table>
</body>
</html>
