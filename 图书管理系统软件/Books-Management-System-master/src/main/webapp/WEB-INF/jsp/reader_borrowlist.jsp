<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>图书借阅榜</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-3.2.1.js"></script>
    <script src="js/bootstrap.min.js" ></script>
    <style>
        body{
            background-color: rgb(240,242,245);
        }
        li{
            float:left;
            display:inline-block
        }
        #divcss5{height:200px;width:180px;border:1.5px solid #F5F5F5}
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



<link href="/Content/list-style.css" rel="stylesheet"/>

    <div class="content2 clearfix">
          <!-- Nav tabs -->
          <ul class="nav nav-tabs nav-tabs-dcontent" role="tablist">
            <li role="presentation" class="active"><a href="/reader_borrowlist.html">图书推荐榜</a></li>
          </ul>
            <!-- Tab panes -->
        <div class="tab-content news-tab">
            <div class="pop_list">
                    <ul>
                        <c:forEach items="${topbooks}" var="book">
                            <li>
                                <div  id="divcss5">
                                    <img width="140" height="150" src="showPic/${book.pic}" alt="bookdetail.html?bookId=<c:out value="${book.bookId}"></c:out>"/>

                                    <a href="bookdetail.html?bookId=<c:out value="${book.bookId}"></c:out>">
                                        <p><c:out value="${book.name}"></c:out></p>
                                    </a>
                                    <i class="icon icon-player1"></i>

                                    <td>
                                        <a href="lendbook.html?bookId=${book.bookId}">
                                            <button type="button" class="btn btn-success btn-xs">借阅</button>
                                        </a>
                                    </td>

                                    <td>
                                        <a href="myshelf_add.html?bookId=<c:out value="${book.bookId}"></c:out>">
                                            <button type="button" class="btn btn-success btn-xs">放入书架</button>
                                        </a>
                                    </td>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
            </div>

        </div>
    </div>

</body>
</html>
