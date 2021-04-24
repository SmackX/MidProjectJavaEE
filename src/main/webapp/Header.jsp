<%--
  Created by IntelliJ IDEA.
  User: Azike
  Date: 4/24/2021
  Time: 7:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Header</title>
    <style>
        .header{
            background-color: darkorange;
            height: 30px;
            padding-left: 30px;
            padding-top: 10px;
        }
        a{
            margin-left: 10px;
            text-decoration: none;
            color: white;
        }
        a:hover{
            color: black;
        }
        #time{
            height: 15px;
            float: bottom;
            padding-left: 10px;
            padding-bottom: 3px;
        }
        #login{
            float: right;
            margin-right: 30px;
            padding-top: 12px;
            font-size: x-large;
        }
    </style>
</head>
<body>
    <div class="header">

        <a href="index.jsp">Home</a>
        <a href="Forum1.jsp">Post</a>
        <a href="Theme1.jsp">Forum</a>
        <a href="Check" id="login">login</a>
    </div>

    <div class="header" id="time">
        <%@ page import="java.util.Date" %>
        <%= new Date() %>
    </div>
</body>
</html>
