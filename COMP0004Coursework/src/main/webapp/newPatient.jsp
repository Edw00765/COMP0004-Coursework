<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/meta.jsp"/>
    <title>Add New Patient</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">
    <h1>Add New Patient</h1>
    <form action="newPatientAdded.html" method="post">
        <label for="ID">ID:</label>
        <input type="text" id="ID" name="ID" required><br>

        <label for="BIRTHDATE">BirthDate:</label>
        <input type="text" id="BIRTHDATE" name="BIRTHDATE" required><br>

        <label for="DEATHDATE">DeathDate:</label>
        <input type="text" id="DEATHDATE" name="DEATHDATE"><br>

        <label for="SSN">SSN:</label>
        <input type="text" id="SSN" name="SSN" required><br>

        <label for="DRIVERS">Drivers:</label>
        <input type="text" id="DRIVERS" name="DRIVERS"><br>

        <label for="PASSPORT">Passport:</label>
        <input type="text" id="PASSPORT" name="PASSPORT"><br>

        <label for="PREFIX">Prefix:</label>
        <input type="text" id="PREFIX" name="PREFIX"><br>

        <label for="FIRST">First:</label>
        <input type="text" id="FIRST" name="FIRST" required><br>

        <label for="LAST">Last:</label>
        <input type="text" id="LAST" name="LAST" required><br>

        <label for="SUFFIX">Suffix:</label>
        <input type="text" id="SUFFIX" name="SUFFIX"><br>

        <label for="MAIDEN">Maiden:</label>
        <input type="text" id="MAIDEN" name="MAIDEN"><br>

        <label for="MARITAL">Marital:</label>
        <input type="text" id="MARITAL" name="MARITAL"><br>

        <label for="RACE">Race:</label>
        <input type="text" id="RACE" name="RACE"><br>

        <label for="ETHNICITY">Ethnicity:</label>
        <input type="text" id="ETHNICITY" name="ETHNICITY"><br>

        <label for="GENDER">Gender:</label>
        <input type="text" id="GENDER" name="GENDER" required><br>

        <label for="BIRTHPLACE">Birthplace:</label>
        <input type="text" id="BIRTHPLACE" name="BIRTHPLACE"><br>

        <label for="ADDRESS">Address:</label>
        <input type="text" id="ADDRESS" name="ADDRESS"><br>

        <label for="CITY">City:</label>
        <input type="text" id="CITY" name="CITY"><br>

        <label for="STATE">State:</label>
        <input type="text" id="STATE" name="STATE"><br>

        <label for="ZIP">Zip:</label>
        <input type="text" id="ZIP" name="ZIP"><br>

        <input type="submit" value="Add Patient">
    </form>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>