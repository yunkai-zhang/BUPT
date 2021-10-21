<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--本form.jsp不能放到web-inf文件夹下，否则不能在弹出localhost时直接访问，而只能通过servlet转过去。--%>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <form action="hello" method="post">
        <input type="text" name="method"/>
        <input type="submit"/>
    </form>

</body>
</html>
