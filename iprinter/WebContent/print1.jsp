<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.printer.controller.UserManager" %>
<!-- 用户必须登录系统才能进入该页面 -->
<%
 if(!UserManager.isLogin(request))
	return; 
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="WEB-INF/include/title.jsp" %>
<!-- bootstrap-datetimepicker css -->
<link href="static/css/bootstrap-datetimepicker.min.css" rel="stylesheet">

<!-- custom styles -->
<link href="static/css/custom.css" rel="stylesheet">
<link href="static/css/prettify.css" rel="stylesheet">

<!-- fileinput css -->
<link href="static/css/fileinput.min.css" rel="stylesheet">


<style>
.progress {margin-top:10px;}
	
.bwizard-steps {width: 100%;}
.bwizard-steps > li {width: 24%;}
.bwizard-steps > li > a {color: grey; font-weight: bold;}
.bwizard-steps > li:last-child {width: 25%;}

#rootwizard .pager {width: 20%; float: right;}
</style>


<!-- fileinut js -->
<script src="static/js/fileinput.min.js"></script>
<script src="static/js/fileinput_locale_zh.js"></script>

<!-- 方法定义 -->
<script type="text/javascript">
	function settingView(entryid) {
		$.ajax({
			url: "PriceServlet", //列出所有纸张大小
			method: "get",
			dataType: "json",
			data: { 
				action: "list-papersize",
				entryid: entryid
			},
			success: function(data) {
				//展示文件名
				$("#strong-filename").text(data.originalname);
				
				//price
				var priceObj = data.price;
				
				//展示纸张大小
				var paperSizeList = data.paperSizeList;
				$("#paper-size td:last").empty();
				for(var i = 0; i < paperSizeList.length; i++) {
					if(paperSizeList[i] == priceObj.papersize) {
						$("#paper-size td:last").append("<label class='radio-inline' onclick='getPrice();'><input type='radio' name='papersize' value='" 
								+ paperSizeList[i] + "' checked>" + paperSizeList[i] + "</label>&nbsp;&nbsp;");
					} else {
						$("#paper-size td:last").append("<label class='radio-inline' onclick='getPrice();'><input type='radio' name='papersize' value='" 
								+ paperSizeList[i] + "'>" + paperSizeList[i] + "</label>&nbsp;&nbsp;");
					}
				}
				//展示是否双面打印
				if(priceObj.biside == 0) {
					$("#bi-side input[type=radio][name=biside][value=1]").prop("checked", false);
					$("#bi-side input[type=radio][name=biside][value=0]").prop("checked", 'checked');
				} else {
					$("#bi-side input[type=radio][name=biside][value=0]").prop("checked", false);
					$("#bi-side input[type=radio][name=biside][value=1]").prop("checked", 'checked');
				}
				
				if(priceObj.color == 0) {
					$("#print-color input[type=radio][name=color][value=1]").prop("checked", false);
					$("#print-color input[type=radio][name=color][value=0]").prop("checked", 'checked');
				} else {
					$("#print-color input[type=radio][name=color][value=0]").prop("checked", false);
					$("#print-color input[type=radio][name=color][value=1]").prop("checked", 'checked');
				}
				//展示单价
				$("#print-price strong").text(priceObj.price);
				
				//展示当前的打印数量
				var curPrintCount = $("#" + entryid).find("input").val();
				$("#print-count td:last").empty();
				$("#print-count td:last").append("<div id='entry-in-modal'><a onclick=\"decrease('')\"><i class='fa fa-minus-square'></i></a>&nbsp;" + 
				  		"<input type='text' value='"+ curPrintCount +"' class='text-center' size='5'>&nbsp;<a onclick=\"increase('')\"><i class='fa fa-plus-square'></i></a></div>");
				
				$("#entry-in-modal").val(entryid);
				//模态框弹出
				$('#myModal').modal().on('hidden.bs.modal', function(e) {
					$("#entry-in-modal").val("");
				});
				
			}
		});
		
	}
	
	//模态框点击保存按钮后调用该函数，进行数据同步
	function settingSave() {
		var entryid = $("#entry-in-modal").val();
		$('#myModal').modal("hide");
		$.ajax({
			url: "OrderServlet",
			method: "post",
			dataType: "json",
			data: {
				action: "set-entry",
				entryid: entryid,
				printcount: $("#print-count input").val(),
				papersize: $("#paper-size input:radio:checked").val(),
				biside: $("#bi-side input:radio:checked").val(),
				color: $("#print-color input:radio:checked").val(),
				price: $("#print-price strong").text()
			},
			success: function(data) {
				if(data.status == "ok") {
					//刷新tab2
					showTab2();
				}
			}
		});	
	}
	
	function getPrice() {
		$.ajax({
			url: "PriceServlet",
			method: "get",
			dataType: "json",
			data: {
				action: "get-price",
				papersize: $("#paper-size input:radio:checked").val(),
				biside: $("#bi-side input:radio:checked").val(),
				color: $("#print-color input:radio:checked").val()
			},
			success: function(data) {
				$("#print-price strong").text(data.price);
			}
		});
	}
	
	
	function del(entryid) {
		$.ajax({
			url: "UploadServlet",
			method: "get",
			dataType: "json",
			data: {
				action: "delete-file",
				entryid: entryid
			},
			success: function(data) {
				if(data.status == "ok") {
					//重新加载tab2页面
					showTab2();
				}
			} 
		});	
	}
	
	function decrease(entryid) {
		if(entryid == '') {
			decrease("entry-in-modal");
		} else {
			var old = parseInt($("#" + entryid).find("input").val());
			if(old < 2) return;
			$("#" + entryid).find("input").val(old - 1);
		}
	}
	
	function increase(entryid) {
		if(entryid == '') {
			increase("entry-in-modal");
		} else {
			var old = parseInt($("#" + entryid).find("input").val());
			if(old > 100) return;
			$("#" + entryid).find("input").val(old + 1);
		}
	}

	function postCount(entryid) {
		$.ajax({
			url: "OrderServlet",
			method: "post",
			dataTpye: "json",
			data: {
				action: "post-count",
				entryid: entryid,
				printcount: $("#" + entryid).find("input").val()
			},
			success: function(data) {
				return;
			}
		});
	}
	
