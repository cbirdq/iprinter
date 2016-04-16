	<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
		<hr>

        <!-- Footer -->
        <footer>
            <div class="row">
                <div class="col-lg-12">
                    <p>Copyright &copy; Your Website 2014</p>
                </div>
            </div>
        </footer>

    </div>
    <!-- /.container -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

    <!-- Script to Activate the Carousel -->
    <script>
    $('.carousel').carousel({
        interval: 5000 //changes the speed
    })
    
    <!-- 更新导航栏的激活状态 -->
    function activeNavbar(curUrl) {
    	var actived = 0;
    	$("#navbar-ul-lists > li").each(function() {
	    	if($(this).children(":first").attr("href")==curUrl) {
	    		$(this).addClass("active");
	    		actived = 1;
	    	} 
	    });
	    
	    if(actived == 0) {
	    	if(curUrl == "print1.jsp" || curUrl == "print1.jsp" 
	    		|| curUrl == "print3.jsp" || curUrl == "print4.jsp") {
	    		$("#li-2").addClass("active");
	    	}
	    }
	    
    }
    var urlSplits = window.location.href.split("/");
    var relUrl = urlSplits[urlSplits.length-1];
    activeNavbar(relUrl);
    
    
    
    </script>