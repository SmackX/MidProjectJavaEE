<%--
  Created by IntelliJ IDEA.
  User: Azike
  Date: 15.03.2021
  Time: 2:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3><a href="index.jsp">login</a></h3>
<h1>Вход в систему</h1><br>
    <form method="post" action="AuthFilter">

        <input type="text" required placeholder="login" name="login"><br>
        <input type="password" required placeholder="password" name="password"><br><br>
        <input class="button" type="submit" value="Войти">

    </form>
</body>
</html>
