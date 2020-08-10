<%--
  Created by IntelliJ IDEA.
  User: haoqiangwang3
  Date: 2020/8/6
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>注册</title>

    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/bootstrap/css/bootstrap.min.css">

    <script type="text/javascript" src="<%=request.getContextPath() %>/static/bootstrap/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/bootstrap/js/bootstrap.min.js"></script>

    <script type="text/javascript">
        function submitRegister2() {
            var userId = $("#user_id").val();
            if(userId == null || userId == ""){
                alert("用户号不能为空！")
                return;
            }
            var password = $("#password").val();
            if(password == null || password == ""){
                alert("用户密码不能为空！")
                return;
            }
            $.ajax({
                type:"post",
                url:"<%=request.getContextPath() %>/registerSure",
                data:{"userId":userId, "password":password},
                dataType:"json",
                success : function (data) {
                    alert("请求成功,结果为：" + data.flag);
                    if("success" == data.flag){
                        console.info("注册成功");
                        window.location.href = "<%=request.getContextPath() %>/success";
                    }else{
                        window.location.href = "<%=request.getContextPath() %>/fail";
                    }
                },error : function (error) {
                    alert("出现异常，" + error)
                }
             });
        }
    </script>
</head>
<body>
<form>
    <div class="form-group">
        <label class="col-sm-2 col-sm-2 control-label" for="user_id">用户号：</label>
        <div class="col-lg-3 col-sm-3">
            <input type="text" class="form-control" id="user_id" name="user_id">
        </div>

        <label class="col-sm-2 col-sm-2 control-label" for="password">用户密码：</label>
        <div class="col-lg-3 col-sm-3">
            <input type="text" class="form-control" id="password" name="password">
        </div>
    </div>
    <div style="text-align: center">
        <button class="btn btn-primary" type="button" onclick="submitRegister2();">确认注册</button>
    </div>
</form>
</body>
</html>
