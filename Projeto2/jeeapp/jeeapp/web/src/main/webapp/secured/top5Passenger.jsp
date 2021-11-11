<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Top 5 passengers</title>
</head>
<body>
<p>Top 5 users:</p>
<c:forEach items="${clients}" var="client" varstatus="loop">
  <c:out value="${loop.count} ${client.name}"/>
</c:forEach>
<a href="mainM.jsp">Go back</a>
</body>
</html>