	<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
	
	<div class="header-top">
		<div class="container">
			<div class="row" style="padding-top:10px;">
				<div class="col-md-10" >
					<form class="form-inline" action="">
						<i class="fa fa-fw fa-map-marker"></i>您的位置
						<select class="form-control">
						  <option>浙江工业大学屏峰校区</option>
						  <option>浙江科技学院</option>
						  <option>浙江外国语学院</option>
						  <option>浙江工商大学</option>
						  <option>浙江理工大学</option>
						</select>
					</form>
				</div>
				<div class="col-md-2">
					<span id="qqLoginBtn"></span>
					<script type="text/javascript">
					//调用QC.Login方法，指定btnId参数将按钮绑定在容器节点中
					QC.Login({
					       //btnId：插入按钮的节点id，必选
					       btnId:"qqLoginBtn",    
					       //用户需要确认的scope授权项，可选，默认all
					       scope:"all",
					       //按钮尺寸，可用值[A_XL| A_L| A_M| A_S|  B_M| B_S| C_S]，可选，默认B_S
					       size: "B_M"
					   }, function(reqData, opts){//登录成功
					       //根据返回数据，更换按钮显示状态方法
					       var dom = document.getElementById(opts['btnId']),
					       _logoutTemplate=[
					            //头像
					            '<span><img src="{figureurl}" class="{size_key}"/></span>',
					            //昵称
					            '<span>{nickname}</span>',
					            //退出
					            '<span><a href="javascript:QC.Login.signOut();">退出</a></span>'    
					       ].join("");
					       dom && (dom.innerHTML = QC.String.format(_logoutTemplate, {
					           nickname : QC.String.escHTML(reqData.nickname), //做xss过滤
					           figureurl : reqData.figureurl
					       }));
					   }, function(opts){//注销成功
					         alert('QQ登录 注销成功');
					   }
					);
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
                <a class="navbar-brand" href="index.html"><img src="images/printer_favorite.png" height="80px;" align="top"/></a>
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
    