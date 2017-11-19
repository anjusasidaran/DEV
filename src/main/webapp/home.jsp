 <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.Date"%>
<HTML>
 <HEAD>
      <link href="css/bootstrap.css" rel="stylesheet"></link>
    <link href="css/bootstrap-responsive.css" rel="stylesheet"></link>
    <link href="css/site.css" rel="stylesheet"></link>
    <link href="css/bootstrap.min.css" rel="stylesheet"></link>
    <link href="css/base-admin-3.css" rel="stylesheet"></link>
    <link href="css/font-awesome.css" rel="stylesheet"></link>
    <link href="css/font-awesome.min.css" rel="stylesheet"></link>
  <LINK href="css/style.css" type=text/css rel=Stylesheet>
 <style>
	 <!--
		body {
			margin-left: 0px;
			margin-top: 0px;
			margin-right: 0px;
			margin-bottom: 0px;
			background-image: url(images/background.gif)
		}
		.container 
		{
			background:#66CCFF;
			margin:0 0px;
			FONT-SIZE: 8.5pt;
			COLOR: #4d4d4f;
			FONT-FAMILY: verdana;
		}
.rtop, .rbottom{
display:block;
background:#fff;
}
.rtop *, .rbottom *{
display: block;
height: 1px;
overflow: hidden;
background:#66CCFF;
}
.r1{margin: 0 5px}
.r2{margin: 0 3px}
.r3{margin: 0 2px}
.r4{margin: 0 1px; height: 2px}
	-->
</style>
<script>

</script>
  </HEAD>
  <BODY>

  	<script src="js/jquery-2.0.3.js"></script>
  	<script src="js/jquery-ui.js"></script>
  	<script src="js/bootstrap.js"></script>
  	<script src="js/Common.js"></script>
<a id="loadData" href="#">Load Data</a>
												
			<div class="modal fade" id="myModal3">
				<div class="modal-dialog">

					<div class="modal-content">

						<div class="modal-body"></div>
					</div>
				</div>
			</div>


	
											
	<script>
	$("#loadData").click(function () {
        $('#myModal3').modal('show');
     
        $('.modal-body').load('upload.jsp');
     
});

	</script>
  </BODY>
