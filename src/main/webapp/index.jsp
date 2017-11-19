<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.Date"%>
<HTML>
 <HEAD>
  <TITLE> ..:: PA Dashboard Utility ::.. </TITLE>
  <link href="css/bootstrap.css" rel="stylesheet"></link>
	<link href="css/bootstrap-responsive.css" rel="stylesheet"></link>

	<link href="css/datepicker.css" rel="stylesheet"></link>
	<link href="css/toggle-button.css" rel="stylesheet"></link>
	<link href="css/jquery.datetimepicker.css" rel="stylesheet"></link>
	<link href="css/bootstrap.min.css" rel="stylesheet"></link>
	<link href="css/base-admin-3.css" rel="stylesheet"></link>
	<link href="css/font-awesome.css" rel="stylesheet"></link>
	<link href="css/font-awesome.min.css" rel="stylesheet"></link>
	 <link href="css/jquery.countdownTimer.css" rel="stylesheet" ></link>
  <LINK href="css/style.css" type=text/css rel=Stylesheet>
 <style>
	 <!--
		body {
			margin-left: 0px;
			margin-top: 0px;
			margin-right: 0px;
			margin-bottom: 0px;
			background-color: #3b4b54;
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
  <BODY onLoad="initVars()">
  	<script src="js/jquery-2.0.3.js"></script>
	<script src="js/jquery-ui.js"></script>
	<script src="js/formatDatePicker.js"></script>
	<script src="js/jquery.unobtrusive-ajax.js"></script>
	<script src="js/jquery.unobtrusive-ajax.min.js"></script>
	<script src="js/bootstrap.js"></script>
	<script src="js/toggle-button.js"></script>
	<script src="js/jquery.datetimepicker.min.js"></script>
	<script src="js/date.js"></script>
	<script src="js/bootstrap-datepicker.js"></script>
	<script src="js/jquery.datetimepicker.full.min.js"></script>
	<script src="js/bootbox.min.js"></script>
	<script src="js/jquery.countdownTimer.js"></script>
	<script src="js/jquery.countdownTimer.min.js"></script>
	<script src="js/Common.js"></script>

  
  <br>
	<table width="94%" border="0" bgcolor="#3b4b54" cellspacing="0" cellpadding="0" align=center>
	<tr><td>
	
  		<jsp:include page="header.jsp"/>

 	</td></tr>
		<tr >
			<td bgcolor="#3b4b54">
			<div id="left" style="background-color:#3b4b54">
			<jsp:include page="leftNavigation.jsp"/>
			</div>
			</td>
						
						<td align="left" valign="top"   bgcolor="#ffffff" >
						<!--Contentl Satrts here -->
							 <table border="3" cellpadding="0" cellspacing="0" width="100%" bordercolor="#66CCFF">
								 
								 <tr>
									<td colspan=2 style='padding-right:5px;'>
										<table border="0" cellpadding="1" cellspacing="0" width="100%" >
											  <tr>
												  <td  class="m8c" style='vertical-align:top;text-align:justify;'>

												 <table border="0" cellpadding="8" cellspacing="1" width="100%" bgcolor="#FFFFFF" >
												
													 
													<tr>
														<td style="height:400px;vertical-align:top;"><jsp:include page="welcome.jsp"/></td>
														</tr>
														<tr><td>
														
			<div class="modal fade" id="myModal3">
        <div class="modal-dialog">
          
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" >&times;</button>
                   <font color="#3b4b54"><h4 class="modal-title" id="title1">PA Data Load</h4>
               </font> </div>
                <div class="modal-body">
                <div id="divProcessing" class="modal fade">
		<img src="images/Processing.gif">
		 
	</div>
                    <p id="message1">
 <form name="form1" method="post" action="UploadServlet" enctype="multipart/form-data">
        <table align="center" width="55%" style="font-size: 13px">
<tr><td> <font color="#3b4b54">Please Select PA Data File</font></td><td>

<input class="btn-primary" type="file" onChange="check()" id ="files" name="files[]" multiple></div></td>

</tr>


<tr><td></td></tr>
<tr><td></td><td><input class="btn-primary" type="submit" id="btnSubmit"  name="btnSubmit" disabled="true" value="Load Data" onClick="validate()"></td></tr>
<tr><td colspan=2><output id="list"></output><div id="divList"></div></td>
<td></td></tr>
</table>
			
<p style="color:red" id="demo"></p>
						</p>
						</form>
						
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
													</tr>
												</table></td>
											</tr>
											
										</table>
									</td>
								 </tr>
							 
							
							
							 </table>
						<!--Contentl ends here -->
						</td>
						<br>
						<br><br>
					</tr>
				</table>
				
				<tr>
				<td background="images/bottom-strip.gif" height="41" valign="center" class="m8c" align=center style='padding-right:4px;'></td>
		 </tr>
		<!--header ends here -->
	</table>
	<script>
	$("#loadData").click(function () {
        $('#myModal3').modal('show');
     
      //  $('.modal-body').load('upload.jsp');
     
});
	
	function validate(){
		if(document.getElementById('files').files.length==0){
		alert("Please Select PA Data File");
		}
		document.getElementById('btnSubmit').disabled=true;
		$("#fileDiv").addClass("disabledbutton");

		$("#divProcessing").modal('show');
		
		return true;
	}
	function check(){
		document.getElementById('btnSubmit').disabled=false;
		return true;
	}
	</script>
  </BODY>
</HTML>