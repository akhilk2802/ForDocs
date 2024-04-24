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
        .main{

        }
    </style>

</head>
<body>
<%@include file="navbar.jsp"%>

<div class="main container-fluid">

    <div class="row">
        <div class="col-8">
            <div class="container">
            <h3>All posts</h3>
            <c:forEach items="${posts}" var="post">
                <div class="container">
                    <div class="card">
                        <div class="card-header">
                            <h3 class="card-title">${post.postName}</h3>
                            <h6>${post.user.username}</h6>
                        </div>
                        <div class="card-body">

                            <p class="card-text">${post.description}</p>
                            <div class="row">
                                <div class="col">
                                    <p>Vote count: ${post.voteCount}</p>
                                    <button class="upvoteButton" data-postid = "${post.postId}">Upvote</button>
                                    <button class="downvoteButton" data-postid = "${post.postId}">Downvote</button>
                                </div>
                            </div>
                        </div>
                        <div class="card-footer">
                            <h6><strong>Comments</strong></h6>
                            <c:forEach items="${post.comments}" var="comment">
                                <h6>${comment.user.username}: ${comment.text}</h6>
                            </c:forEach>
                            <form method="post" action="${pageContext.request.contextPath}/api/comment/create/${post.postId}">
                                <div class="row">
                                    <div class="col-10"><input type="text" class="form-control" id="commentText" name="commentText" placeholder="Add Your Comment"></div>
                                    <div class="col-2"><button type="submit" class="btn btn-primary">Submit</button></div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </c:forEach>
            </div>
        </div>
        <div class="col-4">
            <div class="container">

                <div class="row">
                    <button> Create New Post</button>
                </div>
                <div class="row">
                    <h4><strong>Communities</strong></h4>
                </div>
            </div>
        </div>
    </div>


</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    document.addEventListener("DOMContentLoaded", function() {
        document.querySelectorAll('.upvoteButton, .downvoteButton').forEach(function(button) {
            button.addEventListener("click", function() {
                var postId = this.getAttribute("data-postid");
                console.log("Post ID:", postId);
                if (this.classList.contains("upvoteButton")) {
                    console.log("upvote")
                    upvotePost(postId);
                } else if (this.classList.contains("downvoteButton")) {
                    console.log("downvote")
                    downvotePost(postId);
                }
            });
        });
    });

    function upvotePost(postId) {
        fetch('/api/votes/upvote/' + postId, {
            method: 'POST',
            credentials: 'same-origin', // Include this if you're using sessions
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(response => {
                if (response.ok) {
                    console.log("Post upvoted successfully");
                    location.reload();
                } else if(response.status === 401) {
                    const redirectToLogin = confirm("You need to be logged in to upvote. Do you want to redirect to the login page?");
                    if (redirectToLogin) {
                        window.location.href = "/api/auth/showLogin";
                    }
                }else{
                    console.error("Failed to upvote post");
                }
            })
            .catch(error => {
                console.error("Error while upvoting post:", error);
            });
    }

    function downvotePost(postId) {
        fetch('/api/votes/downvote/' + postId, {
            method: 'POST',
            credentials: 'same-origin', // Include this if you're using sessions
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(response => {
                if (response.ok) {
                    console.log("Post downvoted successfully");
                    location.reload();
                } else if(response.status === 401){
                    const redirectToLogin = confirm("You need to be logged in to downvote. Do you want to redirect to the login page?");
                    if (redirectToLogin) {
                        window.location.href = "/api/auth/showLogin";
                    }
                } else {
                    console.error("Failed to downvote post");
                }
            })
            .catch(error => {
                console.error("Error while downvoting post:", error);
            });
    }

</script>

</body>
</html>
