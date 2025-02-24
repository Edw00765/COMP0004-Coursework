<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <jsp:include page="/meta.jsp"/>
    <title>Patient Data App</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">
    <h1>Edit Patient Data</h1>
    <form method="POST" action="/editPatient.html">
        <%
            List<String> columnNames = (List<String>) request.getAttribute("columnNames");
            List<String> patientDetail = (List<String>) request.getAttribute("patientDetail");
            int counter = 0;
            for (String columnName : columnNames) {
        %>
        <label for="<%=columnName%>"><%=columnName%>:</label>
        <input type="text" id="<%=columnName%>" name="<%=columnName%>" placeholder="Enter the new value for <%=columnName%>" value="<%=patientDetail.get(counter)%>"/>
        <br/>
        <% counter++;} %>
        <input type="hidden" name="patientID" value="<%=patientDetail.get(0)%>">
        <input type="submit" value="Edit"/>
    </form>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>
