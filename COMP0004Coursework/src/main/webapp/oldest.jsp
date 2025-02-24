<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="/meta.jsp"/>
    <title>Patient Details</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">
    <h1>Oldest Patient Details</h1>

    <%
        System.out.println(request.getAttributeNames());
        List<String> patientDetail = (List<String>) request.getAttribute("oldestPatient");
        List<String> detailType = (List<String>) request.getAttribute("detailType");
        if (patientDetail != null && patientDetail.size() != 0 && detailType.size() != 0) {
    %>
    <ul>
        <%
            int j = 0;
            for (int i = 0; i < patientDetail.size(); i++) {
                if (j >= detailType.size()) {
                    j = 0;
                }
        %>
        <li><%= detailType.get(j) %>: <%= patientDetail.get(i) %></li>
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
<jsp:include page="/footer.jsp"/>
</body>
</html>