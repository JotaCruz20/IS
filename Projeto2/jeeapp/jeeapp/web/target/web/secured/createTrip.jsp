
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create a Trip</title>
</head>
<body>
<p>Fill the form to create a trip:</p>
<form action="<%= request.getContextPath() %>/createTrip" method="post">
    Departure point: <input name="departure" type="text" placeholder="departure point..." />
    <br>
    Departure time: <input name="departureTime" type="datetime-local">
    <br>
    Destination: <input name="destination" type="text" placeholder="destination..." />
    <br>
    Capacity: <input name="capacity" type="number" placeholder="capacity..." />
    <br>
    Price: <input name="price" type="text" min="0.00" max="30.00" step="0.01" placeholder="price..." />
    <br>
    <input type="submit">
</form>
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
