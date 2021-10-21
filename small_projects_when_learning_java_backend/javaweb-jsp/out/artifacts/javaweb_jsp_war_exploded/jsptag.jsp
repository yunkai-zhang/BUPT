
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%--拼接页面--%>
<%--<jsp:include page=""></jsp:include>--%>

<%--转发--%>
<%--http://localhost:8080/javaweb_jsp_war_exploded/jsptag.jsp?name=zhangyk&age=22--%>
<jsp:forward page="jsptag2.jsp">
    <jsp:param name="name" value="zhangyk"></jsp:param>
    <jsp:param name="age" value="22"></jsp:param>
</jsp:forward>



</body>
</html>
