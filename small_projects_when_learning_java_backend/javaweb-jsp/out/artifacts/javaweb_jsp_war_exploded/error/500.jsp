
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<%--定制错误页面--%>
<head>
    <title>Title</title>
</head>
<body>

<%--src是相对路径，相对的是500.jsp的路径
1. 第一次运行发现读取图片失败，发现是因为img没有被打包进out文件夹，根据（https://blog.csdn.net/firstonedz/article/details/105796420）
把存有图片的文件夹img设置为resource root后，打包成功
2. src="../img/500.png"
但是图片还是没显示，这是因为tomcat设置了项目路劲，所以src的目录为项目目录javaweb_jsp_war_exploded
故可以使用src="img/500.png"直接访问；也可以使用项目绝对路径来寻求对图片进行访问（https://www.cnblogs.com/zshibo/p/8011514.html）
--%>
<img src="${pageContext.request.contextPath}/img/500.png" alt="500">
</body>
</html>
