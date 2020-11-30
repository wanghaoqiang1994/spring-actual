<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <!-- IE将使用最新的引擎渲染网页 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- 页面的宽度与设备屏幕的宽度一致
         初始缩放比例 1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>首页</title>
</head>

<body>

<h1>
    ${message} : ${time}
</h1>
<P>
    <a href="<%= request.getContextPath() %>/plat/handlerOne">功能一</a>
</P>
</body>
</html>