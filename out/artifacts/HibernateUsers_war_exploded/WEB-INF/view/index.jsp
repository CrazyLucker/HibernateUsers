<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html;charset=UTF-8"  %>
<html>


<head>
    <title>Userbase </title>
</head>

<body>
    <h1> Users</h1><br>

    <c:forEach var="user" items="${requestScope.users}">
        <ul>
            <li>Id: <c:out value="${user.id}"/></li>
            <li>Name: <c:out value="${user.name}"/></li>
            <li>Surname: <c:out value="${user.surname}"/></li>
            <li>Age: <c:out value="${user.age}"/></li>

            <form method="post" action="/delete">
                <input type="text" hidden name="id" value="${user.id}"/>
                <input type="submit" value="Delete">
            </form>

            <form method="post" action="/update">
                <input type="text" hidden name="id" value="${user.id}"/>
                <input type="submit" value="Update">
            </form>
        </ul>
        <hr />

    </c:forEach>

    <h2> Add user</h2><br>
    <form method="post" action = "/">
        <label><input type="text" name = "name"></label> Name <br>
        <label><input type = "text" name = "surname"></label> Surname <br>
        <label><input type = "number" name = "age"></label> Age <br>
        <label><input type = "submit" value = "Add" name = "button"></label> <br>
    </form>

    </body>
</html>