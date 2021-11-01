<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Login</title>
</head>
<body>
<strong>Create Account</strong>
<form action="<%= request.getContextPath() %>/login" method="post">
    Email: <input name="email" type="text" placeholder="email..." />
    <br>
    Password: <input name="password" type="password" placeholder="password..." />
    <br>
    <input type="submit">
</form>
</body>
</html>