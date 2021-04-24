<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<div class="form">

    <%@include file="Header.jsp"%>


    <div style="margin-top: 50px">
        <%-- this is similar to an estimate --%>
        <%! int count=0;String status="weakly"; %>
    
              <form name="Like" action="AddLike" method="post">
            <%= count++ %>
            <%= status%>
            <input type="submit" value="Like">
        </form>

        <% if ( count > 4 && count < 10 ){
                status="fine!";
            } else if ( count > 9){
                status="excellent!";
            }
        %>
    </div>
</div>
    <jsp:include page="/Check" />

</body>
</html>
