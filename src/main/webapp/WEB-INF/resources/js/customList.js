$(document)
		.ready(
				function() {
					/**
					 * 全选
					 */
					$("#selectAll").click(
							function() {
								var isSelectAll = $(this).prop("checked");
								if (isSelectAll) {
									$("input[name^='customids']").prop(
											"checked", true);
								} else {
									$("input[name^='customids']").prop(
											"checked", false);
								}
							});
					
					/**
					 * 鼠标悬浮变手
					 */
					$(".table tbody tr").mouseover(function() {
						$(this).css("cursor", "pointer");
					});
					
					/**
					 * 单击行请求custom详细信息
					 */
					$(".table tbody tr")
							.click(
									function() {
										var id = $(this).find("td").eq(0)
												.html();
										$
												.post(
														"getCustom.do",
														{
															customid : id
														},
														function(data) {
															if (data
																	.indexOf("nologin") >= 0) {
																alert("你还没登陆或登录已过期，请登录！");
																window.location.href = "../login";
															} else if (data
																	.indexOf("none") >= 0) {
																alert("该客户已不存在！");
																window.location.href = "showCustom";
															}
															var json = $
																	.parseJSON(data);
															$(
																	"#form_custom_update #customid")
																	.val(
																			json.customid);
															$(
																	"#form_custom_update #licenseplates")
																	.val(
																			json.licenseplates);
															$(
																	"#form_custom_update #idcard")
																	.val(
																			json.idcard);
															$(
																	"#form_custom_update #agencycode")
																	.val(
																			json.agencycode);
															$(
																	"#form_custom_update #phonenum")
																	.val(
																			json.phonenum);
															$(
																	"#form_custom_update #carowner")
																	.val(
																			json.carowner);
															$(
																	"#form_custom_update #insurer")
																	.val(
																			json.insurer);
															$(
																	"#form_custom_update #insured")
																	.val(
																			json.insured);
															$(
																	"#form_custom_update #carmodel")
																	.val(
																			json.carmodel);
															$(
																	"#form_custom_update #carframecode")
																	.val(
																			json.carframecode);
															$(
																	"#form_custom_update #enginecode")
																	.val(
																			json.enginecode);
															$(
																	"#form_custom_update #firsttime")
																	.val(
																			json.firsttime);
															$(
																	"#form_custom_update #starttime")
																	.val(
																			json.starttime);
															$(
																	"#form_custom_update #endtime")
																	.val(
																			json.endtime);
															$(
																	"#form_custom_update #insurance")
																	.val(
																			json.insurance);
															$(
																	"#form_custom_update #insurancecode")
																	.val(
																			json.insurancecode);
															if (json.cardamage != "") {
																$(
																		"#form_custom_update input[name='cardamage']:checkbox")
																		.prop(
																				"checked",
																				true);
															} else {
																$(
																		"#form_custom_update input[name='cardamage']:checkbox")
																		.prop(
																				"checked",
																				false);
															}
															if (json.robbery != "") {
																$(
																		"#form_custom_update input[name='robbery']")
																		.prop(
																				"checked",
																				true);
															} else {
																$(
																		"#form_custom_update input[name='robbery']")
																		.prop(
																				"checked",
																				false);
															}
															var three = false;
															if (json.three20 != "") {
																three = true;
																$(
																		"#form_custom_update input[name='three']")
																		.eq(1)
																		.prop(
																				"checked",
																				true);
															} else if (json.three30 != "") {
																three = true;
																$(
																		"#form_custom_update input[name='three']")
																		.eq(2)
																		.prop(
																				"checked",
																				true);
															} else if (json.three50 != "") {
																three = true;
																$(
																		"#form_custom_update input[name='three']")
																		.eq(3)
																		.prop(
																				"checked",
																				true);
															} else if (json.three100 != "") {
																three = true;
																$(
																		"#form_custom_update input[name='three']")
																		.eq(4)
																		.prop(
																				"checked",
																				true);
															} else if (json.three150 != "") {
																three = true;
																$(
																		"#form_custom_update input[name='three']")
																		.eq(5)
																		.prop(
																				"checked",
																				true);
															}
															if (!three) {
																$(
																		"#form_custom_update input[name='three']")
																		.eq(0)
																		.prop(
																				"checked",
																				true);
															}
															$(
																	"#form_custom_update #driver")
																	.val(
																			json.driver);
															$(
																	"#form_custom_update #passenger")
																	.val(
																			json.passenger);
															if (json.foreignglass != "") {
																$(
																		"#form_custom_update input[name='glass']")
																		.eq(0)
																		.prop(
																				"checked",
																				true);
															} else if (json.domesticglass != "") {
																$(
																		"#form_custom_update input[name='glass']")
																		.eq(1)
																		.prop(
																				"checked",
																				true);
															}
															var nick = false;
															if (json.nick2 != "") {
																nick = true;
																$(
																		"#form_custom_update input[name='nick']")
																		.eq(1)
																		.prop(
																				"checked",
																				true);
															} else if (json.nick5 != "") {
																nick = true;
																$(
																		"#form_custom_update input[name='nick']")
																		.eq(2)
																		.prop(
																				"checked",
																				true);
															} else if (json.nick10 != "") {
																nick = true;
																$(
																		"#form_custom_update input[name='nick']")
																		.eq(3)
																		.prop(
																				"checked",
																				true);
															} else if (json.nick15 != "") {
																nick = true;
																$(
																		"#form_custom_update input[name='nick']")
																		.eq(4)
																		.prop(
																				"checked",
																				true);
															}
															if (!nick) {
																$(
																		"#form_custom_update input[name='nick']")
																		.eq(0)
																		.prop(
																				"checked",
																				true);
															}
															if (json.autoignition != "") {
																$(
																		"#form_custom_update input[name='autoignition']")
																		.eq(0)
																		.prop(
																				"checked",
																				true);
															} else {
																$(
																		"#form_custom_update input[name='autoignition']")
																		.eq(0)
																		.prop(
																				"checked",
																				false);
															}
															if (json.wading != "") {
																$(
																		"#form_custom_update input[name='wading']")
																		.eq(0)
																		.prop(
																				"checked",
																				true);
															} else {
																$(
																		"#form_custom_update input[name='wading']")
																		.eq(0)
																		.prop(
																				"checked",
																				false);
															}
															$(
																	"#form_custom_update #remark")
																	.text(
																			json.remark);
														});
									});

				});

