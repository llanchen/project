<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<title></title>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="Css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="Css/bootstrap-responsive.css" />
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

@media ( max-width : 980px) {
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
	<form class="form-inline definewidth m20" action="selectmh" method="post">
		用户名称： <input type="text" name="username" id="username"
			class="abc input-default" placeholder="" value="">&nbsp;&nbsp;
		<button type="submit" class="btn btn-primary">查询</button>
	</form>
	<table class="table table-bordered table-hover definewidth m10">
		<thead>
			<tr>
				<th>用户ID</th>
				<th>用户名</th>
				<th>密码</th>
				<th>联系电话</th>
				<th>姓名</th>
				<th>注册时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="item">
				<tr>
					<td>${item.id}</td>
					<td>${item.username}</td>
					<td>${item.password}</td>
					<td>${item.telephone}</td>
					<td>${item.name}</td>
					<td>${item.regTime}</td>
					<td><button><a href="UpdateUser.jsp?id=${item.id}&username=${item.username}
					&password=${item.password}&telephone=${item.telephone}&name=${item.name}">编辑</a></button><button disabled="disabled"><a href="UserServlet?method=doDelete&UserId=${item.id}"  onclick="return false">删除</a></button> </td>
				</tr>
			</c:forEach>
		</tbody>
		<%-- <tbody>
			<c:forEach items="${listmh}" var="item">
				<tr>
					<td>${item.id}</td>
					<td>${item.username}</td>
					<td>${item.password}</td>
					<td>${item.telephone}</td>
					<td>${item.name}</td>
					<td>${item.regTime}</td>
					<td><button><a href="UpdateUser.jsp?id=${item.id}&username=${item.username}
					&password=${item.password}&telephone=${item.telephone}&name=${item.name}">编辑</a></button><button disabled="disabled"><a href="UserServlet?method=doDelete&UserId=${item.id}"  onclick="return false">删除</a></button> </td>
				</tr>
			</c:forEach>
		</tbody> --%>
		<tfoot>
			<tr>
				<td colspan="4" align="center">
					<!-- pi 相当于 《input name="name" vlues=""/> --> <a
					href="userPage?pi=1">首页</a> <a
					href="userPage?pi=${page.pageNum-1 }"
					onclick=" return ${page.pageNum-1>=1 }">上一页</a> <span>${page.pageNum}/${page.pages}</span>
					<a href="userPage?pi=${page.pageNum+1 }"
					onclick=" return ${page.pageNum+1<=page.pages}">下一页</a> <a
					href="userPage?pi=${page.pages}">尾页</a>
				</td>
			</tr>
		</tfoot>
	</table>
</body>
</html>