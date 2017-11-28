<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Update Information</title>
</head>
<body>
    <h4>Old data</h4>
    <ul>
        <li>Id: <c:out value="${requestScope.user.id}"/></li>
        <li>Name: <c:out value="${requestScope.user.name}"/></li>
        <li>Surname: <c:out value="${requestScope.user.surname}"/></li>
        <li>Age: <c:out value="${requestScope.user.age}"/></li>
    </ul>

    <h4>Put new data</h4>

    <form method="get" action = "/update">
        <label><input type="text" name = "name"></label> Name <br>
        <label><input type = "text" name = "surname"></label> Surname <br>
        <label><input type = "number" name = "age"></label> Age <br>
        <input type="number" hidden name="id" value="${requestScope.user.id}"/>
        <label><input type = "submit" value = "Update" ></label> <br>
    </form>
</body>
</html>
