<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Create Post</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@include file="navbar.jsp"%>

<div class="container">
    <h2>Create</h2>
    <div class="container">
        <form action="${pageContext.request.contextPath}/api/post/create" method="post">
            <label for="postName">Name : </label>
            <input type="text" name="postName" id="postName">
            <label for="url">Url : </label>
            <input type="text" name="url" id="url">
            <label for="description">Description : </label>
            <input type="text" name="description" id="description">
            <input value="submit" type="submit">
        </form>
    </div>
</div>

</body>
</html>