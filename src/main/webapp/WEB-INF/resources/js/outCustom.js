$(document)
		.ready(
				function() {

					var nowDate = new Date();
					var nowYear = nowDate.getFullYear();
					$("#year option").remove();
					$("#year").append("<option value=''>请选择年份</option>");

					for (var i = nowYear; i >= nowYear - 50; i--) {
						$("#year")
								.append(
										"<option value='" + i + "'>" + i
												+ "年</option>");
						if (i <= 1970) {
							break;
						}
					}
					var year = $("#hiddenYear").val();
					var month = $("#hiddenMonth").val();
					$("#year").val(year);
					$("#month").val(month);

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

					$("#btn_search").click(
							function() {
								var basePath = $("#basePath").val();
								$("#form_custom").attr("action",
										basePath + "/custom/outCustom");
								$("#form_custom").submit();
							});

					$("#btn_out").click(
							function() {
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
									return false;
								}
								var basePath = $("#basePath").val();
								$("#form_custom").attr("action",
										basePath + "/custom/output.do");
								$("#form_custom").submit();
							});

					$("#btn_out_all").click(
							function() {
								var basePath = $("#basePath").val();
								$("#form_custom").attr("action",
										basePath + "/custom/outputAll.do");
								$("#form_custom").submit();
							});

					$(".table tbody tr").mouseover(function() {
						$(this).css("cursor", "pointer");
					});

					$(".table tbody tr")
							.click(
									function() {
										var id = $(this).find("td").eq(0).find(
												"input").val();
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
															$(
																	"#form_custom_update input[name='cardamage']:checkbox")
																	.prop(
																			"disabled",
																			true);
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
															$(
																	"#form_custom_update input[name='robbery']")
																	.prop(
																			"disabled",
																			true);
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
																	"#form_custom_update input[name='three']")
																	.prop(
																			"disabled",
																			true);
															$(
																	"#form_custom_update #driver")
																	.val(
																			json.driver);
															$(
																	"#form_custom_update #passenger")
																	.val(
																			json.passenger);
															var bglass = false;
															if (json.foreignglass != "") {
																bglass = true;
																$(
																		"#form_custom_update input[name='glass']")
																		.eq(1)
																		.prop(
																				"checked",
																				true);
															} else if (json.domesticglass != "") {
																bglass = true;
																$(
																		"#form_custom_update input[name='glass']")
																		.eq(2)
																		.prop(
																				"checked",
																				true);
															}
															if(!bglass){
																$(
																"#form_custom_update input[name='glass']")
																.eq(0)
																.prop(
																		"checked",
																		true);
															}
															$(
																	"#form_custom_update input[name='glass']")
																	.prop(
																			"disabled",
																			true);
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
															$(
																	"#form_custom_update input[name='nick']")
																	.prop(
																			"disabled",
																			true);
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
															$(
																	"#form_custom_update input[name='autoignition']")
																	.prop(
																			"disabled",
																			true);
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
																	"#form_custom_update input[name='wading']")
																	.prop(
																			"disabled",
																			true);
															$(
																	"#form_custom_update #remark")
																	.val(
																			json.remark);
														});
									});
				});

var Select = {
	del : function(obj, e) {
		if ((e.keyCode || e.which || e.charCode) == 8) {
			var opt = obj.options[0];
			opt.text = opt.value = opt.value.substring(0,
					opt.value.length > 0 ? opt.value.length - 1 : 0);
		}
	},
	write : function(obj, e) {
		if ((e.keyCode || e.which || e.charCode) == 8)
			return;
		var opt = obj.options[0];
		opt.selected = "selected";
		opt.text = opt.value += String.fromCharCode(e.charCode || e.which
				|| e.keyCode);
	}
}

function changeCurrentPage(currentPage) {
	$("#currentPage").val(currentPage);
	var basePath = $("#basePath").val();
	$("#form_custom").attr("action", basePath + "/custom/outCustom");
	$("#form_custom").submit();
}