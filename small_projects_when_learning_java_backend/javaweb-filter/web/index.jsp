<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2021/6/20
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <%--span是行内标签，用于分割一行为多个区域来实现一些显示效果。这里是为了把固定文本和动态输出区分隔开,且给动态文本设置颜色--%>
  <h1>当前有 <span style="color: blue;"><%=this.getServletConfig().getServletContext().getAttribute("onlineCount")%></span> 人在线 </h1>
  </body>
</html>
