<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="bootstrap-4.5.0/css/bootstrap.min.css">
    <script src="jquery-3.5.1.min.js"></script>
    <script src="bootstrap-4.5.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="global.css">
    <title>Software</title>
    <script>
        $(document).ready(() => {
            if(sessionStorage.getItem("status") === "success") {
                $("#msg").removeClass("alert-info").removeClass("alert-danger").addClass("alert-success").html("Success !").css("display", "");
                sessionStorage.clear();
            }
            $("form").on("submit", function (event) {
                event.preventDefault();
                const formData = {};
                $(this).serializeArray().forEach((data) =>{
                    formData[data.name] = data.value;
                });
                if (formData.confirmPassword !== formData.password) {
                    $("#msg").removeClass("alert-info").removeClass("alert-success").addClass("alert-danger").html('Password and Confirm Password don\'t match !').css("display", "");
                    return;
                }
                const finalizedFormData = {
                    username: formData.username,
                    password: formData.password,
                    permission: formData.permission
                };
                console.log("finalized form data: ", finalizedFormData);
                $("#msg").removeClass("alert-danger").removeClass("alert-success").addClass("alert-info").html("Loading...").css("display", "");
                $.ajax({
                    url: '/api/admin/add',
                    method: 'POST',
                    data: JSON.stringify(finalizedFormData),
                    timeout: 15000,
                    dataType: 'json',
                    contentType: 'application/json',
                    success: function (data) {
                        console.log("success: ", data);
                        sessionStorage.setItem("status", "success");
                        window.location.reload();
                    },
                    error: function (data) {
                        console.log("error: ", data);
                        $("#msg").removeClass("alert-info").removeClass("alert-success").addClass("alert-danger").html("Error !").css("display", "");
                    },
                    complete: function(data) {
                        console.log("complete: ", data);
                    }
                })
            });
        });
    </script>
</head>
<body>
    <div class="container">
        <div class="card">
            <div class="card-header text-white bg-primary">
                <h2>Add User</h2>
            </div>
            <div class="card-body">
                <form id="create-user">
                    <div class="form-group">
                        <label for="input-username">Username</label>
                        <input id="input-username" class="form-control" type="text" name="username">
                    <div>
                    <div class="form-group">
                        <label for="input-password">Password</label>
                        <input id="input-password" class="form-control" type="password" name="password">
                    </div>
                    <div class="form-group">
                        <label for="input-confirm-password">Confirm password</label>
                        <input id="input-confirm-password" class="form-control" type="password" name="confirmPassword">
                    </div>
                    <div class="form-group">
                        <label for="input-select">Role</label>
                        <select id="input-select" name="permission">
                            <option value="ROLE_ADMIN">ADMIN</option>
                            <option value="ROLE_USER">USER</option>
                        </select>
                    </div>
                    <input type="submit" class="btn btn-primary" value="Add">
                </form>
                <a href="/" class="btn btn-secondary">Back</a>
            </div>
        </div>
        <div id="msg" class="alert alert-info" role="alert" style="display: none;">
            Loading...
        </div>
    </div>
</body>
</html>
