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
    Wallet: <input name="wallet" type="text" value="${client.wallet}" step="0.01" disabled="disabled"/>
    <br>
    Password: <input name="password" type="password"/>
    <br>
    Name: <input name="name" type="text" value="${client.name}"/>
    <br>
    Birthday: <input name="birthdate" type="date" value="${client.birthdate}">
    <br>
    <input type="submit" value="Change Info">
</form>
<br>
<br>
<form action="<%= request.getContextPath() %>/chargeWallet" method="post">
    Money to Charge: <input name="money" step="0.01" type="number"/>
    <br>
    <input type="submit" value="Charge Wallet">
</form>
<br>
<br>
<form action="<%= request.getContextPath() %>/delete" method="post">
    <input type="submit" value="Delete Acount">
</form>
<br>
<br>
<button onclick="goBack()">Go Back</button>
<br>
<br>
<a href="<%= request.getContextPath() %>/logout">Log Out</a>
<script>
    function goBack() {
        window.history.back();
    }
</script>
</body>
</html>