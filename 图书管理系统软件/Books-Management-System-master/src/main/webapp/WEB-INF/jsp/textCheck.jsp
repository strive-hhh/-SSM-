<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>密码重置</title>
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
            <a class="navbar-brand " href="textCheck.html"><p class="text-primary">密码重置</p></a>
        </div>
    </div>
</nav>


<div style="position: relative;top: 10%;width: 80%;margin-left: 10%">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">短信验证密码重置</h3>
        </div>
        <div class="panel-body">
            <form action="#" method="post" id="modifyPassword">

                <div class="input-group">
                    <span class="input-group-addon">手机号</span>
                    <input type="text" class="form-control" name="phone" id="phone">
                </div>

                <div class="input-group">
                    <div class="form-group">
                        <span class="input-group-addon">验证码</span>
                        <input type="text" class="form-control" name="checkmessage" id="checkmessage" >
                        <input type="submit" value="发送" class="btn btn-success btn-sm" class="text-left">
                    </div>
                </div>

                <div class="input-group">
                    <span class="input-group-addon">新密码</span>
                    <input type="text" class="form-control"  name="newPassword" id="newPassword">
                </div>

                <input type="submit" value="提交" class="btn btn-success btn-sm" class="text-left">


                <script>
                    function mySubmit(flag){
                        return flag;
                    }
                    $("#modifyPassword").submit(function () {
                        if($("#phone").val()==''||$("#checkmessage").val()==''||$("#newPassword").val()==''){
                            alert("请填入完整密码重置信息！");
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
