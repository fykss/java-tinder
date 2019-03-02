<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/favicon.ico">

    <title>Signin Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="src/main/resources/templates/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="src/main/resources/templates/css/style.css">
</head>

<body class="error">
<div class="error_box">
    <div class="error_box-title">Error:</div>
    <div>This email: <span class="error_box-span-email">${error_email}</span>.<br>Is exist. Try again.</div>
</div>
</body>
</html>
