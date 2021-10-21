<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2021/6/19
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    //跳页面的两种实现方式
    pageContext.forward("index.jsp");//前端想跳的话，这个就能跳
    //request.getRequestDispatcher("/index.jsp").forward(request,response);//servlet后台这么写
%>

</body>
</html>
