
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--引入jstl核心标签库，我们才能使用jstl标签--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h4>if测试</h4>
<%--自己给自己提交表单--%>
<form action="coreif.jsp" method="get">
    <%--
    EL表达式获取表单中的数据
    ${param.参数名}
    --%>
    <input type="text" name="username" value="${param.username}">
    <input type="submit" value ="登录">
</form>

<%--判断如果提交的用户是管理员，则登陆成功
作用等同于c:if
request.getParameter("username").equals会报空指针错误因为初始时param.username为空，request.getParameter("username")就也为空
--%>
<%
    if("admin".equals(request.getParameter("username")))
        out.println("登陆成功");
%>
<%--isAdmin存了if表达式的布尔值--%>
<c:if test="${param.username=='admin'}" var="isAdmin">
    <c:out value="欢迎您管理员"></c:out>
</c:if>
<c:out value="${isAdmin}"></c:out>

</body>
</html>
