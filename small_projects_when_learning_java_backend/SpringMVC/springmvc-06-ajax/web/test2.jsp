<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2021/8/5
  Time: 16:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ajax实现对象传输</title>

    <%--引入jquery--%>
    <script src="${pageContext.request.contextPath}/statics/js/jquery-3.6.0.js"></script>

    <script>

        /*自执行函数，tomcat一起来这函数就会运行，就会不停监听click。这就是所谓的入口函数
        *
        * 如果不加$(),默认在页面加载前搜索btn，但是此时没有btn，所以不会给btn注册点击事件
        * */
        $(function(){

            // click动作会有一个function
            $("#btn").click(function () {
                console.log("点击我干嘛")

                /*现在简写参数，把数据写到参数里面，和之前在大括号中写参数等效
                *
                * jQuery.post(url,param[可以省略], success)
                * */
                jQuery.post("${pageContext.request.contextPath}/a2",function(data){
                    // console.log("data="+data);

                    //把data的数据拆分到下面的html中
                    var html="<>";

                    //let(更安全)使i只能在for方法块中使用。如果是var，for外也能使用。但是我当前js不支持let，先用var吧，虽然js支持版本可以直接idea中调
                    for(var i=0; i<data.length;i++){

                        html+="<tr>"+"" +
                            "<td>" +data[i].name+"</td>"+
                            "<td>" +data[i].age+"</td>"+
                            "<td>" +data[i].sex+"</td>"+
                            "</tr>"
                    }

                    //把post中的html放到id为content的tbody中用
                    $("#content").html(html);
                })

            })

        })

    </script>
</head>
<body>

<input type="button" value="加载数据" id="btn">
<table>
    <tr>
        <td>姓名</td>
        <td>年龄</td>
        <td>性别</td>
    </tr>
    <tbody id="content">
        <%--数据在后台。所以要请求后台，成功后用callback来获取后台的数据--%>
    </tbody>
</table>

</body>
</html>
