<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Select Dates</title>
</head>
<body>
<strong>Select Dates</strong>
<form action="<%= request.getContextPath() %>/listTripsManager" method="post">
  Beginning: <input name="start" type="date">
  <br>
  End: <input name="end" type="date">
  <br>
  <input type="submit">
</form>
<br>
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
