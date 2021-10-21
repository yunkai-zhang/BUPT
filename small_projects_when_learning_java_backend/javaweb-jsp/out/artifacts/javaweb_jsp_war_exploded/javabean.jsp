<%@ page import="com.zhangy.pojo.People" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%--测试jsp:usebean的时候必须把下面的java注释掉，不然两个people会编译报错--%>
<%--<%--%>
    <%--People people = new People();--%>
    <%--people.setAddress("北京");--%>
    <%--people.setAge(3);--%>
    <%--people.setId(1);--%>
    <%--people.setName("张云1");--%>
<%--%>--%>

<jsp:useBean id="people" class="com.zhangy.pojo.People" scope="page" />
<jsp:setProperty name="people" property="address" value="北京"/>
<jsp:setProperty name="people" property="age" value="3"/>
<jsp:setProperty name="people" property="name" value="张云1"/>
<jsp:setProperty name="people" property="id" value="1"/>

姓名：<jsp:getProperty name="people" property="name"/>
编号：<jsp:getProperty name="people" property="id"/>
年龄：<jsp:getProperty name="people" property="age"/>
地址：<jsp:getProperty name="people" property="address"/>
</body>
</html>
