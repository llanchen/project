<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
	<div class="form-inline definewidth m20" action="#" method="post">
		<div class="form-inline definewidth m20">
		订单号码： <input type="text" name="mso" id="mso"
			class="abc input-default" placeholder="" value="">&nbsp;&nbsp;
		<button type="submit" class="btn btn-primary">查询</button>
		&nbsp<button class="btn btn-primary" onclick="excel()">导出Excel</button>
		&nbsp;</div>
<%--	</form>--%>
	&nbsp;
	<table class="table table-bordered table-hover definewidth m10">
		<thead>
			<tr>
				<!-- <th>编号</th> -->
				<th>订单编号</th>
				<th>姓名</th>
				<th>电话</th>
				<th>下单日期</th>
				<th>订单状态</th>
				<th>订单金额</th>
				<th>收货地址</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${mpi.list}" var="item">
				<tr>
					<%-- <td>${item.id}</td> --%>
					<td>${item.msoid}</td>
					<td>${item.msoname}</td>
					<td>${item.telephone}</td>
					<td>${item.msoTime}</td>
					<td>${item.paystate}</td>
					<td>${item.msomoney}</td>
					<td>${item.address}</td>
					<td><a class="btn btn-success" href="msoxq?msoid=${item.msoid}">订单详情</a>&nbsp;&nbsp;&nbsp;
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="4" align="center">
					<a href="msoPage?pi=1">首页</a>
					<a href="msoPage?pi=${mpi.pageNum-1 }"
					onclick=" return ${mpi.pageNum-1>=1 }">上一页</a>
					<span>${mpi.pageNum}/${mpi.pages}</span>
					<a href="msoPage?pi=${mpi.pageNum+1 }"
					onclick=" return ${mpi.pageNum+1<=mpi.pages}">下一页</a> <a
					href="msoPage?pi=${mpi.pages}">尾页</a>
				</td>
			</tr>
		</tfoot>
	</table>
</body>
</html>
<script>

	function excel(){
		location.href="excelServlet";
		/*$.ajax({
			url:excelServlet,
			type:get,
			success:function(data){
				alert('导出成功！');
			}
		});*/
	}

	function del(id) {

		if (confirm("确定要删除吗？")) {

			var url = "#";

			window.location.href = url;

		}

	}
</script>
