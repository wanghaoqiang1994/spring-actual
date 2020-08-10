<%--
  Created by IntelliJ IDEA.
  User: haoqiangwang3
  Date: 2020/8/10
  Time: 13:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户列表</title>

    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/bootstrap/css/bootstrap-table.css">

    <script type="text/javascript"
            src="<%=request.getContextPath() %>/static/bootstrap/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/bootstrap/js/bootstrap.min.js"></script>


    <script type="text/javascript" src="<%=request.getContextPath() %>/static/bootstrap/js/bootstrap-table.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath() %>/static/bootstrap/js/bootstrap-table-zh-CN.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
</head>
<body>
<div>
    <div>
        <div class="col-*-12">
            <div class="container">
                <div class="row">
                    <div id="toolbar">
                        <div class="btn btn-primary" data-toggle="modal" data-target="#addModal">添加记录</div>
                    </div>
                    <table id="mytab" class="table table-hover"></table>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        //根据窗口调整表格高度
        $(window).resize(function() {
            $('#mytab').bootstrapTable('resetView', {
                height: tableHeight()
            })
        })

        $("#mytab").bootstrapTable('destroy'); //清除缓存表格数据
        $("#mytab").bootstrapTable({
            method: 'post',
            url: "<%=request.getContextPath() %>/user/list",//请求路径
            contentType: "application/x-www-form-urlencoded",
            dataType: "json",//期待返回数据类型
            dataField: "rows",
            striped: true, //是否显示行间隔色
            //height: tableHeight(),
            sidePagination: 'server',//server:服务器端分页|client：前端分页
            pagination: true,//是否分页
            pageNumber: 1, //初始化加载第一页
            pageSize: 10,//单页记录数
            pageList: [5, 20, 50, 100],//可选择单页记录数
            showRefresh: true,//刷新按钮
            showColumns: true,//列选择按钮
            showToggle: true,//详细视图和列表视图的切换按钮
            paginationShowPageGo: true,
            showJumpto: true,
            toolbar: "#toolbar",//指定工具栏
            queryParams: function getParams(params) {
                var temp = {
                    limit: params.limit, //页面大小
                    offset: params.offset,//起始
                    page: (params.offset / params.limit) + 1, //页码
                    //query_id : $("query_id").val(), //自定义条件
                }
                return temp;
            },
            columns: [
                {
                    title: "全选",
                    field: "select",
                    checkbox: true,
                    width: 20,//宽度
                    align: "center",//水平
                    valign: "middle"//垂直
                },
                {
                    title: "学号",//标题
                    field: "id",//键名
                    sortable: true//是否可排序
                },
                {
                    title: "姓名",
                    field: "name",
                    sortable: true,
                    titleTooltip: "this is name"
                },
                {
                    title: "年龄",
                    field: "age",
                    sortable: true
                },
                {
                    title: "具体信息",
                    field: "info",
                    formatter: 'infoFormatter',//对本列数据做格式化
                },
                {
                    title: "操作",
                    field: 'operate',
                    align: 'center',
                    formatter: operation,//对资源进行扫做
                }
            ],
            onLoadSuccess: function (data) {
                console.log("数据加载成功...");
            },
            onLoadError: function () {
                alert("数据加载失败");
            },
            onClickRow: function (row, $element) {
                //$element是当前tr的jquery对象
                $element.css("background-color", "green");
            },//单击row事件
            locale: "zh-CN", //中文支持
            detailView: false, //是否显示详情折叠
            detailFormatter: function (index, row, element) {
                var html = '';
                $.each(row, function (key, val) {
                    html += "<p>" + key + ":" + val + "</p>"
                });
                return html;
            }
        });

        /*$("#addRecord").click(function(){
            alert("name:" + $("#name").val() + " age:" +$("#age").val());
        });*/
    })

    function operation(value, row, index) {
        var id = value;
        var result = "";
        result += "<a href='javascript:;' class='btn btn-xs green' onclick=\"editViewById('" + id + "', view='view')\" title='查看'><span class='glyphicon glyphicon-search'></span></a>";
        result += "<a href='javascript:;' class='btn btn-xs blue' onclick=\"editViewById('" + id + "')\" title='编辑'><span class='glyphicon glyphicon-pencil'></span></a>";
        result += "<a href='javascript:;' class='btn btn-xs red' onclick=\"editViewById('" + id + "')\" title='删除'><span class='glyphicon glyphicon-remove'></span></a>";

        return result;
    }

    function infoFormatter(value, row, index) {
        return "id:" + row.id + " name:" + row.name + " age:" + row.age;
    }

    function editViewById(id, view) {

    }

    function tableHeight() {
        return $(window).height() - 50;
    }
</script>
</body>
</html>
