<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Create Account</title>
</head>
<body>
<strong>Create Account</strong>
<form action="<%= request.getContextPath() %>/create" method="post">
    Email: <input name="email" type="text" placeholder="email..." />
    <br>
    Password: <input name="password" type="password" placeholder="password..." />
    <br>
    Name: <input name="name" type="text" placeholder="name..." />
    <br>
    Birthday: <input name="birthdate" type="date">
    <br>
    <input type="submit">
</form>
</body>
</html>