<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Select Dates</title>
</head>
<body>
<strong>Select Dates</strong>
<form action="<%= request.getContextPath() %>/listtrips" method="post">
    Beginning: <input name="start" type="date">
    <br>
    End: <input name="end" type="date">
    <br>
    <input type="submit">
</form>

</body>
</html>
