<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
<title>客户信息管理主页</title>
<!-- Bootstrap -->
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet">
<!-- HTML5 Shim 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
<!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果 -->

<!--[if lte IE 9]>
	<script src="${pageContext.request.contextPath}/js/respond.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/html5.js"></script>
  <![endif]-->
<script src="${pageContext.request.contextPath}/js/jquery-1.10.2.js"></script>
<script src="${pageContext.request.contextPath}/js/mydate.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<style>
	body {
		min-width: 800px;
		margin-top: 20px;
	}
</style>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid" style="margin-bottom: 25px;">
			<div class="col-md-2 col-md-offset-8">
				<label>当前用户： ${loginuser.uname }</label>
			</div>
			<div class="col-md-2">
				<a href="${pageContext.request.contextPath}/logout">退出</a>
			</div>
		</div>
		<div class="row-fluid" style="clear: both;">
			<div class="col-md-2">
				<aside>
				<p>
					<a href="${pageContext.request.contextPath}/custom/showCustom">客户信息管理</a>
				</p>
				<p>
					<a href="${pageContext.request.contextPath}/custom/addCustom">客户信息导入</a>
				</p>
				<c:if test="${loginuser.role < 2 }">
					<p>
						<a href="${pageContext.request.contextPath}/custom/outCustom">客户信息导出</a>
					</p>
				</c:if>
				<c:if test="${loginuser.role == 0 }">
					<p>
						<a href="${pageContext.request.contextPath}/user/showUser">用户信息管理</a>
					</p>
					<p>
						<a href="${pageContext.request.contextPath}/user/addUser">用户信息添加</a>
					</p>
					<p>
						<a href="${pageContext.request.contextPath}/loginRecord/showRecord">用户登录记录</a>
					</p>
				</c:if>
				<p>
					<a href="${pageContext.request.contextPath}/user/changePwd">用户密码修改</a>
				</p>
				</aside>
			</div>
			<div class="col-md-10">
				<br/><br/>
				<center><h2>欢迎进入客户信息管理系统</h2></center>
			</div>
		</div>
	</div>
</body>
</html>
