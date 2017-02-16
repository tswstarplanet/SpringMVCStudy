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

    <link href="<c:url value='/styles/bootstrap.min.css' />" rel="stylesheet"
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
            <button type="submit" class="btn btn-primary" onclick="makeFriend()">提交</button>
        </form>
    </c:if>

</div>
<!-- /container -->
<script src="<c:url value='/scripts/jquery-3.1.1.min.js' />"></script>
<script src="<c:url value='/scripts/bootstrap.min.js' />"></script>

<script>
    $(document).ready(function () {
        alert(123);
       $("#makeFriendForm").submit(function (event) {
          event.preventDefault();
          makeFriend();
       });
    });

    function makeFriend() {
        alert(1)
        var makeFriendModel = {};
        makeFriendModel["username"] = "333";
        makeFriendModel["username"] = $("#makeFriendUsername").val();
        alert(2);
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/friend/makeFriend",
            data: JSON.stringify(makeFriendModel),
            dataType: "json",
            success: function (data) {
                console.log("success: ", data);
            },
            error: function (e) {
                console.log("error: ", e);
            },
            done: function (e) {
                console.log("DONE");
            }
        });
    }
</script>

<%--<script>--%>
    <%--$('#spittleForm').submit(function (e) {--%>
        <%--var form = $('#spittleForm');--%>
        <%--e.preventDefault();--%>
        <%--var data = {};--%>

        <%--$.each(this, function (i, v) {--%>
            <%--var input = $(v);--%>
            <%--data[input.attr("name")] = input.val();--%>
            <%--delete data["undefined"];--%>
        <%--});--%>
        <%--$.ajax({--%>
            <%--type : "POST",--%>
            <%--contentType : "application/json; charset=utf-8",--%>
            <%--url : "${home}/publish",--%>
            <%--data : JSON.stringify(data),--%>
            <%--dataType : "json",--%>
            <%--timeout : 5000,--%>
            <%--success : function (data) {--%>
                <%--console.log("SUCCESS: ", e);--%>
            <%--},--%>
            <%--error : function (e) {--%>
                <%--console.log("ERROR: ", e);--%>
            <%--}--%>
            <%--done : function (e) {--%>
                <%--console.log("DONE");--%>
            <%--}--%>
        <%--});--%>
    <%--})--%>

    <%--function displayMySpittles() {--%>
        <%--var data = {};--%>
        <%--$.ajax({--%>
            <%--type : "POST",--%>
            <%--contentType : "application/json",--%>
            <%--url : "${home}/publish",--%>
            <%--data : JSON.stringify(data),--%>
            <%--dataType : "json",--%>
            <%--timeout : 5000,--%>
            <%--success : function (data) {--%>
                <%--console.log("SUCCESS: ", e);--%>
            <%--},--%>
            <%--error : function (e) {--%>
                <%--console.log("ERROR: ", e);--%>
            <%--}--%>
            <%--done : function (e) {--%>
                <%--console.log("DONE");--%>
            <%--}--%>
        <%--});--%>
    <%--}--%>
<%-- </script> --%>
</body>
</html>
