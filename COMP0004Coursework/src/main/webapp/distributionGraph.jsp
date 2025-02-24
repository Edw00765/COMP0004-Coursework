<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/meta.jsp"/>
    <title>Age Distribution Chart</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
<jsp:include page="/header.jsp"/>
<canvas id="ageDistributionChart"></canvas>

<%
    Map<Integer, Integer> distribution = (Map<Integer, Integer>) request.getAttribute("distribution");
    StringBuilder ageGroupsArray = new StringBuilder("[");
    StringBuilder countsArray = new StringBuilder("[");
    for (Map.Entry<Integer, Integer> entry : distribution.entrySet()) {
        ageGroupsArray.append(entry.getKey()).append(",");
        countsArray.append(entry.getValue()).append(",");
    }
    ageGroupsArray.deleteCharAt(ageGroupsArray.length() - 1).append("]");
    countsArray.deleteCharAt(countsArray.length() - 1).append("]");
%>
<script>

    var ageGroups = <%= ageGroupsArray %>;
    var counts = <%= countsArray %>;


    var ctx = document.getElementById('ageDistributionChart').getContext('2d');
    var myChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: ageGroups,
            datasets: [{
                label: 'Age Distribution',
                data: counts,
                backgroundColor: 'rgba(54, 162, 235, 0.5)',
                borderColor: 'rgba(54, 162, 235, 1)',
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
    });
</script>
<jsp:include page="/footer.jsp"/>
</body>
</html>
