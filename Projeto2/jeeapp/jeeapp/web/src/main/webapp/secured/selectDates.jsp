<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Select Dates</title>
</head>
<body>
<strong>Select Dates</strong>
<form action="<%= request.getContextPath() %>/listTrips" method="post" onsubmit="return isToday()">
    Beginning: <input name="start" id="date" type="date">
    <br>
    End: <input name="end" type="date" id="after">
    <br>
    <input type="submit">
</form>
<br>
<button onclick="goBack()">Go Back</button>
<br>
<br>
<a href="<%= request.getContextPath() %>/logout">Log Out</a>
<script>
    function goBack() {
        window.history.back();
    }

    function isToday(){
        const today = Date.now()-86400000;
        const startDate = Date.parse(document.getElementById('date').value);
        const endDate = Date.parse(document.getElementById('after').value);
        if(endDate<startDate){
            window.alert("End date should be after start date");
            return false;
        }
        else if(startDate<today){
            window.alert("Start date should be after today");
            return false;
        }
        else return true;
    }
</script>

</body>
</html>
