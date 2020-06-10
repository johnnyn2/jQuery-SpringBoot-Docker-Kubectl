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
                let valid = true;
                let invalidEntry = "";
                $(this).serializeArray().forEach((data) =>{
                    if (data.value === "") {
                        valid = false;
                        invalidEntry = data.name;
                    }
                    if (data.name === "version" && typeof data.value === 'number') {
                        valid = false;
                        invalidEntry = data.name;
                    }
                });
                if (!valid) {
                    $("#msg").removeClass("alert-info").removeClass("alert-success").addClass("alert-danger").html('Invalid '+ invalidEntry + '!').css("display", "");
                } else {
                    const formData = {};
                    $(this).serializeArray().forEach((data) =>{
                    formData[data.name] = data.value;
                    });
                    $("#msg").removeClass("alert-danger").removeClass("alert-success").addClass("alert-info").html("Loading...").css("display", "");
                    $.ajax({
                        url: '/update',
                        method: 'PUT',
                        data: JSON.stringify(formData),
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
                }
            })
        })
    </script>
</head>
<body>
    <div class="container">
        <div class="card">
            <div class="card-header text-white bg-primary">
                <h2>
                    <#if software??>
                        ${software.name}
                    <#else>
                        Not available
                    </#if>
                </h2>
            </div>
            <div class="card-body">
                <#if software??>
                    <form id="update-form">
                        <div class="form-group">
                            <label for="software-name">
                                Name
                            </label>
                            <input id="software-name" class="form-control" type="text" name="name" value="${software.name}" placeholder="Software name (e.g. Eclipse)">
                        </div>
                        <div class="form-group">
                            <label for="software-version">
                                Version
                            </label>
                            <input id="software-version" class="form-control" type="text" name="version" value="${software.version}" placeholder="Software version (e.g. 1.0)">
                        </div>
                        <div class="form-group">
                            <label for="software-description">
                                Description
                            </label>
                            <input id="software-description" class="form-control" type="text" name="description" value="${software.description}" placeholder="Software description (e.g. This is used to code)">
                        </div>
                        <input type="submit" class="btn btn-primary" value="Save">
                        <a href="getAll" class="btn btn-secondary">Back</a>
                    </form>
                    
                </#if>
            </div>
        </div>
        <div id="msg" class="alert alert-info" role="alert" style="display: none;">
            Loading...
        </div>
    </div>
</body>
</html>