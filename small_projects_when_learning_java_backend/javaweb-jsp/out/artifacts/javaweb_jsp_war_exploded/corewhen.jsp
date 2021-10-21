<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--定义一个变量是score，值85--%>
<c:set var="score" value="85"/>

<c:choose>
    <%--判断有先后顺序，只要满足就会跳出--%>
    <c:when test="${score>=80}">
        你的成绩优秀
    </c:when>
    <c:when test="${score>=70}">
        你的成绩良好
    </c:when>
    <c:when test="${score>=60}">
        你的成绩及格
    </c:when>
    <c:when test="${score<60}">
        你的成绩不及格
    </c:when>
</c:choose>
</body>
</html>
