<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Navbar</title>
    <style>
        .navbar{
            color: black;
            background-color: black;
        }
        .brandheading{
            color: white;
        }
    </style>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap-theme.min.css">
</head>
<body>
<nav class="navbar navbar-expand-lg ">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/home">
        <h2 class="brandheading"><strong>fordocs</strong></h2>
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ml-auto">
            <c:if test="${sessionScope.token != null}">
                <li class="nav-item">
                    <form action="${pageContext.request.contextPath}/api/auth/logout" method="post">
                        <input class="nav-link" value="logout" type="submit"/>
                    </form>
                </li>
                <li>
                    <form action="${pageContext.request.contextPath}/api/myprofile">
                        <input type="submit" value="profile" class="nav-link"/>
                    </form>
                </li>
            </c:if>
            <c:if test="${sessionScope.token == null}">
                <li class="nav-item">
                    <form action="${pageContext.request.contextPath}/api/auth/showLogin">
                        <input class="nav-link" value="login" type="submit"/>
                    </form>
                </li>
            </c:if>
        </ul>
    </div>
</nav>

<!-- Bootstrap JS (optional, for dropdowns and toggles) -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