</script>


</head>
<body>
<%@ include file="WEB-INF/include/header.jsp" %>


<!-- modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">修改打印参数</h4>
      </div>
      <div class="modal-body">
      
       	<div class="container-fluid">
       	  <div class="row">
       	  	<div class="col-md-12">
       	  	  <table style='border:0; margin-bottom:10px;'>
       	  	  	<tr><td style='padding-right:20px;'><img src="static/images/file.png" width="58px" height="71px;"></td>
       	  	  		<td><table><tr><td>文&nbsp;件&nbsp;名：</td><td><strong id="strong-filename"></strong></td></tr>
       	  	  			<tr><td>文件页数：</td><td><strong id="strong-pagecount">1</strong></td></tr>
       	  	  		</table></td>
       	  	  	</tr>
       	  	  </table>
       	  	</div>
       	  </div>
       	  
       	  <div class="row">
       	  	<div class="col-md-12">
       	  	  <table class="table ">
       	  	  	<tr id="paper-size"><td>纸张大小：</td><td></td></tr>
       	  	  	<tr id="bi-side"><td>双面打印：</td><td>
       	  	  		<label class="radio-inline" onclick='getPrice();'>
       	  				<input type="radio" name="biside" value="0"> 否
	       	  		</label>&nbsp;&nbsp;
	       	  		<label class="radio-inline" onclick='getPrice();'>
	       	  			<input type="radio" name="biside" value="1"> 是
	       	  		</label>
       	  	  	</td></tr>
       	  	  	<tr id="print-color"><td>彩色打印：</td><td>
       	  	  		<label class="radio-inline" onclick='getPrice();'>
       	  				<input type="radio" name="color" value="0" > 否
	       	  		</label>&nbsp;&nbsp;
	       	  		<label class="radio-inline" onclick='getPrice();'>
	       	  			<input type="radio" name="color" value="1"> 是
	       	  		</label>
       	  	  	</td></tr>
       	  	  	<tr id="print-price"><td>打印单价：</td><td>
       	  	  		<i class="fa fa-jpy"></i>&nbsp;<strong style="color:red"></strong>
       	  	  	</td></tr>
       	  	  	<tr id="print-count"><td>打印份数：</td><td>
       	  	  	</td></tr>
       	  	  </table>
       	  	</div>
       	  </div>	
       	</div>
      </div>
      <div class="modal-footer">
      	<input id="entry-in-modal" value="" type="hidden" />
        <button type="button" class="btn btn-primary" onclick="settingSave()">保存</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
      </div>
    </div>
  </div>
