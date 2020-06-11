<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="bootstrap-4.5.0/css/bootstrap.min.css">
    <script src="jquery-3.5.1.min.js"></script>
    <script src="bootstrap-4.5.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="global.css">
    <title>Login</title>
</head>
<body>
    <div class="container">
        <div class="card">
            <div class="card-header text-white bg-primary">
                <h2>Login page</h2>
            </div>
            <div class="card-body">
                <form action="/authentication" method="POST">
                    <input type="text" class="form-control" placeholder="Username" name="username">
                    <input type="password" class="form-control" placeholder="Password" name="password">
                    <input type="submit" class="btn btn-primary" value="Login">
                </form>
                <div id="msg" class="alert alert-danger" role="alert">
                    Invalid username / passowrd
                </div>
            </div>
        </div>
    </div>
</body>
</html>