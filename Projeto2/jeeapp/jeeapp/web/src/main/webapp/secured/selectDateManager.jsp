<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Select Date</title>
</head>
<body>
<strong>Select Date:</strong>
<form action="<%= request.getContextPath() %>/listTripsOnDateManager" method="post">
    Date: <input name="date" type="date">
    <br>
    <input type="submit">
</form>

</body>
</html>
