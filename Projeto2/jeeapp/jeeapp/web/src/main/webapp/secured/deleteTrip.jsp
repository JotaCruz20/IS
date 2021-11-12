<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Delete</title>
</head>
<body>
<c:forEach items="${trips}" var="trip">
    <c:out value="Bus from ${trip.departurePoint} to ${trip.destination}, leaving at ${trip.departureTime}"/>
    <form action="<%= request.getContextPath() %>/deleteTrip" method="post">
        <input type="text" value="${trip.id}" name="tripId" hidden="hidden">
        <input type="submit" value="Delete this trip">
    </form>
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
