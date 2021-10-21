<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2021/6/18
  Time: 21:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
404 study！
<%--404.png如果没办法打包，那就先关闭tomcat，取消img的resource root标记，再恢复标记，再启动tomcat，图片就会被打包进去--%>
<img src="${pageContext.request.contextPath}/img/404.png" alt="404">
</body>
</html>
