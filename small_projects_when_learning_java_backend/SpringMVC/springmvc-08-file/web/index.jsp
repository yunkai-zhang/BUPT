<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>$Title$</title>
</head>
<body>

<%--enctype="multipart/form-data"表示前端以二进制流的方式提交。提交到/upload请求--%>
<form action="${pageContext.request.contextPath}/upload2" enctype="multipart/form-data" method="post">
  <input type="file" name="file"/>
  <input type="submit" value="upload">
</form>

<%--${pageContext.request.contextPath}可以省略--%>
<a href="${pageContext.request.contextPath}/download">点击下载</a>
</body>
</html>
