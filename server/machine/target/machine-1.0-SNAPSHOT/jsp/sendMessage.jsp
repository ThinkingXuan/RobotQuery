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
<h1>聊天测试</h1>
        <form action="${pageContext.request.contextPath}/message/sendToRobot.action" method="post">
            user_username<input type="text" name="userId">
            content<input type="text" name="content">
            <input type="submit">
        </form>
</body>
</html>
