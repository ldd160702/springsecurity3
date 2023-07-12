<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en"><head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>Please Sign in</title>
  <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
  <link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" rel="stylesheet" crossorigin="anonymous">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
  <form:form class="form-signin" method="post" action="/login-processing">
    <h2 class="form-signin-heading">Please sign in</h2>
    <c:if test="${param.error != null}">
    <i class="alert alert-danger" role="alert">
      Invalid username and password.
    </i>
    </c:if>
    <p>
      <label for="username" class="sr-only">Username</label>
      <input type="text" id="username" name="username" class="form-control" placeholder="Username" required="required" autofocus="">
    </p>
    <p>
      <label for="password" class="sr-only">Password</label>
      <input type="password" id="password" name="password" class="form-control" placeholder="Password" required="required">
    </p>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
  </form:form>
</div>
</body></html>

