<%--
  Created by IntelliJ IDEA.
  User: haoqiangwang3
  Date: 2020/8/13
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <script type="text/javascript" src="<%= request.getContextPath()%>/static/js/vue.js "></script>
</head>
<body>

<div id="app-1">
    <div v-html="message"></div>
</div>

<div id="app-2">
    <span v-bind:title = "message">
        鼠标悬停几秒查看此处动态绑定的提示信息！
    </span>
</div>

<div id="app-5">
    <p>{{message}}</p>
    <button v-on:click="reverseMessage">反转消息</button>
</div>

<div id="app-6">
    <p>{{message}}</p>
    <input v-model="message">
</div>

<div id="app-7">
    <ol>
        <!-- 自定义组件 -->
        <todo-item
            v-for="item in groceryList"
            v-bind:todo="item"
            v-bind:key="item.id"
        ></todo-item>
    </ol>
</div>
<script>
    new Vue({
        el: '#app-1',
        data: {
            message: 'hello'
        }
    })

    new Vue({
        el: '#app-2',
        data: {
            message: '页面加载于' + new Date().toLocaleString()
        }
    })

    var app5 = new Vue({
        el: '#app-5',
        data: {
            message: 'hello vue.js'
        },
        methods: {
            reverseMessage: function () {
                this.message = this.message.split('').reverse().join('')
            }
        }
    })

    var app6 = new Vue({
        el: '#app-6',
        data: {
            message: '输入...'
        }
    })

    Vue.component('todo-item',{
        props: ['todo'],
        template: '<li>{{todo.text}}</li>'
    })

    var app7 = new Vue({
        el: '#app-7',
        data: {
            groceryList: [
                {id:0, text:'蔬菜'},
                {id:1, text:'奶酪'},
                {id:2, text:'其他'}
            ]
        }
    })
</script>
</body>
</html>
