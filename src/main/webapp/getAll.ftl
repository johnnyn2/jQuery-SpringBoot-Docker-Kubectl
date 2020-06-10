<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="jquery-3.5.1.min.js"></script>
    <script>
        $(document).ready(() => {
            if(sessionStorage.getItem("status") === "success") {
                $("#msg").removeClass("alert-info").removeClass("alert-danger").addClass("alert-success").html("Success !").css("display", "");
                sessionStorage.clear();
            }
            $("form").on("submit", function (event) {
                event.preventDefault();
                const formType = $(this).attr("id");
                if (formType === "create-form") {
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
                            url: '/addSoftware',
                            method: 'POST',
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
                } else if (formType === "delete-form") {

                }
            })
        })
    </script>
    <title>Software  List</title>
</head>
<body>
    <div class="container">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h2>Software List</h2>
            </div>
            <div class="panel-body">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>name</th>
                            <th>version</th>
                            <th>description</th>
                            <th></th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <#list softwares as software>
                            <tr>
                                <td style="vertical-align:middle;">${software.name}</td>
                                <td style="vertical-align:middle;">${software.version}</td>
                                <td style="vertical-align:middle;">${software.description}</td>
                                <td>
                                    <a href="${'/getSoftware?name='+software.name}" class="btn btn-primary" role="button">Edit</a>
                                </td>
                                <td>
                                    <form id="delete-form" action="${'/delete?name='+software.name}" name="software" method="DELETE">
                                        <input class="btn btn-danger" role="button" type="submit" value="Delete">
                                    </form>
                                </td>
                            </tr>
                        </#list>
                        <tr>
                            <form id="create-form" action="/addSoftware" name="software" method="POST">
                                <td><input class="form-control" type="text" name="name"></td>
                                <td><input class="form-control" type="text" name="version"></td>
                                <td><input class="form-control" type="text" name="description"></td>
                                <td colspan="2"><button type="submit" class="btn btn-success" style="width: 100%;">Create</button></td>
                            </form>
                        </tr>
                    </tbody>
                </table>   
            </div>
            
        </div>
        <div id="msg" class="alert alert-info" role="alert" style="display: none;">
            Loading...
        </div>
    </div>
</body>
</html>