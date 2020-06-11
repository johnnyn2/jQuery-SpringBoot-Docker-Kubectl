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
    <script type="text/javascript">
        $(document).ready(function () {
            $("#reset-btn").click(function () {
                $("#login-form").trigger("reset");
            })
        })
    </script>
</head>
<body>
    <div class="container" style="width: 500px;">
        <div class="card">
            <div class="card-header text-white bg-primary">
                <h2>Login page</h2>
            </div>
            <div class="card-body">
                <form id="login-form" action="/authentication" style="display: flex; flex-direction: column; align-items: center;" method="POST">
                    <div style="width: 400px;">
                        <input id="input-username" type="text" class="form-control" placeholder="Username" name="username">
                        <input id="input-password" type="password" class="mt-2 form-control" placeholder="Password" name="password">
                    </div>
                    <div class="mt-3">
                        <input type="submit" class="btn btn-primary" value="Login">
                        <div class="btn btn-secondary" id="reset-btn">RESET</div>  
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>