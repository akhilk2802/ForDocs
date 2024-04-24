<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<%@include file="navbar.jsp"%>

<div class="container">
    <h3>My posts</h3>
</div>

<c:forEach items="${posts}" var="post">
    <div class="container">
        <div class="card">
            <div class="card-header">
                    ${post.user.username}
            </div>
            <div class="card-body">
                <h5 class="card-title">${post.postName}</h5>
                <p class="card-text">${post.description}</p>
                <a href="#" class="btn btn-primary">Vote</a>
                <form action="${pageContext.request.contextPath}/api/post/delete/${post.postId}" method="post" >
                    <input class="btn btn-danger" value="Delete" type="submit"/>
                </form>
                <form action="${pageContext.request.contextPath}/api/post/update/${post.postId}" method="post" >
                    <input class="btn btn-warning" value="Update" type="submit"/>
                </form>
            </div>
        </div>
    </div>
</c:forEach>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
