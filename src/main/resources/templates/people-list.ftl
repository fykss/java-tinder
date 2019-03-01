<!doctype html>
<html lang="en" xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/favicon.ico">

    <title>People list</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="./src/main/resources/templates/css/bootstrap.min.css">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="./src/main/resources/templates/css/style.css">
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
                                    <tr>
                                        <td width="10">
                                            <div class="avatar-img">
                                                <img class="img-circle" src="${listItem.getUrlImg()}" />  
                                            </div>
                                        </td>
                                        <td class="align-middle">
                                            ${listItem.getName()} ${listItem.getSurname()}
                                        </td>
                                        <td class="align-middle">
                                            ${listItem.getPosition()}
                                        </td>
                                        <td  class="align-middle">
                                            Last Login:  ${listItem.getDate()}<br>
                                            <small class="text-muted">
                                                ${listItem.getTimeDif()}
                                            </small>
                                        </td>
                                        <td>
                                            <button class="btn_submit" form="form_like" type="submit" name="userId" value="${listItem.getId()}">Send</button>
                                        </td>
                                    </tr>
                                </#list>
                                </tbody>
                            </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    var btn = document.getElementById('test');
    btn.onclick = function(e){
        console.log(btn.value())
        console.log(e.target.closest)
    }
    // document.addEventListener("click",function(e){
    //     e.preventDefault(),
    //     e.target.closest(".toggle__menu")&&document.getElementsByClassName("toggle")[0].classList.toggle("navbar__active")})}$(document).ready(function(){$(".social-carousel").slick()}),dropdown();

    // document.getElementsByClassName('test').onclick = function(e) {
    //     var tr = e.target;
    //     alert(tr.tagName);
    // };

</script>

</body>
</html>
