<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="Css/style.css" />
    <script type="text/javascript" src="Js/jquery.js"></script>
    <script type="text/javascript" src="Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="Js/bootstrap.js"></script>
    <script type="text/javascript" src="Js/ckform.js"></script>
    <script type="text/javascript" src="Js/common.js"></script>

    <style type="text/css">
        body {
            padding-bottom: 40px;
        }
        .sidebar-nav {
            padding: 9px 0;
        }

        @media (max-width: 980px) {
            /* Enable use of floated navbar text */
            .navbar-text.pull-right {
                float: none;
                padding-left: 5px;
                padding-right: 5px;
            }
        }


    </style>
</head>
<body>
<form class="form-inline definewidth m20" action="#" method="post">
    类型名称：
    <input type="text" name="cName" id="cName"class="abc input-default" placeholder="" value="">&nbsp;&nbsp;
    <button type="submit" class="btn btn-primary">查询</button>&nbsp;&nbsp; <button type="button" class="btn btn-success" id="addnew">新增类型</button>
</form>
<table class="table table-bordered table-hover definewidth m10">
    <thead>
    <tr>
        <th>编号</th>
        <th>商品类型</th>
        <th>操作</th>
    </tr>
    </thead>
	     <tbody>
	<c:forEach items="${cates}" var="item">
	<tr>
	<td>${item.id}</td>
	<td>${item.cName}</td>
	<td>
    	<a href="">编辑</a>
    	<a href="delcate?CateId=${item.id}">删除</a>
    </td>
	</tr>
	</c:forEach>
	</tbody>
	<%-- <tfoot>
	<tr>
				<td colspan="4" align="center">
					<!-- pi 相当于 《input name="name" vlues=""/> --> <a
					href="CateServlet?method=doPost&pi=1">首页</a>
					<a href="CateServlet?method=doPost&pi=${page.pageIndex-1 }"
					onclick=" return ${page.pageIndex-1>=1 }">上一页</a>
					<span>${page.pageIndex}/${page.countPage}</span>
					<a href="CateServlet?method=doPost&pi=${page.pageIndex+1 }"
					onclick=" return ${page.pageIndex+1<=page.countPage}">下一页</a> <a
					href="CateServlet?method=doPost&pi=${page.countPage}">尾页</a>
				</td>
			</tr>
	</tfoot> --%>
</table>
</body>
</html>
<script>
    $(function () {


		$('#addnew').click(function(){

				window.location.href="AddCate.jsp";
		 });


    });

	function del(id)
	{


		if(confirm("确定要删除吗？"))
		{

			var url = "#";

			window.location.href=url;

		}




	}
</script>
