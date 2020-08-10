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

    <!-- 引入外部bootstrap的css文件(压缩版)，版本是3.3.7 -->
    <link rel="stylesheet" href="static/bootstrap/css/bootstrap.min.css">

    <!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
    <!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.jsdelivr.net/npm/html5shiv@3.7.3/dist/html5shiv.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/respond.js@1.4.2/dest/respond.min.js"></script>
    <![endif]-->

    <style type="text/css">
        .input-group {
            margin: 10px 0px; //输入框上下外边距为10px, 左右为0px
        }

        h3 {
            padding: 5px;
            border-bottom: 1px solid #ddd;// h3字体下边框
        }

        li {
            list-style-type: square;//列表项图标为小正方形
            margin: 10 px 0;// 上下外边距是10px
        }

        em {// 强调的样式
            color: #c7254e;
            font-style: inherit;
            background-color: #f9f2f4;
        }
    </style>
    
    <script type="text/javascript">
        function loginFun() {
            var user_id = $("#username").val();
            if(user_id == null || user_id == ""){
                alert("用户名不能为空！");
                return;
            }
            var user_password = $("#password").val();
            if(user_password == null || user_password == ""){
                alert("密码不能为空！");
                return;
            }

            //获取选中的角色
            var type = $('input:radio:checked').val();
            console.info("radio选中的值为：" + type);
            if(type == null || type == "admin"){
                type = "admin";
            }else{
                type = "user";
            }

            $.ajax({
                type: "post",
                url: "<%=request.getContextPath() %>/login",
                data: {"username" : user_id, "password" : user_password, "type" : type},
                dataType: "json",
                success: function (data) {
                    alert("请求成功,结果为：" + data.flag);
                    if ("success" == data.flag) {
                        window.location.href = "<%=request.getContextPath() %>/success";
                    } else {
                        window.location.href = "<%=request.getContextPath() %>/fail";
                    }
                }
            });
        }
    </script>
</head>

<body background="static/image/back.jpg" style="background-repeat:no-repeat;
background-size: 100% 100%; background-attachment: fixed;">
<div class="row" style="margin-top:80px;" >
    <div class="col-md-4" style="margin-left: 34%">
        <div class="well col-md-10">
            <h3 style="text-align: center">用户登录</h3>
            <form id="login" action="<%= request.getContextPath() %>/login">
                <div class="input-group input-group-md">
                    <span class="input-group-addon" id="sizing-addon1">
                        <i class="glyphicon glyphicon-user" aria-hidden="true"></i></span>
                    <input type="text" class="form-control" id="username" placeholder="用户名" value="" >
                </div>
                <div class="input-group input-group-md">
                    <span class="input-group-addon" id="sizing-addon2"><i class="glyphicon glyphicon-lock"></i></span>
                    <input type="password" class="form-control" id="password" placeholder="密码" value="" >
                </div>
                <div class="well well-sm" style="text-align:center;">
                    <input type="radio" name="kind" value="user"> 用户
                    <input type="radio" name="kind" value="admin" style="margin-left: 30px"> 管理员
                </div>
                <div style="text-align: center">
                    <button type="button" onclick="loginFun()" class="btn btn-primary" style="margin-right: 80px">登录</button>
                    <a href="<%=request.getContextPath() %>/register"> <button type="button" class="btn btn-primary" >注册</button></a>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- NO.1 加载框架依赖的jQuery文件(压缩版)，版本是3.3.1 -->
<script src="static/bootstrap/js/jquery-3.3.1.min.js"></script>
<!-- NO.2 加载Bootstrap的所有JS插件，版本是3.3.7 -->
<script src="static/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>