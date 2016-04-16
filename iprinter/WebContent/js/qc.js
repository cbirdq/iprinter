/**
 * qq_connection
 */


function qq_login() {
	QC.Login({
	       btnId:"qqLoginBtn", //btnId：插入按钮的节点id，必选   
	       scope:"all", //用户需要确认的scope授权项，可选，默认all
	       size: "B_M" //按钮尺寸，可用值[A_XL| A_L| A_M| A_S|  B_M| B_S| C_S]，可选，默认B_S
	   }, function(reqData, opts){//登录成功
	       alert("登录成功");
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
	QC.api("get_info", {"oauth_consumer_key": "appid"}).success(function(s) {
		if(QC.Login.check()) { 
			QC.getMe(function(openid, accessToken) {
				alert(openid + " " + accessToken);
			});
		}
	});
	
	
	
	
}