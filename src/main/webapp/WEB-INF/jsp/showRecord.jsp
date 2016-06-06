<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>登陆记录页面</title>
<!-- Bootstrap -->
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet">
<!-- HTML5 Shim 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
<!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果 -->

<!--[if lte IE 9]>
	<script src="${pageContext.request.contextPath}/js/respond.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/html5.js"></script>
  <![endif]-->
<link href="${pageContext.request.contextPath}/css/page.css"
	rel="stylesheet">
<script src="${pageContext.request.contextPath}/js/jquery-1.10.2.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<style>
	body {
		min-width: 800px;
		margin-top: 20px;
	}
	.table th, .table td {
		font-size: 0.8em;
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
						<a href="${pageContext.request.contextPath}/record/showRecord">用户登录记录</a>
					</p>
				</c:if>
				<p>
					<a href="${pageContext.request.contextPath}/user/changePwd">用户密码修改</a>
				</p>
				</aside>
			</div>
			<div class="col-md-10">
				<form id="form_record" role="form" 
					action="${pageContext.request.contextPath}/record/showRecord" 
					method="post">
					<fieldset align="center">
						<legend>用戶名模糊搜索</legend>
						 	<label>用戶名</label>
						    <input type="text" id="username" name="username" 
						         placeholder="用戶名" required value="${username }" />
						    <input type="hidden" id="userid" name="userid" 
						    	value="${loginuser.uid }" />
						    <input type="hidden" id="currentPage" name="currentPage" 
						    	value="${page.currentPage }" />
						    <input type="hidden" id="basePath" 
						    	value="${pageContext.request.contextPath }" />
						    <button id="btn_search" type="button" style="margin-left: 20px;" class="btn btn-default">搜索</button>
						    <button id="btn_delete" type="button" style="margin-left: 20px;" class="btn btn-default">删除选中</button>
					</fieldset>
					<fieldset align="center">
						<legend>用户登录记录列表</legend>
						<table class="table table-bordered table-condensed table-striped table-hover">
							<thead>
								<tr align="center">
									<th><input type="checkbox" id="selectAll" value="true" />全选</th>
									<th>用户名</th>
									<th>用户类型</th>
									<th>登录IP</th>
									<th>登录时间</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${records}" var="record">
									<tr>
										<td>
											<input type="checkbox" name="recordids"
												value="${record.recordid }" />
										</td>
										<td>${record.user.uname }</td>
										<c:choose>
											<c:when test="${record.user.role==0 }">
												<td>超级管理员</td>
											</c:when>
											<c:when test="${record.user.role==1 }">
												<td>一级管理员</td>
											</c:when>
											<c:when test="${record.user.role==2 }">
												<td>二级管理员</td>
											</c:when>
										</c:choose>
										<td>${record.ipaddress }</td>
										<td><fmt:formatDate value="${record.logintime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</fieldset>
					<div class='page fix'>
						共 <b>${page.totalCount}</b> 条
						<c:if test="${page.currentPage != 1}">
							<a href="javascript:changeCurrentPage('1')" class='first'>首页</a>
							<a href="javascript:changeCurrentPage('${page.currentPage-1}')" class='pre'>上一页</a>
						</c:if>
						当前第<span>${page.currentPage}/${page.totalPage}</span>页
						<c:if test="${page.currentPage != page.totalPage}">
							<a href="javascript:changeCurrentPage('${page.currentPage+1}')" class='next'>下一页</a>
							<a href="javascript:changeCurrentPage('${page.totalPage}')" class='last'>末页</a>
						</c:if>
						跳至&nbsp;<input id="currentPageText" type='text' value='${page.currentPage}' class='allInput w28' />&nbsp;页&nbsp;
						<a href="javascript:changeCurrentPage($('#currentPageText').val())" class='go'>GO</a>
					</div>
				</form>
			</div>
		</div>
	</div>	
	<script type="text/javascript">
		$(document).ready(function (){
			$("#selectAll").click(function (){
				var isSelectAll = $(this).prop("checked");
				if(isSelectAll){
					$("input[name^='recordids']").prop("checked", true);
				} else {
					$("input[name^='recordids']").prop("checked", false);
				}
			});
			
			$("#btn_search").click(function(){
				var basePath = $("#basePath").val();
				$("#form_record").attr("action", basePath + "/record/showRecord");
				$("#form_record").submit();
			});
			
			$("#btn_delete").click(function(){
				var isSelected = false;
				$("input[name^='recordids']").each(function(i){
					if($(this).prop("checked")){
						isSelected = true;
						return false;
					}
				});
				if(!isSelected){
					alert("至少选中一个！");
					return false;
				}
				if(confirm("确定要删除吗？")){
					var basePath = $("#basePath").val();
					$("#form_record").attr("action", basePath + "/record/delRecord.do");
					$("#form_record").submit();
				}
			});
		});
		
		
		function changeCurrentPage(currentPage){
			$("#currentPage").val(currentPage);
			var basePath = $("#basePath").val();
			$("#form_record").attr("action", basePath + "/record/showRecord");
			$("#form_record").submit();
		}
	</script>
</body>
</html>
