<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2021/6/18
  Time: 16:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>

  <%--jsp表达式
  作用：用来将程序的输出，输出到客户端
  <%= 变量或者表达式%>
  --%>
  <%= new java.util.Date()%>

  <hr>

  <%--jsp脚本片段--%>
  <%
  int sum =0;
  for(int i=0;i<=100;i++){
    sum+=i;
  }
//  写out，在把jsp转换成servlet时，jsp中的java语句会被直接使用，所以这可以直接写out
  out.println("<h1> Sum="+sum+"</h1>");
  %>

  <hr>

  <%
    int x=10;
    out.println(x);
  %>
  <p>这是一个JSP文档</p>
  <%
  int y=2;
  out.println(y);
  %>

  <hr>

  <%--在代码中嵌入html元素--%>
  <%
    for (int i=0;i<5;i++){
  %>
  <h1>hello zyk <%=i%> </h1>
  <%
    }
  %>

  <hr>

  <%!
  static {
    System.out.println("Loading Servlet");
  }
  private int globalvar = 0;
  public void zhangyk(){
    System.out.println("进入了zhangyk");
  }
  %>

  </body>
</html>
