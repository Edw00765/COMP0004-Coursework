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
  <h1>Patient Details</h1>
  <%
  List<String> patientDetail = (List<String>) request.getAttribute("patientDetail");
  List<String> detailType = (List<String>) request.getAttribute("detailType");
  if (patientDetail.size() !=0)
  {
  %>
  <ul>
    <%
    for (int i = 0; i < patientDetail.size(); i++){
    %>
    <li><%=detailType.get(i)%>: <%=patientDetail.get(i)%></li>
    <% }
    } else
    {%>
    <p>Nothing found</p>
    <%}%>
  </ul>
</div>

<form method="POST" action="/deletePatient.html">
  <input type="hidden" name="patientID" value="<%=patientDetail.get(0)%>">
  <input type="submit" value="Delete Patient"/>
</form>

<div>
  <form method="GET" action="/editPatient.html">
    <input type="hidden" name="patientID" value="<%=patientDetail.get(0)%>">
    <input type="submit" value="Edit Patient"/>
  </form>
</div>
<div>
  <a href="patientList.html">Return to Patient ID List</a>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>