</div>



	<!-- Page Content -->
    <div class="container">
        <!-- Service Tabs -->
        <div class="row">
            <div class="col-lg-12">
                <h2 class="page-header">打印流程</h2>
            </div>
            <div class="col-lg-12">
				<form id="commentForm" method="get" action="" class="form-horizontal">
		        	<div id="rootwizard">
						<ul >
						  	<li><a href="#tab1" data-toggle="tab"><span class="label">1</span> 上传本地文件</a></li>
							<li><a href="#tab2" data-toggle="tab"><span class="label">2</span> 设置打印参数</a></li>
							<li><a href="#tab3" data-toggle="tab"><span class="label">3</span> 填写订单信息</a></li>
							<li><a href="#tab4" data-toggle="tab"><span class="label">4</span> 成功提交订单</a></li>
						</ul>
						<div id="bar" class="progress">
	                      <div class="progress-bar" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%;"></div>
	                    </div>
						<div class="tab-content">
						    <div class="tab-pane" id="tab1">
						    	<!-- 文件上传 -->
								<input id="input-file" name="myfile" type="file" multiple class="file-loading" >
								
								<input id="upload-status" type="hidden" value="" />
								<script>
									$(document).on('ready', function() {
									    $("#input-file").fileinput({
									    	language: "",
									    	uploadUrl: "UploadServlet",
									    	uploadAsync: true,
									    	showCaption: true,
									    	browseLabel: "浏览",
									    	removeClass: "btn btn-danger",
									        removeLabel: "删除",
									        uploadClass: "btn btn-success",
									        uploadLabel: "上传",
									    	minFileCount: 1,
									    	maxFileCount: 5,
									        allowedFileExtensions: ["txt", "doc", "docx", "text"]
									    }).on('filebatchpreupload', function(event, data) { //上传前
									        var n = data.files.length, files = n > 1 ? "这" + n + "个" : "该";
									        if (!window.confirm("确定上传" + files + "文件吗？")) {
									            return {
									                message: "取消上传!", // upload error message
									                data:{} // any other data to send that can be referred in `filecustomerror`
									            };
									        }
									    }).on("fileuploaded", function() {
									    	$("#upload-status").val("ok");
									    });
									});
								</script>
						    </div>
						    
						    <div class="tab-pane" id="tab2">
						      <table class="table table-striped table-bordered">
						      	<thead>
									<tr><th>序号</th><th>文件名</th><th>打印份数</th><th>文档页数</th><th>参数设置</th><th>操作</th></tr>
						      	</thead>
						      	<tbody></tbody>
							  </table>
							  
						    </div>
						    
							<div class="tab-pane" id="tab3">
								<div class="panel panel-success">
							    <div class="panel-heading">待打印文件列表</div>
									<table class="table table-striped table-hover table-bordered">
										<thead>
											<tr><th>序号</th><th>文件名</th><th>打印份数</th><th>文档页数</th><th>单价（元）</th><th>小计（元）</th></tr>
										</thead>
										<tbody></tbody>
								   </table>
							    </div>
							    <div class="panel panel-success">
							    <div class="panel-heading">订单信息</div><br />
									<form class="form-horizontal">
									  <div class="form-group">
									    <label for="inputSchoolid" class="col-sm-2 control-label">打印地点：</label>
									    <div class="col-sm-8">
									        <select class="form-control" id="selectShop"></select>
									    </div>
									  </div>
									  <div class="form-group">
									    <label for="total-count" class="col-sm-2 control-label">文档总数：</label>
									    <div class="col-sm-8">
									      <input type="text" class="form-control" id="total-count" readonly>
									      <input type="hidden" id="inputFilenumber" name="filenumber" value="">
									    </div>
									  </div>
									  <div class="form-group">
									    <label for="total-money" class="col-sm-2 control-label">订单金额：</label>
									    <div class="col-sm-8">
									      <input type="text" class="form-control" id="total-money" readonly>
									      <input type="hidden" id="inputMoney" name="money" value="">
									    </div>
									  </div>
									    <div class="form-group">
							                <label for="inputFetchetime" class="col-md-2 control-label">取货时间：</label>
							                <div class="input-group date form_time col-md-5"  data-link-field="inputFetchetime" >
							                    <input class="form-control" style="margin-left:14px;width:98%" type="text" value="" readonly>
												<span class="input-group-addon" style="left:-100px;"><span class="glyphicon glyphicon-time"></span></span>
							                </div>
											<input type="hidden" id="inputFetchetime" name="fetchtime" value="" /><br/>
							            </div>
									  <div class="form-group">
									    <label class="col-sm-2 control-label">支付方式：</label>
									    <div class="col-sm-8" id="radioPayway">
									      	<label class="radio-inline">
						       	  				<input type="radio" name="payway" value="1" checked> 支付宝支付
							       	  		</label>&nbsp;&nbsp;
							       	  		<label class="radio-inline">
							       	  			<input type="radio" name="payway" value="0"> 现金支付
							       	  		</label>
									    </div>
									  </div>
									  
									  <div class="form-group">
									  	<label for="inputComment" class="col-sm-2 control-label">订单备注：</label>
									    <div class="col-sm-8">
									      <textarea name="comment" class="form-control" rows="3" id="inputComment"></textarea>
									    </div>
									  </div>
									</form>
							    </div>
						    </div>
						    
							<div class="tab-pane" id="tab4">
								<div class="alert alert-success" role="alert">
								恭喜你，订单提交成功！
								</div>
								<div class="embed-responsive embed-responsive-16by9">
								  <iframe class="embed-responsive-item" src=""></iframe>
								</div>
						    </div>
						    
							<ul class="pager wizard">
								<li class="previous"><a href="#">上一步</a></li>
							  	<li class="next"><a href="#">下一步</a></li>
							  	<li class="next post" style="display:none"><a href="#">提交订单</a></li>
							</ul>
							
						</div>
					</div>
				</form>
            </div>
        </div>



