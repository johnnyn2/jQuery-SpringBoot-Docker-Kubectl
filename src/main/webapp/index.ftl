<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="bootstrap-4.5.0/css/bootstrap.min.css">
    <script src="jquery-3.5.1.min.js"></script>
    <script src="bootstrap-4.5.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="global.css">
    <title>Home</title>
</head>
<body>
    <div class="container">
        <div class="card">
            <div class="text-white card-header bg-primary">
                <h2>Home page</h2>
            </div>
            <div class="card-body">
                <div class="card-text">
                    <a href="getAll">Get All Softwares from DB</a>
                <div>
                <div class="card-text">
                    <#if role?? && role.role == "ADMIN">
                        <a href="addPeople">Add Admin/User</a>
                    </#if>
                </div>
                <a href="logout" class="btn btn-danger">Logout</a>
            </div>
        </div>
    </div>
</body>
</html>