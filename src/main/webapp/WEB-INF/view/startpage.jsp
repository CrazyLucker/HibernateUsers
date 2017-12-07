<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>

<table border="1">
    <caption>Users</caption>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Age</th>
        <th></th>
        <th></th>
    </tr>
    <c:forEach var="user" items="${requestScope.users}">
        <tr>
            <td><c:out value="${user.id}"/></td>
            <td><c:out value="${user.name}"/></td>
            <td><c:out value="${user.surname}"/></td>
            <td><c:out value="${user.age}"/></td>
            <td>
                <form method="post" action="/delete">
                    <input type="text" hidden name="id" value="${user.id}"/>
                    <input type="submit" value="Delete">
                </form>
            </td>
            <td>
                <form method="get" action="/update">
                    <input type="text" hidden name="id" value="${user.id}"/>
                    <input type="submit" value="Update">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<h3> Add user</h3><br>
<form method="post" action="/">
    <label><input type="text" name="name"></label> Name <br>
    <label><input type="text" name="surname"></label> Surname <br>
    <label><input type="number" name="age"></label> Age <br>
    <label><input type="submit" value="Add" name="button"></label> <br>
</form>

</body>

</html>