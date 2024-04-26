<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>update password</title>
    <style>
        .navbar{
            color: black;
            background-color: black;
        }
        .brandheading{
            color: white;
        }
        .container{
            border: 1px solid black;
            border-radius: 10px;
            padding: 20px;
            margin-top: 40px;
            justify-content: center;
        }
    </style>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
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
    <h4><strong>Change Password</strong></h4>
    <form action="${pageContext.request.contextPath}/api/updatePassword/" method="post" onsubmit="return validateForm()">
        <div class="form-group">
            <label for="currentPassword">Current Password: </label>
            <input type="password" class="form-control" id="currentPassword" name="currentPassword" placeholder="Enter your current password">
        </div>
        <div class="form-group">
            <label for="newPassword">Current Password: </label>
            <input type="password" class="form-control" id="newPassword" name="newPassword" placeholder="Enter new password">
        </div>
        <div class="form-group">
            <label for="newPassword2">Current Password: </label>
            <input type="password" class="form-control" id="newPassword2" name="newPassword2" placeholder="Retype Password">
        </div>
        <hr/>
        <button type="submit" class="btn btn-primary btn-block">Change Password</button>
    </form>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    function validateForm() {
        const currentPassword = document.getElementById("currentPassword").value;
        const newPassword = document.getElementById("newPassword").value;
        const newPassword2 = document.getElementById("newPassword2").value;
        if (newPassword !== newPassword2) {
            alert("New passwords do not match! Please re-enter.");
            return false;
        }
        return true;
    }
</script>
</body>
</html>