<%@ include file="WEB-INF/include/footer.jsp" %>

	<!-- twitter-bootstrap-wizard -->
    <script src="static/js/jquery.bootstrap.wizard.js"></script>
    <script src="static/js/prettify.js"></script>
	<script>
	$(document).ready(function() {
	  	$('#rootwizard').bootstrapWizard({'tabClass': 'bwizard-steps', 
	  		onTabClick: function(tab, navigation, index) {
				//alert('on tab click disabled');
				return false;
			},
			onNext: function(tab, navigation, index) {
				if(index == 1) {
					// 验证是否已经完成上传文件
					if($("#upload-status").val() != "ok") {
						alert("亲，您还未上传文件哦~~~");
						return false;
					} 
				}  else if(index == 3) {
					//验证订单信息是否填写完整
					var shopid = $("#selectShop").val();
					var fetchtime = $("#inputFetchetime").val();
					var filenumber = $("#inputFilenumber").val();
					var money = $("#inputMoney").val();
					var comment = $("#inputComment").val();
					var payway = $("#radioPayway input:radio:checked").val();
					
					if(shopid == null || shopid == "" || typeof(shopid) == "undefined") {
						alert("亲，不要忘了选择打印地点哦~");
						return false;
					}
					if(fetchtime == null || fetchtime == "" || typeof(fetchtime) == "undefined") {
						alert("亲，不要忘了确认取货时间哦~");
						return false;
					}
					$.ajax({
						url: "OrderServlet",
						method: "post",
						dataType: "json",
						data: {
							action: "post-order",
							shopid: shopid,
							fetchtime: fetchtime,
							filenumber: filenumber,
							money: money,
							comment: comment,
							payway: payway
						},
						success: function(data) {
							alert(data.orderid);
							if(data.status == 'ok') {
								if(payway == 0) {
									//直接跳转到订单详情页面
									$("#tab4 iframe").attr("src", "OrderServelt?action=get-order-detail&orderid=" + orderid);
								} else {
									//跳转到支付页面
									window.location.href="AlipayServlet?orderid=" + orderid + "&money=" + money;
								}
							}
						}
					}); 
				} 

			}, 
			onTabShow: function(tab, navigation, index) {
				var $total = navigation.find('li').length;
				var $current = index;
				var $percent = ($current/$total) * 100;
				$('#rootwizard .progress-bar').css({width:$percent+'%'});
				
				if($current == $total-2) {
					$('#rootwizard').find('.pager .next').hide();
					$('#rootwizard').find('.pager .post').show();
					$('#rootwizard').find('.pager .post').removeClass('disabled');
				} else if($current == $total-1) {
					$('#rootwizard').find('.pager .next').hide();
					$('#rootwizard').find('.pager .previous').hide();
				} else {
					$('#rootwizard').find('.pager .next').show();
					$('#rootwizard').find('.pager .post').hide();
				}
				
				if(index == 1) {
					showTab2();
				}
				if(index == 2) {
					showTab3();
				} 
					
			}
	  	});
		window.prettyPrint && prettyPrint();
	});
	
	function showTab2() {
		$.ajax({
			url: "OrderServlet",
			method: "get",
			dataType: "json",
			data: {action: "get-session-entries"},
			success: function(data) {
				var entries = data.entries;
				$("#tab2 .table tbody").empty();
				var entryid, priceStr;
				for(var i = 0; i < entries.length; i++) {
					entryid = entries[i].id;
					priceStr = priceToString(entries[i].price);
					$("#tab2 .table tbody").append("<tr id='" + entryid + "'><td>" + (i + 1) + "</td><td>" 
							+ entries[i].file.originalname + "</td><td>" + "<div><a onclick=\"decrease('"
							+ entryid +"')\" onmouseout=\"postCount('"+ entryid +"')\"><i class='fa fa-minus-square'></i></a>&nbsp;" 
							+ "<input type='text' value='" + entries[i].printcount + "' class='text-center' size='5'>&nbsp;<a onclick=\"increase('"
					  		+ entryid +"')\" onmouseout=\"postCount('"+ entryid +"')\"><i class='fa fa-plus-square'></i></a></div></td><td>" + entries[i].pagenumber + "</td><td><a onclick=\"settingView('" 
							+ entryid + "');\"><strong style='font-size:13px; color:red;'>" + priceStr + "</strong></a></td><td><a onclick=\"del('"+ entryid + "')\">删除</a></td>"
					);
				}
			}
		});
	}
	
	function priceToString(priceObj) {
		var str = "[ " + priceObj.papersize + " | ";
		if(priceObj.biside == 0)
			str += "单面 | ";
		else 
			str += "双面 | ";
		if(priceObj.color == 0)
			str += "非彩印 | ";
		else
			str += "彩印 | ";
		return str += "￥" + priceObj.price + "/张 ]";
			
	}
	
	
	
	function showTab3() {
		$.ajax({
			url: "OrderServlet",
			method: "get",
			dataType: "json",
			data: {action: "get-session-entries"},
			success: function(data) {
				var entries = data.entries;
				var totalMoney = 0, totalCount = 0;
				$("#tab3 .table tbody").empty();
				for(var i = 0; i < entries.length; i++) {
					$("#tab3 .table tbody").append("<tr><td>" + (i + 1) + "</td><td>" 
							+ entries[i].file.originalname + "</td><td>" + entries[i].printcount + "</td><td>" 
							+ entries[i].pagenumber + "</td><td><i class='fa fa-jpy'></i>&nbsp;<strong style='font-size:13px; color:red;'>" 
							+ entries[i].price.price + "</strong></td><td><i class='fa fa-jpy'></i>&nbsp;<strong style='font-size:13px; color:red;'>"
							+ entries[i].money + "</td></tr>"
					);
					totalMoney += entries[i].money;
					totalCount += entries[i].printcount;
				}
				$("#total-money").val(totalMoney);
				$("#total-count").val(totalCount);
				$("#order-form input[type=hidden][name=money]").val(totalMoney);
				$("#order-form input[type=hidden][name=filenumber]").val(totalCount);
				$.ajax({
					url: "ShopServlet",
					method: "get",
					dataType: "json",
					data: {action: "list-nearby-shop"},
					success: function(data) {
						var shopList = data.shopList;
						$("#selectShop").empty();
						for(var i = 0 ; i < shopList.length; i++) {
							$("#selectShop").append("<option value='" 
									+ shopList[i].id + "'>" + shopList[i].shopname + "	" + shopList[i].phone + "	" 
									+ shopList[i].address + "	" + shopList[i].opentime + "</option>");
						}
						
					}
				});
			}
		});
	}
	
	</script>
	
	
	<!-- bootstrap-datetimepicker js -->
	<script src="static/js/bootstrap-datetimepicker.min.js"></script>
	<script src="static/js/bootstrap-datetimepicker.zh-CN.js"></script>
	<script>
	$('.form_time').datetimepicker({
		language: "zh-CN",
		format: "yyyy-mm-dd hh:ii",
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 1,
		minView: 0,
		maxView: 1,
		forceParse: 0,
		pickerPosition: "bottom-left"
    });
	</script>


</body>
</html>