<%@ page import="com.book.domain.Book" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>全部图书信息</title>
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

<nav class="navbar navbar-default" role="navigation" style="background-color:#fff">
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


<div style="padding-left:500px;">
    <form  method="post" action="reader_querybook_do.html" class="navbar-form navbar-left"  id="searchform">
        <div class="form-group">
            <select id="searchClass" name="searchClass" class="form-control" >
                <option value="bookname">图书名</option>
                <option value="bookid">图书号</option>
                <option value="classname">分类</option>
            </select>
            <input id="searchWord" type="text"  name="searchWord" class="form-control" placeholder="输入检索词"/>
            <input type="submit" value="搜索" class="btn btn-success "  onclick="classChange()"/>
        </div>
    </form>

    <script>
        function mySubmit(flag){
            return flag;
        }
        $("#searchform").submit(function () {
            var val=$("#searchWord").val();
            if(val==''){
                alert("请输入关键字");
                return mySubmit(false);
            }
        })

        function classChange(){
            var searchClass=$("#searchClass").val();
            searchClass.selected='selected';
        }

    </script>
</div>

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

<c:if test="${!empty books}">
    <div class="panel panel-default" style="width: 90%;margin-left: 5%;margin-top: 7%">
        <div class="panel-heading">
            <h3 class="panel-title">
                查询结果：
            </h3>
        </div>
        <div class="panel-body">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>书号</th>
                    <th>书名</th>
                    <th>作者</th>
                    <th>出版社</th>
                    <th>ISBN</th>
                    <th>价格</th>
                    <th>库存</th>
                    <th>详情</th>
                    <th>借阅</th>
                    <th>放入书架</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${books}" var="book">
                    <tr>
                        <td><c:out value="${book.bookId}"></c:out></td>
                        <td><c:out value="${book.name}"></c:out></td>
                        <td><c:out value="${book.author}"></c:out></td>
                        <td><c:out value="${book.publish}"></c:out></td>
                        <td><c:out value="${book.isbn}"></c:out></td>
                        <td><c:out value="${book.price}"></c:out></td>
                        <td><c:out value="${book.stock}"></c:out></td>
                        <td>
                            <a href="bookdetail.html?bookId=<c:out value="${book.bookId}"></c:out>">
                                <button type="button" class="btn btn-success btn-xs">详情</button>
                            </a>
                        </td>

                        <td>
                            <a href="lendbook.html?bookId=${book.bookId}">
                                <button type="button" class="btn btn-success btn-xs">借阅</button>
                            </a>
                        </td>

                        <td>
                            <a href="myshelf_add.html?bookId=<c:out value="${book.bookId}"></c:out>
                                     <%--  &name=<c:out value="${book.name}"></c:out>
                                       &stock=<c:out value="${book.stock}"></c:out>
                                       &author=<c:out value="${book.author}"></c:out>
                                       &publish=<c:out value="${book.publish}"></c:out>
                                       &isbn=<c:out value="${book.isbn}"></c:out>
                                       &price=<c:out value="${book.price}"></c:out>--%>
                            ">
                                <button type="button" class="btn btn-success btn-xs">放入书架</button>
                            </a>
                        </td>

                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</c:if>


</body>
</html>
