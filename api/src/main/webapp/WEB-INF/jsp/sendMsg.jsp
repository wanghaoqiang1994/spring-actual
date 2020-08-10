<%--
  Created by IntelliJ IDEA.
  User: haoqiangwang3
  Date: 2020/8/5
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>发送信息</title>
</head>

<!-- 在线引入样式 -->
<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<body>
<div class="page-heading">
    <ul class="breadcrumb">
        <li><a>Demo</a></li>
        <li class="active">发送信息</li>
    </ul>
</div>

<div class="row">
    <div class="col-sm-12">
        <header class="panel-heading">发送信息
            <span class="tools pull-right"><a href="javascript:;" class="fa fa-chevron-down"></a></span>
        </header>
        <div class="panel-body" style="text-align: center">
            <form role="form" class="form-horizontal" id="sendMsg"
                  action="<%=request.getContextPath() %>/deal/sendMsg" method="post">
                <div class="form-group">
                    <label class="col-lg-2 col-sm-2 control-label" for="user_id">用户id：</label>
                    <div class="col-lg-3 col-sm-3">
                        <input type="text" class="form-control" id="user_id" name="user_id">
                    </div>

                    <label class="col-sm-2 col-sm-2 control-label" for="user_name">用户姓名：</label>
                    <div class="col-lg-3 col-sm-3">
                        <input type="text" class="form-control" id="user_name" name="user_name">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 col-sm-2 control-label" for="receive_name">接收id：</label>
                    <div class="col-lg-3 col-sm-3">
                        <input type="text" class="form-control" id="receive_name" name="receive_name">
                    </div>

                    <label class="col-sm-2 col-sm-2 control-label" for="send_context">信息内容：</label>
                    <div class="col-lg-3 col-sm-3">
                        <input type="text" class="form-control" id="send_context" name="send_context">
                    </div>
                </div>
                <div class="modal-footer" style="text-align: center">
                    <button class="btn btn-primary" type="button" onclick=" submitFun();">提交</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript">
    function submitFun() {
        var user_id = document.getElementById("user_id").value;
        if(user_id == "" || user_id == null){
            alert("用户id不能为空");
            return;
        }

        document.getElementById("sendMsg").submit();
    }
</script>
</body>
</html>
