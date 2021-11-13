<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Top 5 passengers</title>
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
<p>Top 5 users:</p>
<table>
  <tr>
    <th>Top</th>
    <th>Name</th>
    <th>Email</th>
  </tr>
  <c:forEach items="${clients}" var="client" varStatus="loop">
    <tr>
      <td>${loop.count}</td>
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