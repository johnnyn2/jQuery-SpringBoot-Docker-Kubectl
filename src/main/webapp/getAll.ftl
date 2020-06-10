<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="bootstrap-4.5.0/css/bootstrap.min.css">
    <script src="jquery-3.5.1.min.js"></script>
    <script src="bootstrap-4.5.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="global.css">
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
                } else if (formType.indexOf("delete-form") >= 0) {
                    console.log("ddddd: ", $(this));
                    $("#msg").removeClass("alert-danger").removeClass("alert-success").addClass("alert-info").html("Loading...").css("display", "");
                    console.log('deelte daat: ', event, name);
                    
                    $.ajax({
                        url: '/delete',
                        method: 'DELETE',
                        data: $(this).serialize(),
                        timeout: 15000,
                        contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
                        success: function(data) {
                            console.log("success");
                            sessionStorage.setItem("status", "success");
                            window.location.reload();
                        },
                        error: function(data) {
                            console.log("error");
                            $("#msg").removeClass("alert-info").removeClass("alert-success").addClass("alert-danger").html("Error !").css("display", "");
                        },
                        complete: function(data) {
                            console.log("complete");
                        }
                    })
                }
            })
        })
    </script>
    <title>Software  List</title>
</head>
<body>
    <div class="container">
        <div class="card">
            <div class="card-header bg-primary text-white">
                <h2>Software List</h2>
            </div>
            <div class="card-body">
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
                                    <button class="btn btn-danger" role="button" data-toggle="modal" data-target="${'#warning-dialog-' + software?index}">
                                        Delete
                                    </button>
                                    <form id="${'delete-form-' + software?index}" action="${'/delete?name='+software.name}" name="software" method="DELETE">
                                        <input type="hidden" name="name" value="${software.name}">
                                        <div class="modal fade" id="${'warning-dialog-' + software?index}" tabindex="-1" role="dialog" aria-labelledby="warning-dialog" aria-hidden="true">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="warning-dialog-title">Warning</h5>
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body">
                                                    This is an unreversible action. Please click confirm to delete.
                                                </div>
                                                <div class="modal-footer">
                                                    <input type="submit" class="btn btn-danger" form="${'delete-form-' + software?index}" value="Confirm">
                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                </div>
                                                </div>
                                            </div>
                                        </div>
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