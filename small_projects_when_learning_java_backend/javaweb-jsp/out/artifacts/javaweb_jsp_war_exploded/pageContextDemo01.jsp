
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%--内置对象--%>
<%

    pageContext.setAttribute("name1","章昀恺1号");   //保存的数据只在一个页面中有效
    request.setAttribute("name2","章昀恺2号");     //保存的数据只在一次请求中有效，请求转发会携带
    session.setAttribute("name3","章昀恺3号");    //保存的数据只在一次会话中有效，从打开浏览器到关闭浏览器。等于：pageContext.setAttribute("name3","章昀恺3号",PageContext.SESSION_SCOPE);
    application.setAttribute("name4","章昀恺4号");  //保存的数据只在服务器中有效，从打开服务器到关闭服务器
%>


<%--脚本片段中的代码，会被原封不动生成到jsp.java
要求：这里的代码，必须保证java语法的正确性--%>
<%
//    通过pageContext取出参数,我们通过寻找的方式来
    //从底层到高层（作用域）：page->request->session->application
    //JVM:双亲委派机制；
    String name1 = (String) pageContext.findAttribute("name1");
    String name2 = (String) pageContext.findAttribute("name2");
    String name3 = (String) pageContext.findAttribute("name3");
    String name4 = (String) pageContext.findAttribute("name4");
    String name5 = (String) pageContext.findAttribute("name5"); //不存在
    String name6 = (String) pageContext.findAttribute("name6"); //不存在

//    如果加上这句，访问当前jsp会跳到pageDemo02，这是pagedemo02能提取出name2的值，因为请求转发携带了name2的值
    pageContext.forward("pageDemo02.jsp");
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
