<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome to Fordocs</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .container {
            margin-top: 50px;
        }
    </style>

</head>
<body>
<%@ include file="./navbar.jsp" %>

<div class="container">
   <h1>For-Docs</h1>
    <form action="${pageContext.request.contextPath}/api/post/all">
        <input class="nav-link" value="All Post" type="submit"/>
    </form>
    <form action="${pageContext.request.contextPath}/api/post/showcreate" >
        <input class="nav-link" value="Create Post" type="submit"/>
    </form>
    <form action="${pageContext.request.contextPath}/api/post/postbyuser" >
        <input class="nav-link" value="My Posts" type="submit"/>
    </form>
</div>




</form>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
