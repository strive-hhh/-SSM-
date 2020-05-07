<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>预约记录</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-3.2.1.js"></script>
    <script src="js/bootstrap.min.js" ></script>
    <script src=""></script>
    <style>
        body{
            background-color: rgb(240,242,245);
        }
        .btn_disable{
            background-color:#d4d4d4;/* 设置按钮背景颜色为灰色 */
            border-color:#d4d4d4;/* 边框线颜色应与背景颜色一致 */
            color:#fff;/* 设置字体颜色为白色 */
        }
    </style>

    <script type="text/javascript">
        $(function(){
            /*绑定按钮点击事件*/
            $("#bt").one("click",function(){
                /*设置禁用效果，不再响应点击事件*/
                $("#btn").attr({"disabled":"disabled"});
                /*设置样式*/
                $("#btn").addClass("btn_disable");
            });
        });
    </script>
</head>

<body>
<c:if test="${!empty info}">
    <script>alert("${info}");window.location.href="allappointments.html"</script>
</c:if>
<nav  style="position:fixed;z-index: 999;width: 100%;background-color: #fff" class="navbar navbar-default" role="navigation" >
    <div class="container-fluid">
        <div class="navbar-header" style="margin-left: 8%;margin-right: 1%">
            <a class="navbar-brand" href="admin_main.html">图书管理系统</a>
        </div>
        <div class="collapse navbar-collapse" >
            <ul class="nav navbar-nav navbar-left">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        图书管理
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="allbooks.html">全部图书</a></li>
                        <li class="divider"></li>
                        <li><a href="book_add.html">增加图书</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        图书类别管理
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="allbookclasses.html">全部图书类别</a></li>
                        <li class="divider"></li>
                        <li><a href="bookclass_add.html">增加图书类别</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        读者管理
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="allreaders.html">全部读者</a></li>
                        <li class="divider"></li>
                        <li><a href="reader_add.html">增加读者</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        借还管理
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="lendlist.html">借还日志</a></li>
                    </ul>
                </li>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        预约管理
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="allappointments.html">预约记录</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        账号管理
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="allauthorities.html">权限维护</a></li>
                        <li class="divider"></li>
                        <li><a href="authority_add.html">权限添加</a></li>
                        <li class="divider"></li>
                        <li><a href="admin_repasswd.html">密码修改</a></li>
                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="login.html">&nbsp;${admin.adminId}，已登录</a></li>
                <li><a href="logout.html">&nbsp;退出</a></li>
            </ul>
        </div>
    </div>
</nav>

<div style="position: relative;top: 15%">
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



<div class="panel panel-default" style="position:relative;top: 80px;width: 90%;margin-left: 5%">
    <div class="panel-heading">
        <h3 class="panel-title">
            预约记录
        </h3>
    </div>
    <div class="panel-body">
        <table class="table table-hover" >
            <thead>
            <tr>
                <th>读者号</th>
                <th>书名</th>
                <th>作者</th>
                <th>出版社</th>
                <th>预约时间</th>
                <th>同意</th>
                <th>不同意</th>
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


                        <td>
                            <a href="update_appointment_do.html?name=<c:out value="${appointment.name}"></c:out>">

                                    <input id="bt" type="button"
                                            <c:if test="${appointment.state eq 1}">
                                               value="已同意" class="btn btn-xs"
                                            </c:if>

                                            <c:if test="${appointment.state ne 1}">
                                                   value="同意" class="btn btn-success btn-xs"
                                            </c:if>"
                                    >
                            </a>
                        </td>

                        <td>
                            <a href="appointment_delete.html?name=<c:out value="${appointment.name}"></c:out>">
                                <input id="bt1" type="button"
                                    <c:if test="${appointment.state eq -1}">
                                       value="不同意" class="btn-xs"
                                    </c:if>
                                    <c:if test="${appointment.state ne -1}">
                                        value="不同意" class="btn btn-danger btn-xs"
                                    </c:if>
                                >

                            </a>
                        </td>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
