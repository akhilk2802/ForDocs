<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Community</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .container-fluid.main{
            padding: 20px;
        }
        .container.posts{
            margin: 10px;
            padding: 20px;
            /*border: 1px dashed blue;*/
            border-radius: 8px;
        }
        .container.post{
            margin-top: 50px;
            border-radius: 10px;
            padding: 10px;
            border: 0.5px solid black;
        }
        .container.option{
            margin-left: 10px;
            margin-right: 40px;
            padding: 10px;
            border: 1px solid black;
            border-radius: 10px;
        }
        .container.members{
            margin-top: 20px;
            margin-left: 10px;
            margin-right: 20px;
            padding: 20px;
            border: 1px dashed black;
            border-radius: 10px;
        }

    </style>
</head>
<body>
<c:set var="userIdToCheck" value="${currentUser.userId}" />
<c:set var="isMember" value="false" />

<c:forEach items="${members}" var="member">
    <c:if test="${member.userId eq userIdToCheck}">
        <c:set var="isMember" value="true" />
    </c:if>
</c:forEach>


<%
    HttpSession uSession = request.getSession();
    boolean Alert = uSession.getAttribute("notMember") != null && (boolean) uSession.getAttribute("notMember");
    if (Alert) {
        uSession.removeAttribute("notMember");
%>
<script>
    alert("You need to be a Member of the community, Please Join");
</script>
<% } %>

<%@include file="navbar.jsp"%>
<div class="container-fluid main">

    <div class="row">
        <div class="col-8">
            <div class="container posts">
                <h4><strong>Posts by ${community.name}</strong></h4>
                <p>${community.description}</p>
                <c:forEach items="${postsbycommunity}" var="post">
                    <div class="container post">
                        <div class="card">
                            <div class="card-header">
                                <h3 class="card-title">${post.postName}</h3>
                                <h6><i>by: </i>${post.user.username}</h6>
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
            <div class="container option">
                <div class="row">
                    <div class="col-md-6">
                        <form action="${pageContext.request.contextPath}/api/post/showcreate" method="get">
                            <input type="hidden" name="communityId" value="${community.communityId}">
                            <button type="submit" class="btn btn-primary btn-block">Create New Post</button>
                        </form>
                    </div>
                    <c:choose>
                        <c:when test="${isMember}">
                            <div class="col-md-6">
                                <form action="${pageContext.request.contextPath}/api/leave/${community.communityId}" method="post">
                                    <button type="submit" class="btn btn-primary btn-block">Leave Community</button>
                                </form>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="col-md-6">
                                <form action="${pageContext.request.contextPath}/api/join/${community.communityId}" method="post">
                                    <button type="submit" class="btn btn-primary btn-block">Join Community</button>
                                </form>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="container members">
                <div class="row">
                    <div class="col-auto">
                        <div class="container">
                            <h5><strong>Members : </strong></h5>
                            <ol class="list-group +list-group-flush">
                                <c:forEach items="${members}" var="member">
                                    <li class="nav-item">
                                        <button class="btn btn-link">${member.username}</button>
                                    </li>
                                </c:forEach>
                            </ol>
                        </div>
                    </div>
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
        document.querySelectorAll('.upvoteBut ton, .downvoteButton').forEach(function(button) {
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