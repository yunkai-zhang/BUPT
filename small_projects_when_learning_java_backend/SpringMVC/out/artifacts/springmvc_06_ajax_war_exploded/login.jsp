<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login using ajax</title>

    <%--引入jquery
    必须的步骤--%>
    <script src="${pageContext.request.contextPath}/statics/js/jquery-3.6.0.js"></script>

    <script>
        function a1() {
            jQuery.post({
                url:"${pageContext.request.contextPath}/a3",
                // $("#name")一定不要缺少#，选择器必须带#。少了#取不到标签
                data:{"name":$("#name").val()},
            success: function (data) {
                // console.log("data="+data.toString());

                if(data.toString()=="ok"){
                    // 使用选择器获取span，并操作
                    $("#userInfo").css("color","green");
                } else{
                    $("#userInfo").css("color","red");
                }
                $("#userInfo").html(data)
            }
            })

        }
        function a2(){
            jQuery.post({
                url:"${pageContext.request.contextPath}/a3",
                // $("#pwd")一定不要缺少#，选择器必须带#。少了#取不到标签
                data:{"pwd":$("#pwd").val()},
                success: function (data) {
                    // console.log("data="+data.toString());
                    if(data.toString()=="ok"){
                        // 使用选择器获取span，并操作
                        $("#pwdInfo").css("color","green");
                    } else{
                        $("#pwdInfo").css("color","red");
                    }
                    $("#pwdInfo").html(data)
                }
            })

        }
    </script>
</head>
<body>

<p>
    用户名：<input type="text" id="name" onblur="a1()">
    <%--span标签来提示信息：用户名存在与否--%>
    <span id="userInfo"></span>
</p>
<p>
    密码：<input type="text" id="pwd" onblur="a2()">
    <span id="pwdInfo"></span>
</p>

</body>
</html>
