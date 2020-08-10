<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: haoqiangwang3
  Date: 2020/8/6
  Time: 17:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>成功</title>
</head>
<body>

成功页面
<div>
    <shiro:hasPermission name="test:button">
        <button type="button">权限</button>
    </shiro:hasPermission>
</div>
<div>
    <a href="<%= request.getContextPath()%>/logout">退出</a>
</div>

</body>
</html>
