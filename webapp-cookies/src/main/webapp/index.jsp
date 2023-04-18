<%--
  Created by IntelliJ IDEA.
  User: ederc
  Date: 17/04/2023
  Time: 22:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Gerenciando Cookies</title>
</head>
<body>
<h1>Efetuar Login</h1>
<form action="/webapp-cookies/login" method="post">
    <div>
        <label for="username">Usuário</label>
        <div>
            <input type="text" id="username" name="username">
        </div>
    </div>
    <div>
        <label for="password">Senha</label>
        <div>
            <input type="text" id="password" name="password">
        </div>
    </div>
    <div>
        <input type="submit" value="Login">
    </div>
</form>
</body>
</html>