/**
 * 编辑custom
 * @param action 访问地址
 * @returns {Boolean}
 */
function updateCustom(action) {
	var cusid = $("#form_custom_update #customid").val();
	var licen = $("#form_custom_update #licenseplates").val();
	var idCard = $("#form_custom_update #idcard").val();
	var agency = $("#form_custom_update #agencycode").val();
	var phone = $("#form_custom_update #phonenum").val();
	var carOwner = $("#form_custom_update #carowner").val();
	var iner = $("#form_custom_update #insurer").val();
	var ined = $("#form_custom_update #insured").val();
	var carModel = $("#form_custom_update #carmodel").val();
	var frame = $("#form_custom_update #carframecode").val();
	var engine = $("#form_custom_update #enginecode").val();
	var first = $("#form_custom_update #firsttime").val();
	var start = $("#form_custom_update #starttime").val();
	var end = $("#form_custom_update #endtime").val();
	var ince = $("#form_custom_update #insurance").val();
	var incode = $("#form_custom_update #insurancecode").val();
	var damage = $("#form_custom_update input[name='cardamage']:checked").val();
	var rob = $("#form_custom_update input[name='robbery']:checked").val();
	var threee = $("#form_custom_update input[name='three']:checked").val();
	var dr = $("#form_custom_update #driver").val();
	var pass = $("#form_custom_update #passenger").val();
	var gla = $("#form_custom_update input[name='glass']:checked").val();
	var ni = $("#form_custom_update input[name='nick']:checked").val();
	var auto = $("#form_custom_update input[name='autoignition']:checked")
			.val();
	var wad = $("#form_custom_update input[name='wading']:checked").val();
	var mark = $("#form_custom_update #remark").text();
	if ($.trim(licen) == "") {
		alert("必须填入车牌号！");
		$("#form_custom_update #licenseplates").focus();
		return false;
	}
	if ($.trim(idcard) == "" && $.trim(agencycode) == "") {
		alert("身份证号和机构代码至少填一个！");
		$("#form_custom_update #idcard").focus();
		return false;
	}
	if ($.trim(phone) == "") {
		alert("必须填入联系电话！");
		$("#form_custom_update #phonenum").focus();
		return false;
	}
	if ($.trim(frame) == "") {
		alert("必须填入车架号！");
		$("#form_custom_update #carframecode").focus();
		return false;
	}
	if ($.trim(start) == "") {
		alert("必须填入始保时间！");
		$("#form_custom_update #starttime").focus();
		return false;
	}
	if ($.trim(end) == "") {
		alert("必须填入终保时间！");
		$("#form_custom_update #endtime").focus();
		return false;
	}
	if ($.trim(incode) == "") {
		alert("必须填入保单号！");
		$("#form_custom_update #insurancecode").focus();
		return false;
	}
	if (action.indexOf("updateCustom") >= 0) {
		$.post(action, {
			customid : cusid,
			licenseplates : licen,
			idcard : idCard,
			agencycode : agency,
			phonenum : phone,
			carowner : carOwner,
			insurer : iner,
			insured : ined,
			carmodel : carModel,
			carframecode : frame,
			enginecode : engine,
			firsttime : first,
			starttime : start,
			endtime : end,
			insurance : ince,
			insurancecode : incode,
			cardamage : damage,
			robbery : rob,
			three : threee,
			nick : ni,
			driver : dr,
			passenger : pass,
			glass : gla,
			wading : wad,
			autoignition : auto,
			remark : mark
		}, function(data) {
			if (data.indexOf("nologin") >= 0) {
				alert("你没有登录或登录已过期，请登录!");
				window.location.href = "../login";
			} else if (data.indexOf("500") >= 0) {
				window.location.href = "../500";
				alert("修改失败，请重试！");
			} else if (data.indexOf("true") >= 0) {
				alert("修改完成");
			}
		});
	} else if (action.indexOf("addCustom") >= 0) {
		$.post("checkAdd", {
			carCode : licen,
			carFrameCode : frame
		}, function(data) {
			if (data.indexOf("true") >= 0) {
				var confirmText = "";
				var result = data.split("|");
				if (result[1].indexOf("carCode") >= 0) {
					confirmText = "该车牌号已存在，是否覆盖？";
				} else if (result[1].indexOf("carFrameCode") >= 0) {
					confirmText = "该车架号已存在，是否覆盖？";
				}
				if (confirm(confirmText)) {
					$.post("updateCustom.do", {
						customid : result[2],
						licenseplates : licen,
						idcard : idCard,
						agencycode : agency,
						phonenum : phone,
						carowner : carOwner,
						insurer : iner,
						insured : ined,
						carmodel : carModel,
						carframecode : frame,
						enginecode : engine,
						firsttime : first,
						starttime : start,
						endtime : end,
						insurance : ince,
						insurancecode : incode,
						cardamage : damage,
						robbery : rob,
						three : threee,
						nick : ni,
						driver : dr,
						passenger : pass,
						glass : gla,
						wading : wad,
						autoignition : auto,
						remark : mark
					}, function(data) {
						if (data.indexOf("nologin") >= 0) {
							alert("你没有登录或登录已过期，请登录!");
							window.location.href = "../login";
						} else if (data.indexOf("500") >= 0) {
							window.location.href = "../500";
							alert("覆盖失败，请重试！");
						} else if (data.indexOf("true") >= 0) {
							alert("覆盖成功");
							window.location.href = "showCustom";
						}
					});
				} else {
					if (result[1].indexOf("carCode") >= 0) {
						$("#form_custom_update #licenseplates").focus();
					} else {
						$("#form_custom_update #carframecode").focus();
					}
				}
			} else if (data.indexOf("false") >= 0) {
				$.post(action, {
					licenseplates : licen,
					idcard : idCard,
					agencycode : agency,
					phonenum : phone,
					carowner : carOwner,
					insurer : iner,
					insured : ined,
					carmodel : carModel,
					carframecode : frame,
					enginecode : engine,
					firsttime : first,
					starttime : start,
					endtime : end,
					insurance : ince,
					insurancecode : incode,
					cardamage : damage,
					robbery : rob,
					three : threee,
					nick : ni,
					driver : dr,
					passenger : pass,
					glass : gla,
					wading : wad,
					autoignition : auto,
					remark : mark
				}, function(data) {
					if (data.indexOf("nologin") >= 0) {
						alert("你没有登录或登录已过期，请登录!");
						window.location.href = "../login";
					} else if (data.indexOf("500") >= 0) {
						window.location.href = "../500";
						alert("新增失败，请重试！");
					} else if (data.indexOf("true") >= 0) {
						alert("新增成功");
						window.location.href = "showCustom";
					}
				});
			} else if (data.indexOf("nologin") >= 0) {
				alert("你没有登录或登录已过期，请登录!");
				window.location.href = "../login";
			} else if (data.indexOf("500") >= 0) {
				window.location.href = "../500";
				alert("新增失败，请重试！");
			}
		});
	}
}

/**
 * 搜索校验
 * @returns {Boolean}
 */
function checkSearch() {
	var carcode = $("#form_custom_search #carCode").val();
	var carFrameCode = $("#form_custom_search #carFrameCode").val();
	if ($.trim(carcode) == "" && $.trim(carFrameCode) == "") {
		alert("至少输入一个搜索条件");
		return false;
	}
	return true;
}

/**
 * 删除校验
 */
function checkDel() {
	var isSelected = false;
	$("input[name^='customids']").each(function(i) {
		var isCheck = $(this).prop("checked");
		if (isCheck) {
			isSelected = true;
			return false;
		}
	});
	if (!isSelected) {
		alert("至少勾选一个！");
		return isSelected;
	}
	return confirm("确定要删除吗？");
}