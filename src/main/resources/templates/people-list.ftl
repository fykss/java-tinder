<!doctype html>
<html lang="en" xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">


    <title>People list</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="/templates/css/bootstrap.min.css">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="/templates/css/style.css">
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-8 offset-2">
            <div class="panel panel-default user_panel">
                <div class="panel-heading">
                    <h3 class="panel-title">User List</h3>
                </div>
                <div class="panel-body">
                    <div class="table-container">
                        <form action="/liked" method="post" id="form_like" type="submit"></form>
                            <table class="table-users table" border="0">
                                <tbody>
                                <#list listUsers as listItem>
                                    <tr class="table_row">
                                        <td class="align_left" width="10">
                                            <label class="list_label" for="${listItem.getId()}">
                                            <div class="avatar-img">
                                                <img class="img-circle" src="${listItem.getUrlImg()}" />  
                                            </div>
                                            </label>
                                        </td>
                                        <td class="align-middle">
                                            <label class="list_label" for="${listItem.getId()}">
                                            ${listItem.getName()} ${listItem.getSurname()}
                                            </label>
                                        </td>
                                        <td class="align-middle">
                                            <label class="list_label" for="${listItem.getId()}">
                                            ${listItem.getPosition()}
                                            </label>
                                        </td>
                                        <td  class="align-middle">
                                            <label class="list_label" for="${listItem.getId()}">
                                            Last Login:  ${listItem.getDate()}<br>
                                            <small class="text-muted">
                                                ${listItem.getTimeDif()}
                                            </small>
                                            </label>
                                        </td>
                                        <button style="display: none" id="${listItem.getId()}" class="btn_submit" form="form_like" type="submit" name="userId" value="${listItem.getId()}">Send</button>
                                    </tr>
                                </tbody>
                                </#list>
                            </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<a href="/logout">LogOut</a>

</body>
</html>
