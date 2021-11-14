<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Delete</title>
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
<table>
    <tr>
        <th>Departure Point</th>
        <th>Arrival</th>
        <th>Departure Time</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${trips}" var="trip">
        <tr>
            <td>${trip.departurePoint}</td>
            <td>${trip.destination}</td>
            <td>${trip.departureTime}</td>
            <td>
                <form action="<%= request.getContextPath() %>/deleteTrip" method="get">
                    <input type="hidden" name="tripId" value="${trip.id}" />
                    <input type="submit" value="Delete this trip">
                </form>
            </td>
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
