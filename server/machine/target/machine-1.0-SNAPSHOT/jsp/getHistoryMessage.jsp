<%--
  Created by IntelliJ IDEA.
  User: mirko
  Time: 10:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>getMessage</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/message/getHistoryMessages.action" method="post">
    userId<input type="text" name="userId">
    page<input type="text" name="page">
    size<input type="text" name="size">
    <input type="submit">
</form>
</body>
</html>
