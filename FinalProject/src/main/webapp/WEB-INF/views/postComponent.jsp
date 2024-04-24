<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.6.1/css/bootstrap.min.css">

    <title>Document</title>
</head>
<body>
<div class="container">
<div class="card">
    <div class="card-header">
        ${post.user.username}
    </div>
    <div class="card-body">
        <h5 class="card-title">${post.postName}</h5>
        <p class="card-text">${post.description}</p>
        <a href="#" class="btn btn-primary">UpVote</a>
        <a href="#" class="btn btn-primary">DownVote</a>
        <a href="" >Add comment</a>
    </div>
    <div class="card-footer">
        <div>Comments</div>
    </div>
</div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.6.1/js/bootstrap.min.js"></script>
</body>
</html>