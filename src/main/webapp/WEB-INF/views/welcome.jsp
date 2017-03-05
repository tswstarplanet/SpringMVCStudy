<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <c:url var="home" value="/" scope="request" />
    <title>Welcome</title>

    <script type="application/javascript" src="<c:url value = '/resource/js/vue.js' />"></script>

    <link href="<c:url value='/resource/css/bootstrap.min.css' />" rel="stylesheet"
          type="text/css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

    <![endif]-->
</head>
<body>
<div class="container">

    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="get" action="/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h2>Welcome ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a></h2>
        <p>发表朋友圈</p>
        <form id="spittleForm" method="post" action="/spittles/publish">
            <input type="text" name="content" class="form-control" placeholder="请输入内容">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button type="submit" class="btn btn-primary">提交</button>
        </form>

        <p>我的消息</p>
        <c:if test="${not empty mySpittleList}">
            <ul>
                <c:forEach var="mySpittle" items="${mySpittleList}">
                    <li>${mySpittle.content}</li>
                </c:forEach>
            </ul>
        </c:if>
        <form id="makeFriendForm">
            <input type="text" id="makeFriendUsername" name="username" class="form-control" placeholder="请输入对方用户名">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button id="makeFriendApply" type="submit" class="btn btn-primary">提交</button>
        </form>
        <div>
            <p>我的好友申请</p>
            <ul id="myFriendApplyList">
                <li is="friendApplyList-apply"
                    v-for="friendApply in friendApplyList"
                    v-bind:apply="friendApply"
                    >
                </li>
            </ul>
        </div>

        <div>
            <p>我的朋友圈</p>
            <%--<ul id="myFriendSpittleList">--%>
                <%--<li is="friendSpittleList-vue"--%>
                    <%--v-for="friendSpittle in friendSpittleList"--%>
                    <%--v-bind:spittle="friendSpittle"--%>
                <%-->--%>
                <%--</li>--%>
            <%--</ul>--%>
            <input type="text" hidden="hidden" id="pageNumber_input" value="0" />
            <input type="button" id="previous_button" onclick="getFriendSpittleByPage(0)" value="上一页" />
            <input type="button" id="next_button" onclick="getFriendSpittleByPage(1)" value="下一页" />
        </div>
    </c:if>

</div>
<!-- /container -->
<script type="application/javascript" src="<c:url value = '/resource/js/jquery-3.1.1.min.js' />"></script>
<script type="application/javascript" src="<c:url value = '/resource/js/bootstrap.min.js' />"></script>

<script>

</script>

<script type="application/javascript">

//    var friendSpittles = new Vue({
//        el: 'myFriendSpittleList',
//        data: {
//            friendSpittleList: null
//        }
//    })
//
//    Vue.component('friendSpittleList-vue', {
//        template: '\
//                <li>\
//                    {{ spittle.userid }}: {{ spittle.content }}\
//                </li>\
//        ',
//        props: ['spittle']
//    })

    function getFriendSpittleByPage(flag) {
        var pageNumber = -1;
        if (flag == 0) {
            pageNumber = new Number($('#pageNumber_input').val()) - 1;
        } else {
            pageNumber = new Number($('#pageNumber_input').val()) + 1;
        }
        if (pageNumber < 1) {
            return;
        }
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: "/spittles/readFriendSpittles/" + pageNumber,
            data: null,
            dataType: "json",
            success: function (data) {
                if (flag == 0) {
                    var pageNumber2 = new Number($('#pageNumber_input').val()) - 1;
                    $('#pageNumber_input').val(pageNumber2);
                } else {
                    var pageNumber2 = new Number($('#pageNumber_input').val()) + 1;
                    $('#pageNumber_input').val(pageNumber2);
                }
                console.log("success: ", data);
            },
            error: function (e) {
                console.log("error: ", e);
            },
            done: function (e) {
                console.log("done");
            }
        })
    }

    var friendUl = new Vue({
        el: '#myFriendApplyList',
        data: {
            friendApplyList: null
        }
    })

    Vue.component('friendApplyList-apply', {
        template: '\
                <li>\
                    {{ apply.id }}, {{apply.username}}\
                    <button v-on:click="approveFriendApply(apply.id)">批准</button>\
                </li>\
            ',
        methods: {
            approveFriendApply: function (id) {
                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "/friend/approveFriendApply/" + id,
                    data: null,
                    dataType: "json",
                    success: function (data) {
                        alert(data.status);
                        console.log("success: ", data);
                    },
                    error: function (e) {
                        console.log("error: ", e);
                    },
                    done: function (e) {
                        console.log("done");
                    }
                });
            }
        },
        props: ['apply']
    })



    $(document).ready(function () {

        $.ajax({
           type: "GET",
           contentType: "application/json",
           url: "/friend/getMyFriendApply",
           data: null,
           dataType: "json",
           success: function (data) {
               friendUl.friendApplyList = data;
               console.log("success: ", data);
           },
           error: function (e) {
               console.log("error: ", e);
           },
           done: function (e) {
               console.log("done");
           }
        });

        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: "/spittles/getFriendSpittles",
            data: null,
            dataType: "json",
            success: function (data) {
                console.log("success: ", data);
            },
            error: function (e) {
                console.log("error: ", e);
            },
            done: function (e) {
                console.log("done");
            }
        });
    });

    (function ($) {
        $.fn.serializeFormJSON = function () {
            var o = {};
            var a = this.serializeArray();
            $.each(a, function () {
                if (o[this.name]) {
                    if (!o[this.name].push) {
                        o[this.name] = [o[this.name]];
                    }
                    o[this.name].push(this.value || '');
                } else {
                    o[this.name] = this.value || '';
                }
            });
            return o;
        }
    })(jQuery);

    $('#makeFriendForm').submit(function (e) {
        alert(1);
        e.preventDefault();
        var data = $(this).serializeFormJSON();
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/friend/makeFriend",
            data: JSON.stringify(data),
            dataType: "json",
            success: function (data) {
                alert("success: " + JSON.stringify(data));
            },
            error: function (e) {
                console.log("error: ", e);
            },
            done: function (e) {
                console.log("DONE");
            }
        });
    });

</script>

</body>
</html>
