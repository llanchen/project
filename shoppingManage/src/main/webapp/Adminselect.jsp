<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
	<form class="form-inline definewidth m20" action="#" method="post">
		管理员名称： <input type="text" name="adminname" id="adminname"
			class="abc input-default" placeholder="" value="">&nbsp;&nbsp;
		<button type="submit" class="btn btn-primary">查询</button>
		&nbsp;&nbsp;
		<button type="button" class="btn btn-success" id="addnew">新增用户</button>
	</form>
	<table class="table table-bordered table-hover definewidth m10">
		<thead>
			<tr>
				<th>管理员ID</th>
				<th>用户名</th>
				<th>密码</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${adminList}" var="item">
				<tr>
					<td>${item.adminid}</td>
					<td>${item.adminname}</td>
					<td>${item.adminpwd}</td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<%--<tr>
				<td colspan="4" align="center">
					<!-- pi 相当于 《input name="name" vlues=""/> --> <a
					href="adminServlet?method=select&pi=1">首页</a> <a
					href="adminServlet?method=select&pi=${page.pageIndex-1 }"
					onclick=" return ${page.pageIndex-1>=1 }">上一页</a> <span>${page.pageIndex}/${page.countPage}</span>
					<a href="adminServlet?method=select&pi=${page.pageIndex+1 }"
					onclick=" return ${page.pageIndex+1<=page.countPage}">下一页</a> <a
					href="adminServlet?method=select&pi=${page.countPage}">尾页</a>
				</td>
			</tr>--%>
		</tfoot>
	</table>
</body>
</html>
<script>
	$(function() {

		$('#addnew').click(function() {

			window.location.href = "AddAdmin.jsp";
		});
	});
</script>