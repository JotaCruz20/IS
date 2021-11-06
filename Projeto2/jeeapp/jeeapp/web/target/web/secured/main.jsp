<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>OLA ${auth}</p>
<a href="<%= request.getContextPath() %>/editInfo">Edit Personal Info</a>
<br>
<a href="">List All Available Trips</a>
<br>
<a href="">Charge my wallet</a>
<br>
<a href="">List my Trips</a>
<br>
<a href="<%= request.getContextPath() %>/logout">Log Out</a>
<br>
<a href="">Delete Account</a>
</body>
</html>
