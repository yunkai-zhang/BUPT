<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>真实Ajax实验</title>

    <%--引入jquery--%>
    <script src="${pageContext.request.contextPath}/statics/js/jquery-3.6.0.js"></script>

    <%--$是jquery的简写，两者等效--%>
    <script>
      function a(){
        /* 写post方法，要点进post源码看一下；但是jquery不是很看得懂。使用post方法请求了url，携带了参数data。
        post方法执行成功的话，会执行success定义的callback（回调）函数。

        ajax，作为后端只需要掌握post方法中写的四个参数：url，data，success，error

        jquery.post的用法：https://www.cnblogs.com/moxiaotao/p/9588358.html
         */
        jQuery.post({
          url:"${pageContext.request.contextPath}/a1",
          /*
          data以键值对的方式存值。使用选择器$()来取输入框的值

          后端AjaxController.java是从data取数据，而不是从表单取数据，所以a1函数的输入值是name，而不是username。
          */
          data:{"name":$("#username").val()},
          /*
          * 正常情况下，服务器返回数据会让前端重定向（forward）或者转发（redirect）吗，会使整个页面刷新。但是现在不想刷新，只想给（更新）一部分，
          * 所以用了回调函数callback。
          * 为了只更新一部分，前端想办法写一个操作，让后端给前端的时候，前端只接受数据，而不需要关系让后端给前端控制视图。
          * 之前forward和direct更新视图时，视图的控制权在后端。！！！！ajax把视图的控制权交给了前端！！！！。
          *
          * 根据jquery.post源码“jQuery[ method ] = function( url, data, callback, type ) ”success就是callback函数，是post成功后，处理返回东西的方法。
          * data就是接收数据，由data管数据。
          * 后端不需要重定向和转发了，只需要给前端数据，即这里的data。前后端交互数据用json。学ajax和json就是为了前后端彻底分离。
          *
          * ？？？这个data（data改名为data1后仍可正常得到服务器数据，推测不是通过参数名绑定后端，而是参数的顺序）是response.getWriter().print设置的吗？：参考：https://www.cnblogs.com/zzw3014/p/11857097.html
          * ？？？status（post请求成功与否）是存在servlet里然后这里直接调吗？
          * 想彻底弄懂这块应该要会js+jquery
          * */
          success:function (data1,status) {
            console.log("data1="+data1);
            console.log("status="+status); //200 300 400 500
          },
          error:function(){}
                })
      }
    </script>
  </head>
  <body>

  <%--输入框失去焦点的时候，会调用a函数--%>
  用户名：<input type="text" id="username" onblur="a()">

  </body>
</html>
