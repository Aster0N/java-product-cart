<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>login</title>
    <style>
        a {
            color: #74b9ff;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
        a, button {
            cursor: pointer;
        }
        body, html {
            padding: 0;
            margin: 0;
            font-size: 18px;
            font-family: sans-serif;
            background-color: #2d3436;
            color: #ecf0f1;
        }
        .wrapper {
            width: 1000px;
            margin: 0 auto;
            padding: 0 0 40px 0;
        }
        input {
            padding: 8px;
            margin: 0 0 10px 0;
            background-color: transparent;
            color: #ecf0f1;
            border: none;
            border-bottom: 1px solid #ecf0f1;
        }
        input:focus-within, button:focus-within {
            outline: 2px solid #fff;
            border-radius: 5px;
        }
    </style>
</head>
<body>
    <h1>Login page</h1>
    <form action="/login" method="post">
        Username: <input type="text" name="username"><br>
        Password: <input type="password" name="password"><br>
        <button type="submit">sign in</button>
    </form>
</body>
</html>