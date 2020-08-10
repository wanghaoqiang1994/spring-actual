<%--
  Created by IntelliJ IDEA.
  User: haoqiangwang3
  Date: 2020/7/28
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>欢迎使用</title>
</head>
<body>
<div>
    <label>项目根路径值为：</label><%=request.getContextPath()%>
</div>

<div>
    <ul>
        <li><a href="<%=request.getContextPath() %>/getSendMsg"> 发送信息</a></li>
        <li><a href="<%=request.getContextPath() %>/getSendMsg"> 发送信息</a></li>
    </ul>
</div>

</body>
</html>
