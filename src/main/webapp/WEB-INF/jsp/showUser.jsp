<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<head>
<title>用户信息管理-列表/编辑页面</title>
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
	.table th, .table td {
		font-size: 1em;
		text-align: center;
		height: 1.1em;
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
				<form id="form_user" class="col-md-8" 
					action="${pageContext.request.contextPath}/user/addUser"
					method="post">
					<fieldset align="center">
						<legend>管理按钮</legend>
						<button class="btn btn-default"
							onclick="addUser('${pageContext.request.contextPath}/user/addUser');">新增用户</button>
						<button class="btn btn-default"
							onclick="doUser('${pageContext.request.contextPath}/user/delUser.do');">删除用户</button>
					</fieldset>
					<br />
					<fieldset align="center">
						<legend>用户列表</legend>
						<table class="table table-bordered table-condensed table-striped table-hover">
							<thead>
								<tr align="center">
									<th><input type="checkbox" id="selectAll" value="true" />全选</th>
									<th>用户编号</th>
									<th>用户名</th>
									<th>用户类型</th>
									<th>最后更新时间</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${users}" var="user">
									<tr>
										<td>
										<c:if test="${user.role != 0 }">
											<input type="checkbox" name="selectuid"
												value="${user.uid }" />
											</c:if>
										</td>
										<td>${user.uid }</td>
										<td>${user.uname }</td>
										<c:choose>
											<c:when test="${user.role==0 }">
												<td>超级管理员</td>
											</c:when>
											<c:when test="${user.role==1 }">
												<td>一级管理员</td>
											</c:when>
											<c:when test="${user.role==2 }">
												<td>二级管理员</td>
											</c:when>
										</c:choose>
										<td>${user.lasttime }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</fieldset>
				</form>
				<form id="form_user_update" class="form-horizontal col-md-4" 
					action="${pageContext.request.contextPath}/user/updateUser.do"
					method="post">
					<fieldset align="center">
						<legend>用户信息编辑</legend>
						<div class="form-group">
							<label class="control-label col-sm-3"
								for="username">用户名</label>
							<div class="controls col-sm-9">
								<input type="hidden" />
								<input type="password" style="display:none;"/>
								<input type="hidden" id="userid" name="userid" value="" />
								<input type="hidden" id="uname" value="">
								<input type="text" id="username" class="col-sm-8" 
									name="username" placeholder="用户名,为空则用户名不变" 
									value="" required autocomplete="off"/>
								<font id="showno" style="display: none; color: red;">该名已存在,不可用</font>
								<font id="showyes" style="display: none; color: green;">该名不存在，可用</font>
							</div>
						</div>
						<div class="form-group clear">
							<label class="control-label col-sm-3" for="pwd1">密码</label>
							<div class="controls col-sm-9">
								<input type="password" id="pwd1" name="userpwd" class="col-sm-8" value=""  
									placeholder="密码,不输入则密码不变" autocomplete="off" />
							</div>
						</div>
						<div class="form-group clear">
							<label class="control-label col-sm-3" for="pwd2">确认密码</label>
							<div class="controls col-sm-9">
								<input type="password" id="pwd2" class="col-sm-8" 
									placeholder="确认密码" />
							</div>
						</div>
						<div class="form-group clear">
							<label class="control-label col-sm-3" for="role">用户类型</label>
							<div class="controls col-sm-9">
								<select name="role" id="role" class="col-sm-8">
									<option value="1" selected="selected">一级管理员</option>
									<option value="2">二级管理员</option>
								</select>
							</div>
						</div>
						<div class="form-group clear">
							<button class="btn btn-primary col-sm-2 col-sm-offset-3" type="submit" id="btn_submit">修改</button>
							<button class="btn btn-default col-sm-2" type="reset" id="btn_reset">重置</button>
						</div>
					</fieldset>
				</form>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		$(document).ready(function() {
			$("#selectAll").click(function() {
				var isSelectAll = $(this).prop("checked");
				if (isSelectAll) {
					$("input[name^='selectuid']").prop("checked", true);
				} else {
					$("input[name^='selectuid']").prop("checked", false);
				}
			});
			
			$(".table tbody tr").mouseover(function (){
				$(this).css("cursor", "pointer");
			});
			$(".table tbody tr").click(function (){
				
				var uid = $(this).find("td").eq(1).html();
				if(uid != "1"){
					$.post("getUser.do", {userid: uid} , function (data){
						var json = $.parseJSON(data);
						$("#form_user_update #userid").val(json.uid);
						$("#form_user_update #uname").val(json.uname);
						$("#form_user_update #pwd1").val("");
						$("#form_user_update #username").val(json.uname);
						$("#form_user_update #role").val(json.role);
					});
				}
			});
			
			$("#form_user_update #username").blur(function (){
				var userid = $("#form_user_update #userid").val();
				if(userid != ""){
					var uname = $("#form_user_update #username").val();
					var hidename = $("#form_user_update #uname").val();
					if(uname != hidename && uname != ""){
						$.post("checkUser.do", {username: uname }, function (text) {
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
								$("#form_user_update #btn_submit").prop("disabled", true);
							} else if(text.indexOf("false") >= 0){
								$("#showno").hide();
								$("#showyes").show();
								$("#form_user_update #btn_submit").prop("disabled", false);
							}
						});
					} else {
						$("#showno").hide();
						$("#showyes").hide();
						$("#form_user_update #btn_submit").prop("disabled", false);
					}
				}
			});
		});

		function addUser(action) {
			var form_user = $("#form_user")[0];
			form_user.action = action;
			form_user.submit();
		}

		function doUser(action) {
			var isSelected = false;
			$("input[name^='selectuid']").each(function(i) {
				var isCheck = $(this).prop("checked");
				if (isCheck) {
					isSelected = true;
					if (action.indexOf("delUser.do") > 0) {
						if (confirm("你确定要删除吗？")) {
							var form_user = $("#form_user")[0];
							form_user.action = action;
							form_user.submit();
							return false;
						}
					} else {
						var form_user = $("#form_user")[0];
						form_user.action = action;
						form_user.submit();
						return false;
					}
				}
			});
			if (!isSelected) {
				alert("必须选中一条!");
			}
		}
	</script>
</body>
</html>
