<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="/meta.jsp"/>
    <title>Patient Data App</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">
    <h1>Filter Result</h1>
    <%
        List<String> patients = (List<String>) request.getAttribute("genderFiltered");
        List<String> detailType = (List<String>) request.getAttribute("detailType");
        if (patients.size() !=0)
        {
    %>
    <ul>
        <%
            int j = 0;
            for (int i = 0; i < patients.size(); i++) {
                if (j >= detailType.size()) {
                    j = 0;
                }
        %>
        <li><%= detailType.get(j) %>: <%= patients.get(i)%></li>
        <% if (j >= detailType.size() - 1) { %>
        <br><br>
        <% } %>
        <%
                j++;
            }
        } else {
        %>
        <p>Nothing found</p>
        <%
            }
        %>
    </ul>
</div>
<div>
    <a href="patientList.html">View the Patient ID List</a>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>