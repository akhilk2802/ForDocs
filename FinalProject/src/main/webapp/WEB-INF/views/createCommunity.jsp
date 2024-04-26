<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
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
        <h3><strong>Create Community</strong></h3>
        <div class="container">
            <form action="${pageContext.request.contextPath}/api/community/create" method="post">
                <div class="form-group">
                    <label for="name">Community Name : </label>
                    <input type="text" class="form-control" name="name" id="name">
                </div>
                <div class="form-group">
                    <label for="desc">Description : </label>
                    <input type="text" class="form-control" name="desc" id="desc">
                </div>
                <div class="form-group">
                    <button value="submit" class="btn btn-primary btn-block" type="submit">Submit</button>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>