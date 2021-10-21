
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%--下面代码确实可以实现防止注销用户重登录的功能，但是狂神说太low，用过滤器做比较好--%>
<%--<%--%>
    <%--//在代码最开始的地方，先判断一下是不是已经注销了，注销了就别进了--%>
    <%--Object user_session = request.getSession().getAttribute("USER_SESSION");--%>
    <%--if(user_session==null)--%>
        <%--response.sendRedirect("/Login.jsp");--%>
<%--%>--%>


<h1>主页</h1>

<%--a标签点击后会跳去另一个页面:https://blog.csdn.net/u010297791/article/details/52784879--%>
<p><a href="/servlet/logout">注销</a></p>

</body>
</html>
