<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin role</title>
</head>
<body>
<h1>Admin role</h1>
<hr>
<h2>Users</h2>
<table border="1">
    <thead>
    <tr>
        <th>Username</th>
        <th>Enabled</th>
        <th>Role</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.username}</td>
            <td>${user.enabled}</td>
            <td>
                <ul>
                <c:forEach var="role" items="${user.roles}">
                    <li>${role}</li>
                </c:forEach>
                </ul>
            </td>
            <td>
                <ul>
                    <li>
                        <a href="${pageContext.request.contextPath}/admin/editUser">Edit</a>
                    </li>
                    <li>
                        <a onclick="if (!confirm('Are you sure to delete this user?')) return false" href="${pageContext.request.contextPath}/admin/delete?username=${user.username}">Delete</a>
                    </li>
                </ul>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<hr>
<h3><a href="${pageContext.request.contextPath}/admin/addUser">Add user</a> </h3>
<c:if test="${not empty message}">
    <div class="message">${message}</div>
</c:if>
<hr>
<a href="${pageContext.request.contextPath}/welcome">Back to the homepage</a>
</body>
</html>