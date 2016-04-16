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
					       btnId:"qqLoginBtn", //btnId：插入按钮的节点id，必选   
					       scope:"all", //用户需要确认的scope授权项，可选，默认all
					       size: "B_M" //按钮尺寸，可用值[A_XL| A_L| A_M| A_S|  B_M| B_S| C_S]，可选，默认B_S
					   });
					
					</script>
					
					
<script type="text/javascript">  
    //从页面收集OpenAPI必要的参数。get_user_info不需要输入参数，因此paras中没有参数  
    var paras = {};  
  
    //用JS SDK调用OpenAPI  
    QC.api("get_user_info", paras)  
    //指定接口访问成功的接收函数，s为成功返回Response对象  
    .success(function(s) {  
        //成功回调，通过s.data获取OpenAPI的返回数据  
        alert("获取用户信息成功！当前用户昵称为：" + s.data.nickname);  
    })  
    //指定接口访问失败的接收函数，f为失败返回Response对象  
    .error(function(f) {  
        //失败回调  
        alert("获取用户信息失败！");  
    })  
    //指定接口完成请求后的接收函数，c为完成请求返回Response对象  
    .complete(function(c) {  
        //完成请求回调  
        alert("获取用户信息完成！");  
    });  
</script>  
  
<script type="text/javascript">  
    if (QC.Login.check()) {//如果已登录  
        QC.Login.getMe(function(openId, accessToken) {  
            alert(["当前登录用户的", "openId为：" + openId, "accessToken为：" + accessToken].join("\n"));  
        });  
        //这里可以调用自己的保存接口  
        //...  
    }  
  
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
    