<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>客户信息管理-列表/编辑页面</title>
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
<script src="${pageContext.request.contextPath}/js/mydate.js"></script>
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
	.clear {
		clear: both;
	}
	.border {
		border-bottom: 2 solid #555;
	}
	a {
		font-size: 0.9em;
	}
	#form_custom_update .form-group {
		margin-bottom: 0;
	}
	#form_custom_update label {
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
						<a href="${pageContext.request.contextPath}/record/showRecord">用户登录记录</a>
					</p>
				</c:if>
				<p>
					<a href="${pageContext.request.contextPath}/user/changePwd">用户密码修改</a>
				</p>
				</aside>
			</div>
			<div class="col-sm-11">
				<div class="col-sm-8" >
					<form id="form_custom" role="form" 
						action="${pageContext.request.contextPath}/custom/showCustom" method="post">
						<fieldset align="center">
							<legend>模糊条件搜索</legend>
						      	<label>车牌号</label>
						      	<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage }"/>
						      	<input type="hidden" id="basePath" value="${pageContext.request.contextPath }/"/>
						      	<input type="text" id="carCode" name="carCode" 
						         	placeholder="车牌号"  value="${carCode }" />
						      	<label style="margin-left: 20px;">车架号</label>
						      	<input type="text" id="carFrameCode" name="carFrameCode" 
						      		placeholder="车架号"  value="${carFrameCode }" />
						      	<button id="btn_search" style="margin-left: 20px;" class="btn btn-default">搜索</button>
						      	<c:if test="${loginuser.role < 2 }">
						      		<button id="btn_delete" style="margin-left: 20px;" class="btn btn-default">删除选中</button>
						      	</c:if>
						</fieldset>
						<fieldset align="center">
							<legend>客户列表</legend>
							<table class="table table-bordered table-condensed table-striped table-hover">
								<thead>
									<tr align="center">
										<th><input type="checkbox" id="selectAll" />全选</th>
										<th>车牌号</th>
										<th>联系电话</th>
										<th>车架号</th>
										<th>保单号</th>
										<th>终保时间</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${customs}" var="custom">
										<tr>
											<td><input type="checkbox" name="customids" id="selectCustom" value="${custom.customid }" /></td>
											<td>${custom.licenseplates }</td>
											<td>${custom.phonenum }</td>
											<td>${custom.carframecode }</td>
											<td>${custom.insurancecode }</td>
											<td><fmt:formatDate value="${custom.endtime }" pattern="yyyy/MM/dd"/></td>
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
				<div class="col-sm-4" >
					<form id="form_custom_update" class="form-horizontal" 
						method="post">
						<fieldset align="center">
							<legend>客户信息编辑</legend>
							<div class="form-group ">
								<label class="control-label col-sm-3"
									for="licenseplates">车牌号</label>
								<div class="controls col-sm-9">
									<input type="hidden" id="customid" name="customid" value="" />
									<input type="text" id="licenseplates" class="col-sm-8" 
										name="licenseplates" required />
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-3"
									for="idcard">身份证号</label>
								<div class="controls col-sm-9">
									<input type="text" id="idcard" class="col-sm-8" 
										name="idcard" />
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-3"
									for="agencycode">机构代码</label>
								<div class="controls col-sm-9">
									<input type="text" id="agencycode" class="col-sm-8" 
										name="agencycode" />
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-3"
									for="phonenum">联系电话</label>
								<div class="controls col-sm-9">
									<input type="text" id="phonenum" class="col-sm-8" 
										name="phonenum"/>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-3"
									for="carowner">车主</label>
								<div class="controls col-sm-9">
									<input type="text" id="carowner" class="col-sm-8" 
										name="carowner" />
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-3"
									for="insurer">投保人</label>
								<div class="controls col-sm-9">
									<input type="text" id="insurer" class="col-sm-8" 
										name="insurer" />
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-3"
									for="insured">被保险人</label>
								<div class="controls col-sm-9">
									<input type="text" id="insured" class="col-sm-8" 
										name="insured" />
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-3"
									for="carmodel">车辆型号</label>
								<div class="controls col-sm-9">
									<input type="text" id="carmodel" class="col-sm-8" 
										name="carmodel" />
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-3"
									for="carframecode">车架号码</label>
								<div class="controls col-sm-9">
									<input type="text" id="carframecode" class="col-sm-8" 
										name="carframecode" required />
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-3"
									for="enginecode">发动机号</label>
								<div class="controls col-sm-9">
									<input type="text" id="enginecode" class="col-sm-8" 
										name="enginecode" />
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-3"
									for="firsttime">初登日期</label>
								<div class="controls col-sm-9">
									<input type="text" id="firsttime" class="col-sm-8" 
										onfocus="MyCalendar.SetDate(this)" 
										name="firsttime" />
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-3"
									for="starttime">始保日期</label>
								<div class="controls col-sm-9">
									<input type="text" id="starttime" class="col-sm-8" 
										onfocus="MyCalendar.SetDate(this)" 
										name="starttime" value=""/>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-3"
									for="endtime">终保日期</label>
								<div class="controls col-sm-9">
									<input type="text" id="endtime" class="col-sm-8" 
										onfocus="MyCalendar.SetDate(this)"  
										name="endtime"  />
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-3"
									for="insurance">承保单位</label>
								<div class="controls col-sm-9">
									<input type="text" id="insurance" class="col-sm-8" 
										name="insurance" />
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-3"
									for="insurancecode">保单号</label>
								<div class="controls col-sm-9">
									<input type="text" id="insurancecode" class="col-sm-8" 
										name="insurancecode" />
								</div>
							</div>
							<div class="form-group">
								<label style="text-align: right;">
									<input type="checkbox" id="cardamage"  
										name="cardamage" value="√"/>车损
								</label>&nbsp;&nbsp;&nbsp;
								<label>
									<input type="checkbox" id="robbery" 
										name="robbery" value="√"/>盗抢
								</label>&nbsp;&nbsp;&nbsp;
								<label>
									<input type="checkbox" id="autoignition"  
										name="autoignition" value="√" />自燃
								</label>&nbsp;&nbsp;&nbsp;
								<label>
									<input type="checkbox" id="wading" 
										name="wading" value="√" />涉水
								</label>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-3">三者(万)</label>
								<label>
									<input type="radio" name="three" value="0" checked="checked"/>0
								</label>&nbsp;
								<label>
									<input type="radio" name="three" value="20" />20
								</label>&nbsp;
								<label>
									<input type="radio" name="three" value="30" />30
								</label>&nbsp;
								<label>
									<input type="radio" name="three" value="50" />50
								</label>&nbsp;
								<label>
									<input type="radio" name="three" value="100" />100
								</label>&nbsp;
								<label>
									<input type="radio" name="three" value="150" />150
								</label>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-3"
									for="driver">司机</label>
								<div class="controls col-sm-9">
									<input type="text" id="driver" class="col-sm-8" 
										name="driver" required />
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-3"
									for="passenger">乘客</label>
								<div class="controls col-sm-9">
									<input type="text" id="passenger" class="col-sm-8" 
										name="passenger" required />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3" style="text-align: right;">玻璃</label>
								<label class="col-sm-2 ">
									<input type="radio" name="glass" value="none" checked="checked"/>无
								</label>
								<label class="col-sm-3 ">
									<input type="radio" name="glass" value="foreign" />进口
								</label>
								<label class="col-sm-3" >
									<input type="radio" name="glass" value="domestic"/>国产
								</label>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-3">划痕</label>&nbsp;&nbsp;
								<label>
									<input type="radio" name="nick" value="0" checked="checked"/>0
								</label>&nbsp;&nbsp;
								<label>
									<input type="radio" name="nick" value="2000" />2000
								</label>&nbsp;&nbsp;
								<label>
									<input type="radio" name="nick" value="5000" />5000
								</label>&nbsp;&nbsp;
								<label>
									<input type="radio" name="nick" value="10000" />10000
								</label>&nbsp;&nbsp;
								<label>
									<input type="radio" name="nick" value="15000" />15000
								</label>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-1"
									for="remark">备注</label>
								<div class="controls col-sm-10">
									<textarea id="remark" 
										name="remark" cols="35" rows="3" wrap="physical" 
										placeholder="备注"></textarea>
								</div>
							</div>
							<div class="form-group clear">
								<button id="btn_custom_update" type="button" class="btn btn-primary col-sm-2 col-sm-offset-2" 
									onclick="updateCustom('${pageContext.request.contextPath}/custom/updateCustom.do');">修改</button>
								<button id="btn_custom_add" type="button" class="btn btn-info col-sm-2" 
									onclick="updateCustom('${pageContext.request.contextPath}/custom/addCustom.do');">新增</button>
								<button id="btn_custom_reset" type="reset" class="btn btn-default col-sm-2" >重置</button>
							</div>
						</fieldset>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	<script src="${pageContext.request.contextPath }/js/customList.js" type="text/javascript"></script>
</body>
</html>
