<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>图书信息添加</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-3.2.1.js"></script>
    <script src="js/bootstrap.min.js" ></script>
    <base href="<%=request.getContextPath()%>/"/>
    <style>
        body{
            background-color: rgb(240,242,245);
        }
    </style>
    <meta http-equiv="Content-Type" content="multipart/form-data; charset=utf-8" />
</head>
<body>
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

<div style="position: relative;top: 10%;width: 80%;margin-left: 10%">
            <form action="book_add_do.html" method="post" id="addbook" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="name">图书名</label>
                    <input type="text" class="form-control" name="name" id="name" placeholder="请输入书名">
                </div>
                <div class="form-group">
                    <label for="name">图书图片</label>
                    <input type="file" name="imgFile" id="imgFile"/>
                </div>
                <div class="form-group">
                    <label for="author">作者</label>
                    <input type="text" class="form-control" name="author" id="author"  placeholder="请输入作者名">
                </div>

                <div class="form-group">
                    <label for="stock">库存</label>
                    <input type="text" class="form-control"  name="stock" id="stock" placeholder="请输入图书数量">
                </div>

                <div class="form-group">
                    <label for="publish">出版社</label>
                    <input type="text" class="form-control"  name="publish" id="publish"  placeholder="请输入出版社">
                </div>
                <div class="form-group">
                    <label for="isbn">ISBN</label>
                    <input type="text" class="form-control" name="isbn" id="isbn"  placeholder="请输入ISBN">
                </div>
                <div class="form-group">
                    <label for="introduction">简介</label>
                    <textarea class="form-control" rows="3"  name="introduction" id="introduction" placeholder="请输入简介"></textarea>
                </div>
                <div class="form-group">
                    <label for="language">语言</label>
                    <input type="text" class="form-control" name="language" id="language"  placeholder="请输入语言">
                </div>
                <div class="form-group">
                    <label for="price">价格</label>
                    <input type="text" class="form-control"  name="price"  id="price" placeholder="请输入价格">
                </div>
                <div class="form-group">
                    <label for="pubdate">出版日期</label>
                    <input type="text" class="form-control"  name="pubdate" id="pubdate"   placeholder="请输入出版日期，形如2019-01-01">
                </div>
                <div class="form-group">
                    <label for="classId">分类号</label>
                    <input type="text" class="form-control" name="classId" id="classId"  placeholder="请输入分类号">
                </div>

                <input type="submit" value="添加" class="btn btn-success btn-sm" class="text-left">
                <script>
                    function mySubmit(flag){
                        return flag;
                    }
                    $("#addbook").submit(function () {
                        if($("#name").val()==''||$("#author").val()==''||$("#publish").val()==''||$("#isbn").val()==''||$("#introduction").val()==''||$("#language").val()==''||$("#price").val()==''||$("#pubdate").val()==''||$("#classId").val()==''||$("#pressmark").val()==''||$("#state").val()==''){
                            alert("请填入完整图书信息！");
                            return mySubmit(false);
                        }
                    })
                </script>
            </form>

</div>



</body>
</html>
