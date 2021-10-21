
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--jsp指令
@include会把header页面和footer页面合二为一个页面呈现在jsp转化的servlet中
不同页面有同名变量会报500错误
--%>
<%@include file="common/header.jsp"%>
<h1>网页主体</h1>
<%@include file="common/footer.jsp"%>

<hr>

<%--JSP标签,都是标签，看的比上面舒服
jsp:include：拼接页面，在jsp转化成的servlet中，使用资源引用得到header和footer，本质还是三个页面。
不同页面有同名变量不相互影响
--%>
<jsp:include page="common/header.jsp"/>
<h1>网页主体</h1>
<jsp:include page="common/footer.jsp"/>
</body>
</html>
