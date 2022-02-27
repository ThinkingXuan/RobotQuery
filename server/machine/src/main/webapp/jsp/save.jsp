<%--
  Created by IntelliJ IDEA.
  User: mirko
  Date: 2017/3/15
  Time: 10:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>注册测试</h1>
        <form action="${pageContext.request.contextPath}/user/userRegister.action" method="post">
            role<input type="text" name="roleId">
            usernam<input type="text" name="userUsername">
            password<input type="password" name="userPassword">
            email<input type="text" name="userEmail">
            <input type="submit">
        </form>
</body>
</html>
