<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Create Post</title>
    <style>
        .container{
            padding: 20px;
            margin: 50px;
            justify-content: center;
            border: 1px dashed black;
            border-radius: 10px;
        }
    </style>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@include file="navbar.jsp"%>
<div class="container-fluid">
    <div class="container">
        <h3><strong>Create new Post</strong></h3>
        <div class="container">
            <form action="${pageContext.request.contextPath}/api/post/create" method="post">
                <div class="form-group">
                    <label for="postName">Name : </label>
                    <input type="text" class="form-control" name="postName" id="postName">
                </div>
                <div class="form-group">
                    <label for="url">Url : </label>
                    <input type="text" class="form-control" name="url" id="url">
                </div>
                <div>
                    <label for="description">Description : </label>
                    <input type="text" class="form-control" name="description" id="description">
                </div>
                <input type="hidden" value="${communityId}" name="communityId">
                <hr/>
                <div class="form-group">
                    <button class="btn btn-primary btn-block" type="submit">Create</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>