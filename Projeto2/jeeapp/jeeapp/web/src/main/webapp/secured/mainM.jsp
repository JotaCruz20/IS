<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
<p>OLA manager ${auth}</p>
<a href="<%= request.getContextPath() %>/createTrip">Create a new trip</a>
<br>
<a href="<%= request.getContextPath() %>/deleteTrip">Delete a future trip</a>
<br>
<a href="<%= request.getContextPath() %>/top5passenger">List top 5 passengers</a>
<br>
<a href="<%= request.getContextPath() %>/listTripsManager">List trips between two dates</a>
<br>
<a href="<%= request.getContextPath() %>/listTripsOnDateManager">List trips occuring on a date</a>
<br>

</body>
</html>
