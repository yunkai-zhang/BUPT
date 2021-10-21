
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--定制错误页面--%>
<html>

<%--针对一个页面定制错误页面（web.xml中可以配置所有页面默认的出现错误后的跳转页面）
errorPage以当前项目目录为起点--%>
<%--<%@ page errorPage="/error/500.jsp" %>--%>

<head>
    <title>Title</title>
</head>
<body>

<%
int x = 1/0;
%>

</body>
</html>
