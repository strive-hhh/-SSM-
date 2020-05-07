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





<div style="position: relative;top: 10%;width: 80%;margin-left: 10%">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">添加图书预约</h3>
        </div>
        <div class="panel-body">
            <form action="appointment_add_do.html" method="post" id="addappointment">
                <div class="form-group">
                    <label for="readerId">读者证号</label>
                    <input readonly="readonly" type="text" class="form-control" name="readerId" id="readerId" value="${readercard.readerId}">
                </div>

                <div class="form-group">
                    <label for="name">书名</label>
                    <input type="text" class="form-control" name="name" id="name"  placeholder="请输入书名">
                </div>

                <div class="form-group">
                    <label for="author">作者</label>
                    <input type="text" class="form-control" name="author" id="author"  placeholder="请输入作者名">
                </div>

                <div class="form-group">
                    <label for="publish">出版社</label>
                    <input type="text" class="form-control"  name="publish" id="publish"  placeholder="请输入出版社">
                </div>

                <div class="form-group">
                    <label for="appoint_time">出版日期</label>
                    <input type="text" class="form-control"  name="appoint_time" id="appoint_time"   placeholder="请输入出版日期，形如2019-01-01">
                </div>

                <input type="submit" value="添加" class="btn btn-success btn-sm" class="text-left">
                <script>
                    function mySubmit(flag){
                        return flag;
                    }
                    $("#addappointment").submit(function () {
                        if($("#name").val()==''||$("#author").val()==''||$("#publish").val()==''||$("#appoint_time").val()==''){
                            alert("请填入完整图书预约信息！");
                            return mySubmit(false);
                        }
                    })
                </script>
            </form>
        </div>
    </div>
    </div>
</body>
</html>
