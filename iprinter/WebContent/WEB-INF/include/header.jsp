	<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
	
	<div class="header-top">
		<div class="container">
			<div class="row" style="padding-top:10px;">
				<div class="col-md-10" >
					<form class="form-inline" action="">
						<i class="fa fa-fw fa-map-marker"></i>您的位置
						<select class="form-control" id="chooseLocation">
							<option value="">--请选择--</option>
						</select>
						<script>
						function setLocationItems() {
							$.ajax({
								url: "LocationServlet",
								method: "get",
								dataType: "json",
								data: {action: "list-all"},
								async: false,
								success: function(data) {
									if(data.size > 0) {
										var schools = data.schools;
										$("#chooseLocation").empty(); //清空下拉框
										var checked;
										for(var i = 0; i < schools.length; i++) {
											checked = (data.checked == schools[i].id ? "checked" : "");
												$("#chooseLocation").append("<option value='" 
														+ schools[i].id + "'"+ checked +">" + schools[i].schoolname + "</option>");
										}
									}
								}
							});
						}
						setLocationItems();
						</script>
						
					</form>
				</div>
				<div class="col-md-2">
					<span id="qqLoginBtn"></span>
					<script>
					QC.Login({
				       btnId:"qqLoginBtn", //btnId：插入按钮的节点id，必选   
				       scope:"all", //用户需要确认的scope授权项，可选，默认all
				       size: "B_M" //按钮尺寸，可用值[A_XL| A_L| A_M| A_S|  B_M| B_S| C_S]，可选，默认B_S
				    });
					</script>
				</div>
				
			</div>
		</div>
	</div>
	
	
	
    <!-- Navigation -->
    <nav class="navbar navbar-inverse" role="navigation" >
        <div class="navbar-container container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <a class="navbar-brand" href="index.html"><img src="static/images/printer_favorite.png" height="80px;" align="top"/></a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav" id="navbar-ul-lists">
                    <li role="presentation" id="li-1">
                       	<a href="index.jsp">首页</a>
                    </li>
                    <li role="presentation" id="li-2">
                        <a href="goprint.jsp">在线打印</a>
                    </li>
                    <li role="presentation" id="li-3">
                        <a href="#">学习文库</a>
                    </li>
                    <li class="dropdown" id="li-4">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Portfo<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="portfolio-1-col.html">1 Column Portfolio</a>
                            </li>
                            <li>
                                <a href="portfolio-2-col.html">2 Column Portfolio</a>
                            </li>
                            <li>
                                <a href="portfolio-3-col.html">3 Column Portfolio</a>
                            </li>
                            <li>
                                <a href="portfolio-4-col.html">4 Column Portfolio</a>
                            </li>
                            <li>
                                <a href="portfolio-item.html">Single Portfolio Item</a>
                            </li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Blog <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="blog-home-1.html">Blog Home 1</a>
                            </li>
                            <li>
                                <a href="blog-home-2.html">Blog Home 2</a>
                            </li>
                            <li>
                                <a href="blog-post.html">Blog Post</a>
                            </li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Other<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="full-width.html">Full Width Page</a>
                            </li>
                            <li>
                                <a href="sidebar.html">Sidebar Page</a>
                            </li>
                            <li>
                                <a href="faq.html">FAQ</a>
                            </li>
                            <li>
                                <a href="404.html">404</a>
                            </li>
                            <li>
                                <a href="pricing.html">Pricing Table</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>
    