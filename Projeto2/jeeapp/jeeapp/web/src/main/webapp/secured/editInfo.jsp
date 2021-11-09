<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>EditInfo</title>
</head>
<body>
<strong>Edit Account</strong>
<form action="<%= request.getContextPath() %>/editInfo" method="post">
    Email: <input name="email" type="text" value="${client.email}"  disabled="disabled"/>
    <br>
    Wallet: <input name="wallet" type="text" value="${client.wallet}"  disabled="disabled"/>
    <br>
    Password: <input name="password" type="password"/>
    <br>
    Name: <input name="name" type="text" value="${client.name}"/>
    <br>
    Birthday: <input name="birthdate" type="date" value="${client.birthdate}">
    <br>
    <input type="submit">
</form>
<br>
<form action="<%= request.getContextPath() %>/delete" method="post">
    <input type="submit" value="Delete Acount">
</form>
<br>
<form action="<%= request.getContextPath() %>/chargeWallet" method="post">
    Money to Charge: <input name="money" type="number"/>
    <br>
    <input type="submit" value="Charge Wallet">
</form>
</body>
</html>