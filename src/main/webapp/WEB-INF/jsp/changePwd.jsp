<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> 
<html>
<head>
<title>修改密码页面</title>
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
		<div class="row-fluid" style="margin-bottom: 25px;">
			<div class="col-sm-2 col-sm-offset-8">
				<label>当前用户： ${loginuser.uname }</label>
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
				<form id="form_user" class="form-horizontal col-sm-5 col-sm-offset-3" 
					 method="post">
					<fieldset align="center">
						<legend>用户密码修改</legend>
						<div class="form-group">
							<label class="control-label col-sm-3"
								for="oldpwd">旧密码</label>
							<div class="controls col-sm-9">
								<input type="hidden" />
								<input type="password" style="display:none;"/>
								<input type="hidden" id="userid" name="userid" value="${loginuser.uid }" />
								<input type="hidden" id="username" name="username" value="${loginuser.uname }" />
								<input type="password" id="oldpwd" name="oldpwd" class="col-sm-8" 
									placeholder="旧密码" required/>
							</div>
						</div>
						<div class="form-group clear">
							<label class="control-label col-sm-3" for="pwd1">新密码</label>
							<div class="controls col-sm-9">
								<input type="password" id="pwd1" name="newpwd" class="col-sm-8" 
									placeholder="新密码" required/>
							</div>
						</div>
						<div class="form-group clear">
							<label class="control-label col-sm-3" for="pwd2">确认密码</label>
							<div class="controls col-sm-9">
								<input type="password" id="pwd2" class="col-sm-8" 
									placeholder="确认密码" required/>
							</div>
						</div>
						<div class="form-group clear">
							<button class="btn btn-primary col-sm-2 col-sm-offset-3" type="button" id="btn_submit">修改</button>
							<button class="btn btn-default col-sm-2" type="reset" id="btn_reset">重置</button>
						</div>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function (){
			$("#btn_submit").click(function (){
				var uid = $("#userid").val();
				var uname = $("#username").val();
				var oldPwd = $("#oldpwd").val();
				var newPwd = $("#pwd1").val();
				var pwd2 = $("#pwd2").val();
				if(oldPwd == ""){
					alert("请输入旧密码！");
					$("#oldpwd").focus();
					return false;
				}else if(newPwd == ""){
					alert("请输入新密码！");
					$("#pwd1").focus();
					return false;
				} else if(newPwd != pwd2){
					alert("密码和确认密码不一致，请重新输入!");
					$("#pwd1").val("");
					$("#pwd2").val("");
					$("#pwd1").focus();
					return false;
				}
				
				$.post("changePwd.do", {userid: uid, username: uname, oldpwd: oldPwd, newpwd: newPwd}, function (data) {
					alert(data);
					if(data.indexOf("true") >= 0){
						alert("修改密码成功，请重新登录！");
						window.location.href = "../login";
					} else 
					if (data.indexOf("false") >= 0){
						alert("原密码错误，请重输！");
					} else 
					if (data.indexOf("nologin") >= 0){
						window.location.href = "../login";
					} else 
					if (data.indexOf("500") >= 0){
						window.location.href = "../500";
					}
				});
			});
		});
	</script>
</body>
</html>
