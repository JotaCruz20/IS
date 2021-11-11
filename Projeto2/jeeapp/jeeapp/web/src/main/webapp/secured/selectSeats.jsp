<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Select Seats</title>
</head>
<body>

<form action="<%= request.getContextPath() %>/buyTrip" method="post">
    Select a Seat:&nbsp;
    <select name="seat">
        <c:forEach items="${seats}" var="seat">
            <option value="${seat}">
                    ${seat}
            </option>
        </c:forEach>
    </select>
    <input type="submit" value="Buy this trip">
</form>
</body>
</html>