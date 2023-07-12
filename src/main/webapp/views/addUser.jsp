<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Add User</title>
</head>
<body>
<h1>Add User</h1>
<c:if test="${not empty message}">
    <div class="error">${message}
    </div>
</c:if>
<form:form action="/admin/addUserHandle" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required><br>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br>
    <label for="roles" id="roles" name="roles">Roles:</label>
    <label>
        <input type="checkbox" name="option1" value="Option 1"> ROLE_MANAGER
    </label>
    <label>
        <input type="checkbox" name="option2" value="Option 1"> ROLE_EMPLOYEE
    </label>
    <br>
<%--    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">--%>
    <button type="submit">Add User</button>
</form:form>
<hr>
<a href="/admin">Back</a>
</body>
</html>
