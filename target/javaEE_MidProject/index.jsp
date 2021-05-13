<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        #box{
            background-color: black;
            color: white;
            width: 50%;
            padding: 4px;
            padding-left: 15px;
            margin: 1rem;
            border: 1px solid darkorange;
            border-radius: 20px;
        }
    </style>
</head>
<body>
<div class="form">

    <%@include file="Header.jsp"%>
    <jsp:useBean id="posts" class="dao.PostDAO" scope="session"/>
    <%
        posts.getBD();
        int i = 0;

        while (i < posts.sizeStore()){

            out.println("<div id='box'>"+"Post number: "+ posts.getData(i).getId()+ "<h3>" + posts.getData(i).getText() + "</h3>" + "Rating: " +posts.getData(i).getRating()  +"</div>" +"\n");
            i++;
        }
    %>

</div>

</body>
</html>
