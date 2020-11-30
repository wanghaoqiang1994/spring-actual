<%--
  Created by IntelliJ IDEA.
  User: haoqiangwang3
  Date: 2020/11/30
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>筛选一级科目</title>
</head>
<body>
<form action="<%= request.getContextPath() %>/uploadHandlerOne" method="post" enctype="multipart/form-data">
    <h1 color="white" size="5px">文件上传功能(筛选一级科目)</h1><br><br>
    保存文件地址：<input name="saveFilePath"><br><br>
    上传文件：<input type="file" name="uploadFile"><br><br>
    <input type="submit" value="上传"><br><br>
</form>
</body>
</html>
