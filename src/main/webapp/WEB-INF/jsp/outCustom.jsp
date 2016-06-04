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
						<a href="${pageContext.request.contextPath}/loginRecord/showRecord">用户登录记录</a>
					</p>
				</c:if>
				<p>
					<a href="${pageContext.request.contextPath}/user/changePwd">用户密码修改</a>
				</p>
				</aside>
			</div>
			<div class="col-sm-11">
				<div class="col-sm-8" >
					<form id="form_custom_search" role="form" 
						action="${pageContext.request.contextPath}/custom/searchOut.do" onsubmit="return checkSearch();" 
						method="post">
						<fieldset align="center">
							<legend>终保时间范围搜索</legend>
						      	<label>范围头</label>
						      	<input type="text" id="time1" name="time1" 
						      		value="<fmt:formatDate value='${time1 }' pattern='yyyy/MM/dd' />"  
						         	onfocus="MyCalendar.SetDate(this)" placeholder="2016/01/01" required />
						   		<label style="margin-left: 20px;">范围尾</label>
						      	<input type="text" id="time2" name="time2" 
									value="<fmt:formatDate value='${time2 }' pattern='yyyy/MM/dd' />"  
						      		onfocus="MyCalendar.SetDate(this)" placeholder="2016/02/31" required />
						   		<button class="btn btn-default">搜索</button>
						</fieldset>
					</form>
					<form id="form_user" class="form-inline" role="form" 
						action="${pageContext.request.contextPath}/custom/output.do" 
						method="post" onsubmit="return checkDel();">
						<fieldset align="center">
							<legend>客户列表</legend>
							<table class="table table-bordered table-condensed table-striped table-hover">
								<thead>
									<tr align="center">
										<th colspan="6"><button class="btn btn-default" type="submit" >导出选中</button></th>
									</tr>
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
								<label class="col-sm-3" style="text-align: right;">
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
									<input type="radio" name="three" value="20" />20
								</label>&nbsp;&nbsp;&nbsp;
								<label>
									<input type="radio" name="three" value="50" />50
								</label>&nbsp;&nbsp;&nbsp;
								<label>
									<input type="radio" name="three" value="100" />100
								</label>&nbsp;&nbsp;&nbsp;
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
								<label class="col-sm-3 ">
									<input type="radio" name="glass" value="foreign" readonly="readonly"/>&nbsp;&nbsp;进口
								</label>
								<label class="col-sm-3" >
									<input type="radio" name="glass" value="domestic" readonly="readonly"/>&nbsp;&nbsp;国产
								</label>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-3">划痕</label>&nbsp;
								<label>
									<input type="radio" name="nick" value="2000" />2000
								</label>&nbsp;&nbsp;&nbsp;
								<label>
									<input type="radio" name="nick" value="5000" />5000
								</label>&nbsp;&nbsp;&nbsp;
								<label>
									<input type="radio" name="nick" value="10000" />10000
								</label>&nbsp;&nbsp;&nbsp;
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
	
	<script type="text/javascript">
		$(document).ready(function (){
			$("#selectAll").click(function (){
				var isSelectAll = $(this).prop("checked");
				if(isSelectAll){
					$("input[name^='customids']").prop("checked", true);
				} else {
					$("input[name^='customids']").prop("checked", false);
				}
			});
			
			$(".table tbody tr").click(function (){
				var id = $(this).find("td").eq(0).find("input").val();
				$.post("getCustom.do", {customid: id} , function (data){
					if(data.indexOf("nologin") >= 0){
						alert("你还没登陆或登录已过期，请登录！");
						window.location.href = "../login";
					} else if(data.indexOf("none") >= 0){
						alert("该客户已不存在！");
						window.location.href = "showCustom";
					}
					var json = $.parseJSON(data);
					$("#form_custom_update #customid").val(json.customid);
					$("#form_custom_update #licenseplates").val(json.licenseplates);
					$("#form_custom_update #idcard").val(json.idcard);
					$("#form_custom_update #agencycode").val(json.agencycode);
					$("#form_custom_update #phonenum").val(json.phonenum);
					$("#form_custom_update #carowner").val(json.carowner);
					$("#form_custom_update #insurer").val(json.insurer);
					$("#form_custom_update #insured").val(json.insured);
					$("#form_custom_update #carmodel").val(json.carmodel);
					$("#form_custom_update #carframecode").val(json.carframecode);
					$("#form_custom_update #enginecode").val(json.enginecode);
					$("#form_custom_update #firsttime").val(json.firsttime);
					$("#form_custom_update #starttime").val(json.starttime);
					$("#form_custom_update #endtime").val(json.endtime);
					$("#form_custom_update #insurance").val(json.insurance);
					$("#form_custom_update #insurancecode").val(json.insurancecode);
					if(json.cardamage != ""){
						$("#form_custom_update input[name='cardamage']:checkbox").prop("checked", true);
					}else {
						$("#form_custom_update input[name='cardamage']:checkbox").prop("checked", false);
					}
					$("#form_custom_update input[name='cardamage']:checkbox").prop("disabled", true);
					if(json.robbery != ""){
						$("#form_custom_update input[name='robbery']").prop("checked", true);
					}else {
						$("#form_custom_update input[name='robbery']").prop("checked", false);
					}
					$("#form_custom_update input[name='robbery']").prop("disabled", true);
					if(json.three20 != ""){
						$("#form_custom_update input[name='three']").eq(0).prop("checked", true);
					}else if(json.three50 != ""){
						$("#form_custom_update input[name='three']").eq(1).prop("checked", true);
					}else if(json.three100 != ""){
						$("#form_custom_update input[name='three']").eq(2).prop("checked", true);
					}else if(json.three150 != ""){
						$("#form_custom_update input[name='three']").eq(3).prop("checked", true);
					}
					$("#form_custom_update input[name='three']").prop("disabled", true);
					$("#form_custom_update #driver").val(json.driver);
					$("#form_custom_update #passenger").val(json.passenger);
					if(json.foreignglass != ""){
						$("#form_custom_update input[name='glass']").eq(0).prop("checked", true);
					}else if(json.domesticglass != ""){
						$("#form_custom_update input[name='glass']").eq(1).prop("checked", true);
					}
					$("#form_custom_update input[name='glass']").prop("disabled", true);
					if(json.nick2 != ""){
						$("#form_custom_update input[name='nick']").eq(0).prop("checked", true);
					}else if(json.nick5 != ""){
						$("#form_custom_update input[name='nick']").eq(1).prop("checked", true);
					}else if(json.nick10 != ""){
						$("#form_custom_update input[name='nick']").eq(2).prop("checked", true);
					}else if(json.nick15 != ""){
						$("#form_custom_update input[name='nick']").eq(3).prop("checked", true);
					}
					$("#form_custom_update input[name='nick']").prop("disabled", true);
					if(json.autoignition != ""){
						$("#form_custom_update input[name='autoignition']").eq(0).prop("checked", true);
					}else {
						$("#form_custom_update input[name='autoignition']").eq(0).prop("checked", false);
					}
					$("#form_custom_update input[name='autoignition']").prop("disabled", true);
					if(json.wading != ""){
						$("#form_custom_update input[name='wading']").eq(0).prop("checked", true);
					}else {
						$("#form_custom_update input[name='wading']").eq(0).prop("checked", false);
					}
					$("#form_custom_update input[name='wading']").prop("disabled", true);
					$("#form_custom_update #remark").text(json.remark);
				});
			});
		});
		
		function checkSearch(){
			var time1 = $("#form_custom_search #time1").val();
			var time2 = $("#form_custom_search #time2").val();
			if($.trim(time1) == "" && $.trim(time2) == ""){
				alert("至少输入一个搜索条件");
				return false;
			}
			return true;
		}
		
		function checkDel(){
			var isSelected = false;
			$("input[name^='customids']").each(function(i){
				var isCheck = $(this).prop("checked");
				if(isCheck){
					isSelected = true;
					return false;
				}
			});
			if(!isSelected){
				alert("至少勾选一个！");
				return isSelected;
			}
			return confirm("确定要导出吗？");
		}
	</script>
</body>
</html>
