<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>添加商品</title>
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
	<form action="addPro"  enctype="multipart/form-data" method="post"
		class="definewidth m20">
		<table class="table table-bordered table-hover definewidth m10">
			<tr>
				<td class="tableleft">商品名称</td>
				<td><input type="text" name="pName" /></td>
			</tr>
			<tr>
				<td class="tableleft">商品货号</td>
				<td><input type="text" name="pSn" /></td>
			</tr>
			<tr>
				<td class="tableleft">商品数量</td>
				<td><input type="text" name="pNum" /></td>
			</tr>
			<tr>
				<td class="tableleft">市场价</td>
				<td><input type="text" name="mPrice" /></td>
			</tr>
			<tr>
				<td class="tableleft">网站价</td>
				<td><input type="text" name="iPrice" /></td>
			</tr>
			<tr>
				<td class="tableleft">商品简介</td>
				<td><input type="file" name="pDesc" /></td>
			</tr>
			<tr>
				<td class="tableleft">商品图片</td>
				<td><input type="file" name="pImg" class="preview" data-preview="#img" /></td>
				<td class="tableleft">商品预览</td>
				<td><img class="preview-img" id="img" src="" style="width: 150px;height: 150px;"/></td>
			</tr>
			<tr>
				<td class="tableleft">商品上架时间</td>
				<td><input type="date" value="2018-05-24" name="pubTime" /></td>
			</tr>
			<tr>
				<td class="tableleft">商品是否上架</td>
				<td><input type="radio" value="1" name="isShow"
					checked="checked" />是 <input type="radio" value="0" name="isShow" />否</td>
			</tr>
			<tr>
				<td class="tableleft">商品销量</td>
				<td><input type="text" name="isHot" /></td>
			</tr>
			<tr>
				<td class="tableleft">商品类型</td>
				<td>
				<select name="cId">
				<c:forEach items="${cates}" var="item">
						<option value="${item.id}">${item.cName}</option>
				</c:forEach>
				</select> 
				</td>
			</tr>
			<tr>
				<td class="tableleft">商品详情图片</td>
				<td><input type="file" name="xqImg" class="preview" data-preview="#img1" /></td>
				<td class="tableleft">商品预览</td>
				<td><img class="preview-img" id="img1" src="" style="width: 150px;height: 150px;"/></td>
			</tr>
			<tr>
				<td class="tableleft"></td>
				<td>
					<button type="submit" class="btn btn-primary" type="button">保存</button>
					&nbsp;&nbsp;
					<button type="button" class="btn btn-success" name="backid"
						id="backid">返回列表</button>
				</td>
			</tr>
		</table>
	</form>
</body>
<script>
		//获取图片路劲的方法，兼容多种浏览器，通过createObjectURL实现
		function getObjectURL(file){
		  	if(window.createObjectURL != undefined){
				return window.createObjectURL(file);
		  	}
		  	
			if(window.URL != undefined){
		    	return window.URL.createObjectURL(file);
		  	}

			if(window.webkitURL != undefined){
		    	return window.webkitURL.createObjectURL(file);
		  	}
		 
		  	return null;
		}
		
		//实现功能代码
		$(function(){
			$(".preview").change(function(){
		    	var url = getObjectURL(this.files[0]);	// 获取路径
				var target = $(this).data("preview");	// 目标预览的元素
		    	if(url){
		    		// 为预览图赋值src属性
		      		$(target).attr("src", url);
		    	}
		  	});
		});
	</script>
</html>
