<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>客户信息管理-导出页面</title>
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
<script src="${pageContext.request.contextPath }/js/mydate.js"></script>
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
	.clear {
		clear: both;
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
						method="post">
						<fieldset align="center">
							<legend>终保时间搜索</legend>
						      	<label>年份</label>
						      	<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage }"/>
						      	<input type="hidden" id="basePath" value="${pageContext.request.contextPath }"/>
						      	<input type="hidden" id="hiddenYear" value="${year }"/>
						      	<input type="hidden" id="hiddenMonth" value="${month }"/>
						      	<select id="year" name="year" onkeydown="Select.del(this,event)" onkeypress="Select.write(this,event)">
									<option value="">请选择年份</option>
									<option value="2016">2016年</option>
									<option value="2015">2015年</option>
									<option value="2014">2014年</option>
									<option value="2013">2013年</option>
									<option value="2012">2012年</option>
									<option value="2011">2011年</option>
									<option value="2010">2010年</option>
								</select>
						   		<label style="margin-left: 20px;">月份</label>
						   		<select id="month" name="month" onkeydown="Select.del(this,event)" onkeypress="Select.write(this,event)">
									<option value="">请选择月份</option>
									<option value="1">1月</option>
									<option value="2">2月</option>
									<option value="3">3月</option>
									<option value="4">4月</option>
									<option value="5">5月</option>
									<option value="6">6月</option>
									<option value="7">7月</option>
									<option value="8">8月</option>
									<option value="9">9月</option>
									<option value="10">10月</option>
									<option value="11">11月</option>
									<option value="12">12月</option>
								</select>
						   		<button id="btn_search" class="btn btn-default" style="margin-left: 20px;">搜索</button>
						   		<button id="btn_out" class="btn btn-default" style="margin-left: 20px;">导出选中</button>
						   		<button id="btn_out_all" class="btn btn-default" style="margin-left: 20px;">导出全部</button>
						</fieldset>
						<fieldset align="center">
							<legend>客户列表</legend>
							<table class="table table-bordered table-condensed table-striped table-hover">
								<thead>
									<tr align="center">
										<th><input type="checkbox" id="selectAll"/>全选</th>
										<th>车牌号</th>
										<th>车架号</th>
										<th>联系电话</th>
										<th>保单号</th>
										<th>终保时间</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${customs}" var="custom">
										<tr>
											<td><input type="checkbox" name="customids" value="${custom.customid }"/></td>
											<td>${custom.licenseplates }</td>
											<td>${custom.phonenum }</td>
											<td>${custom.carframecode }</td>
											<td>${custom.insurancecode }</td>
											<td><fmt:formatDate value="${custom.endtime }" pattern="yyyy/MM/dd" /></td>
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
						method="post" onsubmit="return checkUpdate();">
						<fieldset align="center">
							<legend>客户详细信息</legend>
							<div class="form-group ">
								<label class="control-label col-sm-3"
									for="licenseplates">车牌号</label>
								<div class="controls col-sm-9">
									<input type="hidden" id="customid" name="customid" value="" />
									<input type="text" id="licenseplates" class="col-sm-8" 
										name="licenseplates" required readonly="readonly"/>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-3"
									for="idcard">身份证号</label>
								<div class="controls col-sm-9">
									<input type="text" id="idcard" class="col-sm-8" 
										name="idcard" readonly="readonly"/>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-3"
									for="agencycode">机构代码</label>
								<div class="controls col-sm-9">
									<input type="text" id="agencycode" class="col-sm-8" 
										name="agencycode" readonly="readonly" />
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-3"
									for="phonenum">联系电话</label>
								<div class="controls col-sm-9">
									<input type="text" id="phonenum" class="col-sm-8" 
										name="phonenum" required readonly="readonly" />
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-3"
									for="carowner">车主</label>
								<div class="controls col-sm-9">
									<input type="text" id="carowner" class="col-sm-8" 
										name="carowner" readonly="readonly" />
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-3"
									for="insurer">投保人</label>
								<div class="controls col-sm-9">
									<input type="text" id="insurer" class="col-sm-8" 
										name="insurer" required readonly="readonly" />
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-3"
									for="insured">被保险人</label>
								<div class="controls col-sm-9">
									<input type="text" id="insured" class="col-sm-8" 
										name="insured" required readonly="readonly"/>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-3"
									for="carmodel">车辆型号</label>
								<div class="controls col-sm-9">
									<input type="text" id="carmodel" class="col-sm-8" 
										name="carmodel" required readonly="readonly"/>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-3"
									for="carframecode">车架号码</label>
								<div class="controls col-sm-9">
									<input type="text" id="carframecode" class="col-sm-8" 
										name="carframecode" required readonly="readonly"/>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-3"
									for="enginecode">发动机号码</label>
								<div class="controls col-sm-9">
									<input type="text" id="enginecode" class="col-sm-8" 
										name="enginecode" required readonly="readonly"/>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-3"
									for="firsttime">初登日期</label>
								<div class="controls col-sm-9">
									<input type="text" id="firsttime" class="col-sm-8" 
										name="firsttime" required readonly="readonly"/>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-3"
									for="starttime">始保日期</label>
								<div class="controls col-sm-9">
									<input type="text" id="starttime" class="col-sm-8" 
										name="starttime" required readonly="readonly"/>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-3"
									for="endtime">终保日期</label>
								<div class="controls col-sm-9">
									<input type="text" id="endtime" class="col-sm-8" 
										name="endtime" required readonly="readonly"/>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-3"
									for="insurance">承保单位</label>
								<div class="controls col-sm-9">
									<input type="text" id="insurance" class="col-sm-8" 
										name="insurance" required readonly="readonly"/>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-3"
									for="insurancecode">保单号</label>
								<div class="controls col-sm-9">
									<input type="text" id="insurancecode" class="col-sm-8" 
										name="insurancecode" required readonly="readonly"/>
								</div>
							</div>
							<div class="form-group">
								<label style="text-align: right;">
									<input type="checkbox" id="cardamage"  
										name="cardamage" value="√"  readonly="readonly"/>&nbsp;&nbsp;车损
								</label>&nbsp;&nbsp;&nbsp;&nbsp;
								<label>
									<input type="checkbox" id="robbery" 
										name="robbery" value="√" readonly="readonly"/>&nbsp;&nbsp;盗抢
								</label>&nbsp;&nbsp;&nbsp;&nbsp;
								<label>
									<input type="checkbox" id="autoignition"  
										name="autoignition" value="√" />&nbsp;&nbsp;自燃
								</label>&nbsp;&nbsp;&nbsp;&nbsp;
								<label>
									<input type="checkbox" id="wading" 
										name="wading" value="√" />&nbsp;&nbsp;涉水
								</label>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-3">三者（万）</label>&nbsp;
								<label>
									<input type="radio" name="three" value="0" />0
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
										name="driver" readonly="readonly"/>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-3"
									for="passenger">乘客</label>
								<div class="controls col-sm-9">
									<input type="text" id="passenger" class="col-sm-8" 
										name="passenger" readonly="readonly" />
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-3">玻璃</label>
								<label class="col-sm-2 ">
									<input type="radio" name="glass" value="none" readonly="readonly"/>无
								</label>
								<label class="col-sm-3 ">
									<input type="radio" name="glass" value="foreign" readonly="readonly"/>进口
								</label>
								<label class="col-sm-3" >
									<input type="radio" name="glass" value="domestic" readonly="readonly"/>国产
								</label>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-3">划痕</label>&nbsp;
								<label>
									<input type="radio" name="nick" value="0" />0
								</label>&nbsp;
								<label>
									<input type="radio" name="nick" value="2000" />2000
								</label>&nbsp;
								<label>
									<input type="radio" name="nick" value="5000" />5000
								</label>&nbsp;
								<label>
									<input type="radio" name="nick" value="10000" />10000
								</label>&nbsp;
								<label>
									<input type="radio" name="nick" value="15000" />15000
								</label>
							</div>
							<div class="form-group">
								
							</div>
							<label class="control-label col-sm-1"
								for="remark">备注</label>
							<div class="controls col-sm-10">
								<textarea id="remark" 
									name="remark" cols="35" rows="3" wrap="physical" readonly="readonly" >
								</textarea>
							</div>
						</fieldset>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	<script src="${pageContext.request.contextPath }/js/outCustom.js" type="text/javascript"></script>
</body>
</html>
