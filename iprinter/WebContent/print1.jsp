<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.printer.util.UserManager" %>
<!-- 用户必须登录系统才能进入该页面 -->
<%
/* if(!UserManager.isLogin(request))
	return; */
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="WEB-INF/include/title.jsp" %>

<!-- custom styles -->
<link href="static/css/custom.css" rel="stylesheet">
<link href="static/css/prettify.css" rel="stylesheet">

<!-- fileinput -->
<link href="static/css/fileinput.min.css" rel="stylesheet">

<style>
.progress {margin-top:10px;}
	
.bwizard-steps {width: 100%;}
.bwizard-steps > li {width: 24%;}
.bwizard-steps > li > a {color: grey; font-weight: bold;}
.bwizard-steps > li:last-child {width: 25%;}

#rootwizard .pager {width: 20%; float: right;}
</style>


<!-- fileupload -->
<script src="static/js/fileinput.min.js"></script>
<script src="static/js/fileinput_locale_zh.js"></script>


</head>
<body>
<%@ include file="WEB-INF/include/header.jsp" %>

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
						  	<li><a href="#tab1" data-toggle="tab"><span class="label">1</span> 上传文件</a></li>
							<li><a href="#tab2" data-toggle="tab"><span class="label">2</span> 参数设置</a></li>
							<li><a href="#tab3" data-toggle="tab"><span class="label">3</span> 确认订单</a></li>
							<li><a href="#tab4" data-toggle="tab"><span class="label">4</span> 去&nbsp;支&nbsp;付</a></li>
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
									    	uploadUrl: "UploadServlet",
									    	uploadAsync: true,
									    	showCaption: true,
									    	minFileCount: 1,
									    	maxFileCount: 5,
									        allowedFileExtensions: ["txt", "md", "ini", "text"],
									    });
									    $("#input-file").on("fileuploaded", function() {
									    	$("#upload-status").val("ok");
									    });
									});
								</script>
						    </div>
						    
						    <div class="tab-pane" id="tab2">
						      <input type="hidden" name="arguments[]" value="" />
						      <table class="table table-striped table-hover table-bordered">
						      	<thead>
									<tr><th>序号</th><th>文件名</th><th>打印份数</th><th>文档页数</th><th>参数设置</th><th>操作</th></tr>
						      	</thead>
						      	<tbody>
									<tr><td>1</td><td>abc.txt</td><td>2</td><td>2</td><td><a>详细参数设置</a></td><td><a>删除</a></td></tr>
									<tr><td>2</td><td>abc.txt</td><td>2</td><td>2</td><td><a>详细参数设置</a></td><td><a>删除</a></td></tr>
									<tr><td>3</td><td>abc.txt</td><td>2</td><td>2</td><td><a>详细参数设置</a></td><td><a>删除</a></td></tr>
									<tr><td>4</td><td>abc.txt</td><td>2</td><td>2</td><td><a>详细参数设置</a></td><td><a>删除</a></td></tr>
									<tr><td>5</td><td>abc.txt</td><td>2</td><td>2</td><td><a>详细参数设置</a></td><td><a>删除</a></td></tr>
						      	</tbody>
							  </table>
						    </div>
						    
							<div class="tab-pane" id="tab3">
								<table class="table table-striped table-hover table-bordered">
									<caption align="top">待打印文件列表</caption>
									<thead>
										<tr><th>序号</th><th>文件名</th><th>打印份数</th><th>文档页数</th><th>参数设置</th><th>操作</th></tr>
									</thead>
									<tbody>
										<tr><td>1</td><td>abc.txt</td><td>2</td><td>2</td><td><a>详细参数设置</a></td><td><a>删除</a></td></tr>
										<tr><td>2</td><td>abc.txt</td><td>2</td><td>2</td><td><a>详细参数设置</a></td><td><a>删除</a></td></tr>
										<tr><td>3</td><td>abc.txt</td><td>2</td><td>2</td><td><a>详细参数设置</a></td><td><a>删除</a></td></tr>
										<tr><td>4</td><td>abc.txt</td><td>2</td><td>2</td><td><a>详细参数设置</a></td><td><a>删除</a></td></tr>
										<tr><td>5</td><td>abc.txt</td><td>2</td><td>2</td><td><a>详细参数设置</a></td><td><a>删除</a></td></tr>
									</tbody>
							  </table>
						    </div>
						    
							<div class="tab-pane" id="tab4">
								<div class="control-group">
							    <label class="control-label" for="url">URL</label>
							    <div class="controls">
							      <input type="text" id="urlfield" name="urlfield" class="required url">
							    </div>
							  </div>
						    </div>
						    
							<ul class="pager wizard">
								<li class="previous"><a href="#">上一步</a></li>
							  	<li class="next"><a href="#">下一步</a></li>
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
				if(index==1) {
					// 验证是否已经完成上传文件
					if($("#upload-status").val() != "ok") {
						alert("亲，您还未上传文件哦~~~");
						return false;
					}
				}

			

			}, 
			onTabShow: function(tab, navigation, index) {
				var $total = navigation.find('li').length;
				var $current = index;
				var $percent = ($current/$total) * 100;
				$('#rootwizard .progress-bar').css({width:$percent+'%'});
			}
	  	});
		window.prettyPrint && prettyPrint();
	});
	</script>

	
	
</body>
</html>