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
        .container.posts{
            border-radius: 10px;
            padding: 10px;
            border: 0.5px solid black;
        }
        .row.createoption{
            border: 1px dashed black;
            border-radius: 10px;
            padding: 15px;
            margin: 20px;

        }
        .row.communities{
            border: 1.5px dashed blue;
            border-radius: 8px;
            padding: 15px;
            margin: 20px;
        }
    </style>

</head>
<body>
<%@include file="navbar.jsp"%>

<%
    HttpSession usersession = request.getSession();
    boolean showAlert = usersession.getAttribute("loginAlert") != null && (boolean) usersession.getAttribute("loginAlert");
    if (showAlert) {
        usersession.removeAttribute("loginAlert");
%>
<script>
    alert("Login Successful! Redirecting to homepage");
</script>
<% } %>




<div class="main container-fluid">

    <div class="row">
        <div class="col-8">
            <div class="container">
            <h4><strong>All posts</strong></h4>
            <c:forEach items="${posts}" var="post">
                <div class="container posts">
                    <div class="card">
                        <div class="card-header">
                            <h3 class="card-title">${post.postName}</h3>
                            <h6><em>by: </em>${post.user.username}</h6>
<%--                            <p><em>community: </em>${post.community.name}</p>--%>
                            <c:if test="${not empty post.community.name}">
                                <p><em>community: </em>${post.community.name}</p>
                            </c:if>

                        </div>
                        <div class="card-body">

                            <p class="card-text"><i>description: </i>${post.description}</p>
                            <p class="card-text"><i>url: </i><a href="${post.url}" target="_blank" class="card-text">${post.url}</a></p>
                            <div class="row">
                                <div class="col">
                                    <p><i>Vote count: </i>${post.voteCount}</p>
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
                <div class="row createoption">
                    <div class="col-md-6">
                        <form action="${pageContext.request.contextPath}/api/post/showcreate" method="get">
                            <button type="submit" class="btn btn-primary btn-block">Create new Post</button>
                        </form>
                    </div>
                    <div class="col-md-6">
                        <form action="${pageContext.request.contextPath}/api/community/showCommunityForm" method="get">
                            <button type="submit" class="btn btn-primary btn-block">Create new community</button>
                        </form>
                    </div>
                </div>
                <div class="row communities">
                    <div class="col">
<%--                    <div id="communityList" data-spy="scroll" data-target="#communityNavbar" data-offset="0">--%>
                        <h4><strong>Communities</strong></h4>
                            <ol class="list-group +list-group-flush">
                                <c:forEach items="${communities}" var="community">
                                    <form action="${pageContext.request.contextPath}/api/community/postcomm/${community.communityId}" method="get">
                                        <li class="nav-item">
                                            <input type="hidden" value="${community.communityId}">
                                            <button type="submit" class="btn btn-link">${community.name}</button>
                                        </li>
                                    </form>
                                </c:forEach>
                            </ol>

                    </div>
<%--                    </div>--%>
                </div>
            </div>
        </div>
    </div>


</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        document.querySelectorAll('.upvoteButton, .downvoteButton').forEach(function (button) {
            button.addEventListener("click", function () {
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
                } else if (response.status === 401) {
                    const redirectToLogin = confirm("You need to be logged in to upvote. Do you want to redirect to the login page?");
                    if (redirectToLogin) {
                        window.location.href = "/api/auth/showLogin";
                    }
                } else {
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
                } else if (response.status === 401) {
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



        const hiddenInput = document.getElementById("hiddenDateTime");
        const datetimeStr = hiddenInput.value;

        const dateObj = new Date(datetimeStr);
        const date = dateObj.toLocaleDateString();
        const time = dateObj.toLocaleTimeString();

        console.log("Date:", date);
        console.log("Time:", time);

        document.getElementById("displayDate").textContent = date;
        document.getElementById("displayTime").textContent = time;

</script>

</body>
</html>
