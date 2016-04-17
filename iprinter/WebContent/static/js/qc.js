/**
 * qq_connection
 */


function qq_login() {
	QC.Login({
	       btnId:"qqLoginBtn", //btnId：插入按钮的节点id，必选   
	       scope:"all", //用户需要确认的scope授权项，可选，默认all
	       size: "B_M" //按钮尺寸，可用值[A_XL| A_L| A_M| A_S|  B_M| B_S| C_S]，可选，默认B_S
	   }, 
	   function(reqData, opts) {
		   //登录成功
	       var info = {  
	           name: QC.String.escHTML(reqData.nickname),//获取qq昵称,  
	           gender: reqData.gender,//性别：男|女  
	       };  
	       if(QC.Login.check()){  
	           //QQ成功登陆  
	           QC.Login.getMe(function(openId, accessToken){  
	               info.openId=openId;  
	               info.accessToken=accessToken;  
	               //开始写入数据库  
	               $.ajax({
	            	   url: "",
	            	   method: "post",
	            	   dataType: json,
	            	   data: info,
	            	   success: function() {
	            		   return true;
	            	   }
	               });
	           });  
	       }  
	   }, 
	   function(opts){//注销成功
		   window.location.href="index.jsp";
	   }
	);
}