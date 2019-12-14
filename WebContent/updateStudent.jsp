<%@ page import="java.util.*, models.Student, models.StudentUtility"  %>
<!DOCTYPE html>
<html>
<body>

<h2>Update Student</h2>

<% 
	Student student =(Student) request.getAttribute("student");
%>
<%= student %>

<form action="StudentController" method="POST">
  <fieldset>
    <legend>Personal information:</legend>
    <input type="hidden" name="update" value="update">
    <input type="hidden" name="id" value="<%= student.getId() %>">
    Name:<br>
    <input type="text" name="name" value="<%= student.getName() %>">
    <br>
    Email:<br>
    <input type="text" name="email" value="<%= student.getEmail() %>">
    <br>
    Password:<br>
    <input type="password" name="password" value="<%= student.getPassword() %>">
    <br><br>
    <input type="submit" value="Submit">
  </fieldset>
</form>
<a href="StudentController">Bach to home</a>
</body>
</html>