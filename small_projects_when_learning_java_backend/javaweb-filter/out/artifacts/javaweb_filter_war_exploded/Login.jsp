
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1>登录</h1>

<%--这个路径找不到，要代码中设置当前程序目录起点
但是为了简便，直接删除tomcat的deployment的application context路径即可使程序使用当前设置的相对路径--%>
<form action="/servlet/login" method="post">
    <input type="text" name="username">
    <input type="submit">
</form>

</body>
</html>
