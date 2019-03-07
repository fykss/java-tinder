<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Like page</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="/templates/css/bootstrap.min.css">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="/templates/css/style.css">
</head>
<body style="background-color: #f5f5f5;" class="style">

<div class="col-4 offset-4">
    <div class="link_page">
        <div>
            <a class="link_regLogin" href="/liked">Liked</a>
        </div>
        <div>
            <a class="link_regLogin" href="/logout">LogOut</a>
        </div>
    </div>
    <div class="card">
        <div class="card-body">
            <div class="row">
                <div class="col-12 col-lg-12 col-md-12 text-center">
                    <img src="${user.urlImg}" alt="" class="mx-auto rounded-circle img-fluid">
                    <h3 class="mb-0 text-truncated">${user.name} ${user.surname}</h3>
                    <br>
                </div>
                <div class="col-12 col-lg-6">
                    <form action="/users" method="post">
                        <input name="${dislike}" value="${user.id}" type="hidden" >
                        <button name="action" value="disliked" type="submit" class="btn btn-outline-danger btn-block"><span class="fa fa-times"></span> Dislike</button>
                    </form>
                </div>
                <div class="col-12 col-lg-6">
                    <form action="/users" method="post">
                        <input name="${like}" value="${user.id}" type="hidden" >
                        <button name="action" value="liked"  type="submit" class="btn btn-outline-success btn-block"><span class="fa fa-heart"></span> Like</button>
                    </form>
                </div>
                <!--/col-->
            </div>
            <!--/row-->
        </div>
        <!--/card-block-->
    </div>
</div>

</body>
</html>
