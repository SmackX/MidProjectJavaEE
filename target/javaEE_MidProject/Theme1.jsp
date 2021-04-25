<%@ page import="servlets.functional.New_Post" %><%--
  Created by IntelliJ IDEA.
  User: Azike
  Date: 4/24/2021
  Time: 4:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Theme 1</title>
    <style>
        .post{
            margin-left: 40px;
            margin-top: 20px;
            border-color: black;
            padding-left: 10px;
            padding-top: 10px;
        }
    </style>
</head>
<body>
    <%@include file="Header.jsp"%>

    <jsp:useBean id="article" class="servlets.functional.New_Post" scope="session"/>
    <jsp:useBean id="User" class="model.User" scope="session"/>
    <div id="main_div">
        <button style="background-color: cornflowerblue; margin-top: 10px"><a href="Forum1.jsp">New post</a></button>
        <br>
        <div class="post" style="background-color: black; color: white; width: 500px;">
            <h3>Name: <%= New_Post.getName() %></h3>
            <div class="textInPost">Message: <%= New_Post.getText() %></div>
            <br>
        <form action="AddLike">
            <input type="hidden" value="<% New_Post.getId(); %>" name="postID" >
            <input type="hidden" value="<%User.getId(); %>;" name="userID">
            <% article.init();  %>
            <button type="submit">Like</button>
        </form>
        </div>
    </div>
</body>
</html>
