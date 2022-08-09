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
	<form class="form-inline definewidth m20" action="#" method="post">
		商品名称： <input type="text" name="pName" id="pName"
			class="abc input-default" placeholder="" value="">&nbsp;&nbsp;
		<button type="submit" class="btn btn-primary">查询</button>
		&nbsp;&nbsp;
		<button type="button" class="btn btn-success" id="addnew">新增商品</button>
	</form>
	<table class="table table-bordered table-hover definewidth m10">
		<thead>
			<tr>
				<th>编号</th>
				<th>名称</th>
				<th>货号</th>
				<th>数量</th>
				<th>市场价</th>
				<th>网站价</th>
				<th>商品图片</th>
				<th>上架时间</th>
				<!-- <th>是否上架</th> -->
				<th>月销量</th>
				<th>类型</th>
				<th>商品详情</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="item">
				<tr>
					<td>${item.id}</td>
					<td>${item.pName}</td>
					<td>${item.pSn}</td>
					<td>${item.pNum}</td>
					<td>${item.mPrice}</td>
					<td>${item.iPrice}</td>

					<%--//商品的图片与SeehopeShop项目共用，本项目没图片，需要引用SeehopeShop项目的，所以要将图片路径替换为SeehopeShop下的路径--%>
					<td><img class="preview-img" id="img" src="http://localhost:8080/shopping/${item.pImg}" style="width: 150px;height: 150px;"/></td>
					<td>${item.pubTime}</td>
					<%-- <td>${item.isShow}</td> --%>
					<td>${item.isHot}</td>
					<td>${item.cid}</td>
					<td>${item.xqImg}</td>
					<td><a
						href="toUpdatePro?id=${item.id}">编辑</a>
						<a href="deletePro?id=${item.id}">删除</a></td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="4" align="center">
					<!-- pi 相当于 《input name="name" vlues=""/> --> <a
					href="proPage?pi=1">首页</a> <a
					href="proPage?pi=${page.pageNum-1}"
					onclick=" return ${page.pageNum-1>=1}">上一页</a> <span>${page.pageNum}/${page.pages}</span>
					<a href="proPage?pi=${page.pageNum+1}"
					onclick=" return ${page.pageNum+1<=page.pages}">下一页</a> <a
					href="proPage?pi=${page.pages}">尾页</a>

				</td>
			</tr>
		</tfoot>
	</table>
</body>
</html>
<script>
	$(function() {

		$('#addnew').click(function() {

			window.location.href = "AddPro.jsp";
		});

	});

	function del(id) {

		if (confirm("确定要删除吗？")) {

			var url = "#";

			window.location.href = url;

		}

	}
</script>
