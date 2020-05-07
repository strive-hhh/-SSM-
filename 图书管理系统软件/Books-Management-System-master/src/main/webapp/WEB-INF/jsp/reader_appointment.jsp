<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>我的预约</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-3.2.1.js"></script>
    <script src="js/bootstrap.min.js" ></script>
    <style>
        body{
            background-color: rgb(240,242,245);
        }
    </style>
</head>
<body>

<nav class="navbar navbar-default" role="navigation" style="background-color:#fff" style="background-color:#fff">
    <div class="container-fluid">
        <div class="navbar-header" style="margin-left: 8%;margin-right: 1%">
            <a class="navbar-brand " href="reader_borrowlist.html"><p class="text-primary">我的图书馆</p></a>
        </div>
        <div class="collapse navbar-collapse" id="example-navbar-collapse">
            <ul class="nav navbar-nav navbar-left">
                <li>
                    <a href="reader_querybook.html" >
                        图书查询
                    </a>
                </li>
                <li >
                    <a href="mylend.html" >
                        我的借还
                    </a>
                </li>
                <li >
                    <a href="myshelves.html" >
                        我的书架
                    </a>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        预约管理
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="appointment_add.html">增加预约</a></li>
                        <li class="divider"></li>
                        <li><a href="myappointments.html">预约记录</a></li>
                    </ul>
                </li>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        账号管理
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="reader_info.html">个人信息</a></li>
                        <li class="divider"></li>
                        <li><a href="reader_repasswd.html">密码修改</a></li>
                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="reader_info.html">&nbsp;${readercard.name}，已登录</a></li>
                <li><a href="login.html">&nbsp;退出</a></li>
            </ul>
        </div>
    </div>
</nav>

<div style="position: relative;top: 10%">
    <c:if test="${!empty succ}">
        <div class="alert alert-success alert-dismissable">
            <button type="button" class="close" data-dismiss="alert"
                    aria-hidden="true">
                &times;
            </button>
                ${succ}
        </div>
    </c:if>
    <c:if test="${!empty error}">
        <div class="alert alert-danger alert-dismissable">
            <button type="button" class="close" data-dismiss="alert"
                    aria-hidden="true">
                &times;
            </button>
                ${error}
        </div>
    </c:if>
</div>


<div class="panel panel-default" style="width: 90%;margin-left: 5%">
    <div class="panel-heading" >
        <h3 class="panel-title">
            我的预约
        </h3>
    </div>
    <div class="panel-body">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>读者证号</th>
                <th>书名</th>
                <th>作者</th>
                <th>出版社</th>
                <th>预约时间</th>
                <th>状态</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${appointments}" var="appointment">
                <tr>
                    <td><c:out value="${appointment.readerId}"></c:out></td>
                    <td><c:out value="${appointment.name}"></c:out></td>
                    <td><c:out value="${appointment.author}"></c:out></td>
                    <td><c:out value="${appointment.publish}"></c:out></td>
                    <td><c:out value="${appointment.appoint_time}"></c:out></td>
                    <c:if test="${appointment.state==0}">
                        <td>待审核</td>
                    </c:if>
                    <c:if test="${appointment.state==1}">
                        <td>已通过</td>
                    </c:if>
                    <c:if test="${appointment.state==-1}">
                        <td>未通过</td>
                    </c:if>

               </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
