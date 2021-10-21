<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2021/6/19
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--脚本片段中的代码，会被原封不动生成到jsp.java
要求：这里的代码，必须保证java语法的正确性--%>
<%
    //    通过pageContext取出参数,我们通过寻找的方式来
    //从底层到高层（作用域）
    String name1 = (String) pageContext.findAttribute("name1");
    String name2 = (String) pageContext.findAttribute("name2");
    String name3 = (String) pageContext.findAttribute("name3");//可以跨页面取出
    String name4 = (String) pageContext.findAttribute("name4");//可以跨页面取出
    String name5 = (String) pageContext.findAttribute("name5"); //不存在
    String name6 = (String) pageContext.findAttribute("name6"); //不存在
%>

<%--使用EL表达式输出${}
${}等效于<%= %>
--%>
<h1>取出的值为：</h1>
<h1>${name1}</h1>
<h1>${name2}</h1>
<h1>${name3}</h1>
<h1>${name4}</h1>
<h1>${name5}</h1>                           <%--不会显示不存在的变量--%>
<h1> <%= name6%> </h1>                   <%--不存在的变量显示为null（网站中不希望看到，所以一般用EL表达式 ${}）--%>

</body>
</html>
