<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h2>Welcome ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a></h2>

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
            <p>我的好友</p>
            <ul id="myFriendList">
                <li v-for="friend in friendList">
                    {{ friend.username }}, {{ friend.id }}
                </li>
            </ul>
        </div>
    </c:if>

</div>
<!-- /container -->
<script type="application/javascript" src="<c:url value = '/resource/js/jquery-3.1.1.min.js' />"></script>
<script type="application/javascript" src="<c:url value = '/resource/js/bootstrap.min.js' />"></script>

<script>

</script>

<script type="application/javascript">

    $(document).ready(function () {

       $.ajax({
           type: "GET",
           contentType: "application/json",
           url: "/friend/getMyFriends",
           data: null,
           dataType: "json",
           success: function (data) {
               var friendUl = new Vue({
                   el: '#myFriendList',
                   data: {
                       friendList: data
                   }
               });
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
