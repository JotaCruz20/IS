<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Available Trips</title>
</head>
<body>
<c:forEach items="${trips}" var="trip">
    <c:out value="Bus from ${trip.departurePoint} to ${trip.destination}, leaving at ${trip.departureTime}"/>
    <form action="<%= request.getContextPath() %>/buyTrip" method="get">
        <input type="hidden" name="tripId" value="${trip.id}" />
        <input type="submit" value="Return this trip">
    </form>
</c:forEach>
</body>
</html>