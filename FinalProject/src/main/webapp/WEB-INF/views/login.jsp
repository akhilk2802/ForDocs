<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container justify-content-center align-items-center vh-100">
    <div class="card shadow-sm">
        <div class="card-header text-center">
            <h3>Login and Signup</h3>
        </div>
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/api/auth/login" method="post">
                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" class="form-control" id="username" name="username" placeholder="Enter Username">
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" class="form-control" id="password" name="password" placeholder="Enter Password">
                </div>
                <button type="submit" class="btn btn-primary btn-block">Login</button>
            </form>
            New ? Create account here
            <form action="${pageContext.request.contextPath}/api/auth/showSignup">
                <button value="register" type="submit" class="btn btn-primary btn-block">Register</button>
            </form>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
