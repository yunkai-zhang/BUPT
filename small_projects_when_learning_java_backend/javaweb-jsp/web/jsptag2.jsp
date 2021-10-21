
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
jsptag2

<%--取出参数--%>
<%--EL表示只能取requestscope,不是最完善的，所以这里用jsp标签--%>
name:<%=request.getParameter("name")%>
age:<%=request.getParameter("age")%>

</body>
</html>
