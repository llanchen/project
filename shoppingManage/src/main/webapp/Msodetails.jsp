<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>订单详情</title>
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
<script type="text/javascript">
	function deliver(obj, msoid) {

		$.ajax({
			url : 'updateDelivery',
			data : "msoid=" + msoid,
			success : function(data) {
				if(data>0){
					$(obj).parent().text("已发货");
				}
			}

		})

	}
</script>
</head>

<body>
	<div>
		<table class="table table-bordered table-hover definewidth m10">
			<thead>
				<tr>
					<th>订单编号</th>
					<th>订单金额</th>
					<th>付款状态</th>
					<th>物流状态</th>
				</tr>
			</thead>
			<tbody>

				<tr>

					<td>${mso.msoid }</td>
					<td>${mso.msomoney}</td>
					<td>${mso.paystate}</td>
					<td>${mso.deliveryState}
						<c:if
							test="${mso.deliveryState=='未发货' }">
							<input type="button" value="发货"
								onclick="deliver(this,'${mso.msoid }')" />
						</c:if>

					</td>
				</tr>

			</tbody>
		</table>
		<br />
	</div>

	<table class="table table-bordered table-hover definewidth m10">
		<thead>
			<tr>
				<th>商品名称</th>
				<th>购买数量</th>
				<th>商品价格</th>
				<th>商品预览</th>

			</tr>
		</thead>
		<tbody>
			<c:forEach items="${mso.msoxqs}" var="item">
				<tr>

					<td>${item.pro.pSn}</td>
					<td>${item.count}</td>
					<td>${item.pro.iPrice}</td>
					<td><img class="preview-img" id="img"
						src="http://localhost:8080/shopping/${item.pro.pImg}"
						style="width:150px;height:150px;"/></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
