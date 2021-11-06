<%--
  Created by IntelliJ IDEA.
  User: asilva
  Date: 03/11/2021
  Time: 12:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create a Trip</title>
</head>
<body>
<p>Fill the form to create a trip:</p>
<form action="<%= request.getContextPath() %>/createTrip" method="post">
    Departure point: <input name="departure" type="password" placeholder="departure point..." />
    <br>
    Departure time: <input name="departureTime" type="datetime">
    <br>
    Destination: <input name="destination" type="text" placeholder="destination..." />
    <br>
    Capacity: <input name="capacity" type="text" placeholder="capacity..." />
    <br>
    Price: <input name="price" type="text" placeholder="price..." />
    <br>
    <input type="submit">
</form>
</body>
</html>
