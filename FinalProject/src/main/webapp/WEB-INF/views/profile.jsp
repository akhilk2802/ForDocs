<%--<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>--%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<!doctype html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <title>profile</title>
    <style>
        .navbar{
            color: black;
            background-color: black;
        }
        .brandheading{
            color: white;
        }
        .container.top {
            display: flex;
            flex-direction: column;
            border: 2px dashed black;
            border-radius: 5px;
            padding: 10px;
            margin-top: 20px;
        }

        .container.bottom {
            border: 1px dashed blue;
            border-radius: 8px;
            margin-top: 20px;
            padding: 10px;
        }
        .container.profile{
            border: 1px dashed black;
            border-radius: 12px;
            margin-top: 10px;
            padding: 20px;
        }
        .container.updateProfile{
            border: 1px dashed black;
            border-radius: 12px;
            margin-top: 10px;
            padding: 10px;
        }
    </style>
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
                <li class="nav-item">
                    <form action="${pageContext.request.contextPath}/api/auth/logout" method="post">
                        <input class="nav-link" value="logout" type="submit"/>
                    </form>
                </li>
        </ul>
    </div>
</nav>
<div class="container">
    <div class="container top">
        <div class="container">
            <h3><strong>Profile</strong></h3>
            <div class="row">

                <div class="col-8">
                    <div class="container profile">
                        <p><strong>Name : ${profile.username} </strong></p>
                        <p><strong>Email : ${profile.email} </strong></p>
                    </div>
                </div>
                <div class="col-4">
                    <div class="container updateProfile">
                        <button>Update Profile</button>
                        <button>Change Password</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="container bottom">
        <div class="container">
            <h3><strong>My Posts</strong></h3>
            <div class="container posts">
                <c:forEach items="${myposts}" var="post">
                    <div class="card">
                        <div class="card-header">
                            <div class="row align-items-center">
                                <div class="col">
                                    <h3 class="card-title">${post.postName}</h3>
                                </div>
                                <div class="col-auto">
                                    <form action="${pageContext.request.contextPath}/api/post/delete/${post.postId}" method="post">
                                        <button class="btn btn-danger delete-post-btn" type="submit">Delete</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="card-body">
                            <p class="card-text">${post.description} </p>

                                <form action="${pageContext.request.contextPath}/api/post/update/${post.postId}" method="post">
                                    <div class="row">
                                        <div class="col-10"><input type="text" class="form-control" id="postDesc" name="postDesc" placeholder="Edit Post Description">
                                            <input type="hidden" name="url" value=${post.url}>
                                            <input type="hidden" name="postName" value=${post.postName}>
                                        </div>
                                        <div class="col-2"><button type="submit" class="btn btn-link">Submit</button></div>
                                    </div>
                                </form>

                        </div>
                        <div class="card-footer">
                            <h6><strong>Comments</strong></h6>
                            <c:forEach items="${post.comments}" var="comment">
                                <div class="row align-items-center">
                                    <div class="col">
                                        <h6>${comment.text}</h6>
                                    </div>
                                    <div class="col-auto">
                                        <form action="${pageContext.request.contextPath}/api/comment/delete/${comment.commentId}" method="post">
                                            <button class="btn btn-danger delete-comment-btn">Delete Comment</button>
                                        </form>

                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>

</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>