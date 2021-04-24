<%--
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
            border-color: black;
            padding-left: 10px;
            padding-top: 10px;
        }
    </style>
</head>
<body>
    <%@include file="Header.jsp"%>


    <jsp:useBean id="test" class="Logic.Text" scope="session"></jsp:useBean>
    <%
        if ( session.getAttribute("text") != null)
        test.setText((String) session.getAttribute("text"));
        else {
            test.setText(" ");
        }
    %>
    <% String post = test.getText();%>

    <div class="post" style="background-color: black; color: white; width: 300px; height: 200px; margin-top: 70px">
        Today: <br>
        <%= post %>

    </div>

    <%-- out with Property --%>
    <div class="post">
    <jsp:setProperty name="test" property="text" value="<%= post %>" ></jsp:setProperty>
    <jsp:getProperty name="test" property="text"/>
    </div>
</body>
</html>
