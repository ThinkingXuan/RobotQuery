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
<form action="${pageContext.request.contextPath}/user/getSalesVolume.action" method="post">

    <input type="text" name="startTime">
    <input type="text" name="endTime">
    <input type="submit">
</form>
</body>
</html>
