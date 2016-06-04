<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<head>
<title>用户管理页面-新增用户</title>
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
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<style>
	body {
		min-width: 800px;
		margin-top: 20px;
	}
	.clear {
		clear: both;
	}
	a {
		font-size: 0.9em;
	}
</style>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="col-sm-2 col-sm-offset-8">
				<label>当前用户：${loginuser.uname }</label>
			</div>
			<div class="col-sm-2">
				<a href="${pageContext.request.contextPath}/logout">退出</a>
			</div>
		</div>
		<div class="row-fluid" style="clear: both;">
			<div class="col-sm-1">
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
			<div class="col-sm-11">
				<form id="form_user" class="form-horizontal"
					action="${pageContext.request.contextPath}/user/addUser.do"
					method="post" onsubmit="return check()">
					<fieldset align="center">
						<legend>新增用户</legend>
						<div class="form-group">
							<label class="control-label col-sm-2"
								for="username">用户名</label>
							<div class="controls col-sm-6">
								<input type="hidden" />
								<input type="password" style="display:none;"/>
								<input type="text" id="username" class="col-sm-6" 
									name="username" placeholder="用户名" required autocomplete="off" /> <font id="showno"
									style="display: none; color: red;">该名已存在,不可用</font> <font
									id="showyes" style="display: none; color: green;">该名不存在，可用</font>
							</div>
						</div>
						<div class="form-group clear">
							<label class="control-label col-sm-2" for="pwd1">密码</label>
							<div class="controls col-sm-6">
								<input type="password" id="pwd1" name="upwd" class="col-sm-6" 
									placeholder="密码" required autocomplete="off"/>
							</div>
						</div>
						<div class="form-group clear">
							<label class="control-label col-sm-2" for="pwd2">确认密码</label>
							<div class="controls col-sm-6">
								<input type="password" id="pwd2" class="col-sm-6" 
									placeholder="确认密码" required />
							</div>
						</div>
						<div class="form-group clear">
							<label class="control-label col-sm-2" for="role">用户类型</label>
							<div class="controls col-sm-6">
								<select name="role" id="role" class="col-sm-6">
									<option value="1" selected="selected">一级管理员</option>
									<option value="2">二级管理员</option>
								</select>
							</div>
						</div>
						<div class="form-group clear">
							<button class="btn btn-primary col-sm-1 col-sm-offset-2" type="submit" id="btn_submit">确认新增</button>
							<button class="btn btn-default col-sm-1" type="reset" id="btn_reset">重置</button>
						</div>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		$(document).ready(function (){
			$("#username").blur(function (){
				var uname = $("#username").val();
				var name = $("#uname").val();
				if(uname != name || uname != ""){
					$.post("checkUser.do", { username: uname }, function (text) {
						if(text.indexOf("nologin") >= 0){
							$("#showno").hide();
							$("#showyes").hide();
							alert("你未登录或登录已过期，请登录！");
							window.location.href = "../login";
						} else
						if(text.indexOf("405") >= 0){
							$("#showno").hide();
							$("#showyes").hide();
							alert("你未登录或登录已过期，请登录！");
							window.location.href = "../405";
						} else
						if(text.indexOf("true") >= 0){
							$("#showno").show();
							$("#showyes").hide();
							$("#btn_submit").prop("disabled", true);
						} else if(text.indexOf("false") >= 0){
							$("#showno").hide();
							$("#showyes").show();
							$("#btn_submit").prop("disabled", false);
						}
					});
				} else {
					$("#showno").hide();
					$("#showyes").hide();
					$("#btn_submit").prop("disabled", false);
				}
			});
		});
		
		function check(){
			var pwd1 = $("#pwd1").val();
			var pwd2 = $("#pwd2").val();
			if(pwd1 == pwd2){
				return true;
			}else {
				alert("密码和确认密码不一致，请重新输入!");
				$("#pwd1").val("");
				$("#pwd2").val("");
				return false;
			}
		}
	</script>
</body>
</html>