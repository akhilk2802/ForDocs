<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <style>
        .navbar{
            color: black;
            background-color: black;
        }
        .brandheading{
            color: white;
        }
        .container{
            padding: 20px;
            margin: 50px;
            justify-content: center;
        }
    </style>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<%--<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>--%>

<%
    HttpSession userSession = request.getSession();
    boolean showAlert = userSession.getAttribute("registrationAlert") != null && (boolean) userSession.getAttribute("registrationAlert");
    if (showAlert) {
        userSession.removeAttribute("registrationAlert");
%>
<script>
    alert("Registration Successful! Please Login.");
</script>
<% } %>

<%
    String errorMessage = (String) session.getAttribute("loginError");
    if (errorMessage != null) {
        session.removeAttribute("loginError");
%>
<div class="alert alert-danger">
    <%= errorMessage %> : <h4><strong>Login Failed, Please try again! </strong></h4>
</div>

<% } %>

<%
    HttpSession uSession = request.getSession();
    boolean Alert = uSession.getAttribute("notAuth") != null && (boolean) uSession.getAttribute("notAuth");
    if (Alert) {
        uSession.removeAttribute("notAuth");
%>
<script>
    alert("You need to be authorized to perform this action");
</script>
<% } %>



<nav class="navbar navbar-expand-lg ">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/home">
        <h2 class="brandheading"><strong>fordocs</strong></h2>
    </a>
</nav>
<div class="container-fluid">
    <div class="container">
        <h3><strong>Login</strong></h3>
        <form action="${pageContext.request.contextPath}/api/auth/login" method="post">
            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" class="form-control" id="username" name="username" placeholder="Enter Username">
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" class="form-control" id="password" name="password" placeholder="Enter Password">
            </div>
            <button type="submit" class="btn btn-primary btn-block">Login</button>
        </form>
        <hr/>
        <h6><strong>New ? Create account here</strong></h6>
        <hr/>
        <form action="${pageContext.request.contextPath}/api/auth/showSignup">
            <div class="form-group">
                <button value="register" type="submit" class="btn btn-primary btn-block">Register</button>
            </div>
        </form>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
