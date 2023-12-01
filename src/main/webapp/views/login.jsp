<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Маркетинговые исследования. Авторизация</title>
    <link rel="stylesheet" href="../css/general.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<div class="background-layer">
    <div class="center-box w-30">
        <div class="white-block">Внимание! Регистрация в сервисе происходит только с помощью администратора!</div>
        <div>
            <div class="white-block m-t-20px">
                <form method="post" style="text-align: center" action="/login">
                    <label for="username-input">Имя пользователя</label>
                    <br>
                    <input id="username-input" name="username" required>
                    <br>
                    <label for="password-input">Пароль</label>
                    <br>
                    <input id="password-input" type="password" name="password" required>
                    <hr>
                    <button class="simple-btn btn-gray" type="submit">Авторизоваться</button>
                    <br>
                    <a href="/">На главную</a>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
