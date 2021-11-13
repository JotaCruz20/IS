<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Trip's Passengers</title>
  <style>
    table {
      font-family: arial, sans-serif;
      border-collapse: collapse;
      width: 100%;
    }

    td, th {
      border: 1px solid #dddddd;
      text-align: left;
      padding: 8px;
    }

    tr:nth-child(even) {
      background-color: #dddddd;
    }
  </style>
</head>
<body>
<p>Trips Passengers:</p>
<table>
  <tr>
    <th>Name</th>
    <th>Email</th>
  </tr>
  <c:forEach items="${clients}" var="client">
    <tr>
      <td>${client.name}</td>
      <td>${client.email}</td>
    </tr>
  </c:forEach>
</table>
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