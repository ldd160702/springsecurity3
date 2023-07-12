<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add User</title>
</head>
<body>
<h1>Edit User</h1>
<form:form action="/admin/editUserHandle" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" value="" required><br>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" value="" required><br>
    <label for="roles" id="roles" name="roles">Roles:</label>
    <label>
        <input type="checkbox" name="option1" value="Option 1"> ROLE_MANAGER
    </label>
    <label>
        <input type="checkbox" name="option2" value="Option 2"> ROLE_EMPLOYEE
    </label>
    <br>
<%--    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">--%>
    <button type="submit">Save</button>
</form:form>
<hr>
<a href="/admin">Back</a>
</body>
</html>
