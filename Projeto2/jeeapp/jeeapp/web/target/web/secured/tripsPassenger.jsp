<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Trip's Passengers</title>
</head>
<body>
<p>Trips Passengers:</p>
<c:forEach items="${clients}" var="client">
  <c:out value=" ${client.name} ${client.email}"/>
  <br>
</c:forEach>
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