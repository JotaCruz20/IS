<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ERROR</title>
</head>
<body>

<c:out value="Error: ${error}"/>
<a href='main'>
    <button class="GFG">
        Go to main page
    </button>
</a>

</body>
</html>
