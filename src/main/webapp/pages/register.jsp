<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>register</title>
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
        .wrapper {
            width: 1000px;
            margin: 0 auto;
            padding: 0 0 40px 0;
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
        .sign-up-btn {
            padding: 10px;
            font-size: 0.8rem;
            text-transform: uppercase;
        }
        .error-message {
            color: #ff7675;
        }
    </style>
</head>
<body>
    <div class="wrapper">
        <h1>Registration</h1>
        <form method="post">
            <label for="username-input">Username:</label><br>
            <input placeholder="login"  required id="username-input" type="text" name="login">
            <br>
            <label for="password-input">Password:</label><br>
            <input placeholder="password"  required id="password-input" type="password" name="password">
            <br>
            <label for="password-confirm-input">Confirm your password:</label><br>
            <input placeholder="confirm your password" required id="password-confirm-input" type="password"
                   name="password-confirmation">
            <br>
            <button class="sign-up-btn" type="submit">sign up</button>
            <p>Already have an account? <a href="/app/login">Sign in</a></p>
        </form>
        <div class="error-message">
            <p>${error}</p>
        </div>
    </div>
</body>
</html>