<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>阳阳商城管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
<link href="assets/css/bui-min.css" rel="stylesheet" type="text/css" />
<link href="assets/css/main-min.css" rel="stylesheet" type="text/css" />
<script src="http://pv.sohu.com/cityjson?ie=utf-8"></script>
<script>
	function show() {
		var date = new Date(); //日期对象
		var now = "";
		now = date.getFullYear() + "年"; //读英文就行了
		now = now + (date.getMonth() + 1) + "月"; //取月的时候取的是当前月-1如果想取当前月+1就可以了
		now = now + date.getDate() + "日";
		now = now + date.getHours() + "时";
		now = now + date.getMinutes() + "分";
		now = now + date.getSeconds() + "秒";
		document.getElementById("nowDiv").innerHTML = now; //div的html是now这个字符串
		setTimeout("show()", 1000); //设置过1000毫秒就是1秒，调用show方法
	}
</script>
</head>
<body onload="show()">
	<div class="header">

		<div class="dl-title">
			<h1>阳阳商城管理系统</h1>
		</div>
		<div id="nowDiv" style="position: relative;left: 730px;top: 8px;color: white;"></div>
		<div class="dl-log" style="position: relative;top: -20px;">
			登录IP：
			<script type="text/javascript">
				document.write(returnCitySN["cip"] + ','
						+ returnCitySN["cname"])
			</script>
			&nbsp;&nbsp;&nbsp; 欢迎您，<span class="dl-log-user">超级管理员</span><a
				href="login.jsp" title="退出系统" class="dl-log-quit">[安全退出]</a>
		</div>
	</div>
	<div class="content">
		<div class="dl-main-nav">
			<div class="dl-inform">
				<div class="dl-inform-title">
					<s class="dl-inform-icon dl-up"></s>
				</div>
			</div>
			<ul id="J_Nav" class="nav-list ks-clear">
				<li class="nav-item dl-selected"><div
						class="nav-item-inner nav-home">系统管理</div></li>
				<li class="nav-item dl-selected"><div
						class="nav-item-inner nav-order">业务管理</div></li>

			</ul>
		</div>
		<ul id="J_NavContent" class="dl-tab-conten">

		</ul>
	</div>
	<script type="text/javascript" src="assets/js/jquery-1.8.1.min.js"></script>
	<script type="text/javascript" src="assets/js/bui-min.js"></script>
	<script type="text/javascript" src="assets/js/common/main-min.js"></script>
	<script type="text/javascript" src="assets/js/config-min.js"></script>
	<script>
		BUI.use('common/main', function() {
			var config = [ {
				id : '1',
				homePage : '2',
				menu : [ {
					text : '系统管理',
					items : [ {
						id : '1',
						text : '用户管理',
						href : 'userPage'
					}, {
						id : '2',
						text : '管理员管理',
						href : 'findAllAdminsServlet'
					} ]
				} ]
			}, {
				id : '2',
				homePage : '1',
				menu : [ {
					text : '业务管理',
					items : [ {
						id : '1',
						text : '类型管理',
						href : 'catePage'
					}, {
						id : '2',
						text : '商品管理',
						href : 'proPage'
					}, {
						id : '3',
						text : '订单管理',
						href : 'msoPage'
					} ]
				} ]
			} ];
			new PageUtil.MainPage({
				modulesConfig : config
			});
		});
	</script>
</body>
</html>
