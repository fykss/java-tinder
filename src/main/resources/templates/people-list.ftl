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
            <div class="link_page">
                <div>
                    <a class="link_regLogin" href="/users">Profiles</a>
                </div>
                <div>
                    <a class="link_regLogin" href="/logout">LogOut</a>
                </div>
            </div>
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
                                        <td class="align_left align-middle-my" width="10">
                                            <#--<label class="list_label" for="${listItem.getId()}" onclick="openForm()">-->
                                            <label class="list_label" for="${listItem.getId()}">
                                                <div class="avatar-img" >
                                                    <img class="img-circle" src="${listItem.getUrlImg()}" />  
                                                </div>
                                            </label>
                                        </td>
                                        <td class="align-middle align-middle-my">
                                            <label class="list_label" for="${listItem.getId()}">
                                                ${listItem.getName()} ${listItem.getSurname()}
                                            </label>
                                        </td>
                                        <td class="align-middle align-middle-my">
                                            <label class="list_label" for="${listItem.getId()}">
                                                ${listItem.getPosition()}
                                            </label>
                                        </td>
                                        <td  class="align-middle align-middle-my">
                                            <label class="list_label" for="${listItem.getId()}">
                                                <div>
                                                    Last Login: <br>
                                                        <small class="text-muted">
                                                            ${listItem.getTimeDif()}
                                                        </small>
                                                </div>

                                            </label>
                                            <button style="display: none" id="${listItem.getId()}" class="btn_submit" form="form_like" type="submit" name="userId" value="${listItem.getId()}">Send</button>
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

    <#if conn == "/liked/messages">
        <div class="container_chat" id="open">
            <div class="row">
                <div class="width">
                    <div class="col-md-12 chat-header">
                        <div class="row header-one text-white p-1">
                            <div class="col-md-6 name pl-2">
                                <i class="fa fa-comment"></i>
                                <h6 class="ml-1 mb-0">${user.name} ${user.surname}</h6>
                            </div>
                            <div class="col-md-6 options text-right pr-0">
                                <i class="fa fa-window-minimize hide-chat-box hover text-center pt-1" onclick="closeChat()"></i>
                                <p class="arrow-up mb-0">
                                    <i class="fa fa-arrow-up text-center pt-1" onclick="openChat()"></i>
                                </p>
                                <i class="fa fa-times hover text-center pt-1" onclick="closeForm()"></i>
                            </div>
                        </div>

                    </div>
                    <div class="chat-content" id="chat_open">
                        <div class="col-md-12 chats pt-3 pl-2 pr-3 pb-3">
                            <ul class="p-0">
                                <#list listMsg as msg>
                                    <#if msg.getSender()== idUser>
                                        <li class="send-msg float-right mb-2">
                                            <p class="pt-1 pb-1 pl-2 pr-2 m-0 rounded">
                                                ${msg.getTextMessage()}
                                            </p>
                                            <span class="receive-msg-time">${msg.getDate()}</span>
                                        </li>
                                    <#else>
                                        <li class="receive-msg float-left mb-2">
                                            <div class="sender-img">
                                                <img src="${user.urlImg}" class="float-left">
                                            </div>
                                            <div class="receive-msg-desc float-left ml-2">
                                                <p class="bg-white m-0 pt-1 pb-1 pl-2 pr-2 rounded">
                                                    ${msg.getTextMessage()}
                                                </p>
                                                <span class="receive-msg-time">${user.name}, ${msg.getDate()}</span>
                                            </div>
                                        </li>
                                    </#if>
                                </#list>
                            </ul>
                        </div>
                        <div class="col-md-12 p-2 msg-box border border-primary">
                            <div class="row">
                                <div class="col-md-2 options-left">
                                    <i class="fa fa-smile-o"></i>
                                </div>
                                <form action="/liked/messages?id=${user.id}" id="mess" method="post"></form>
                                <div class="col-md-7 pl-0">
                                    <input form="mess" name="messageText" type="text" class="border-0" placeholder="Send message" accesskey="ENTER"/>
                                </div>
                                <div class="col-md-3 text-right options-right">
                                    <i class="fa fa-picture-o mr-2"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    <#else>
    </#if>

</div>

<script>
    function closeForm() {
        document.getElementById("open").style.display = "none";
    }

    function openChat() {
        document.getElementById("chat_open").style.display = "block";
    }

    function closeChat() {
        document.getElementById("chat_open").style.display = "none";
    }
</script>
</body>
</html>
