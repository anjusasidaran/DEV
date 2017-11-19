<!DOCTYPE html>
<html>
<head>
	<title>PA Data Load</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" href="style.css" />
</head>
<body>
  	<script src="js/jquery-2.0.3.js"></script>
  	<script src="js/jquery-ui.js"></script>
  	<script src="js/bootstrap.js"></script>
  	<script src="js/Common.js"></script>
<form name="form1" method="post" action="UploadServlet" enctype="multipart/form-data">
     
      <div class="modal-dialog">
           
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" >&times;</button>
                    <h4 class="modal-title" id="title2">PA Data Load</h4>
                </div>
                <div class="modal-body">
                    
      	
         	 <div class="col-xs-12 col-xs-updated">
	<table align="center" width="50%" style="font-size: 13px">
<tr><td>Please Select PA Data File</td><td>

<input type="file" onChange="go()" id ="files" name="files[]" multiple></div></td>

</tr>


<tr><td></td></tr>
<tr><td></td><td><input class="btn-primary" type="submit" id="btnSubmit"  name="btnSubmit" disabled="true" value="Load Data" onClick="validate()"></td></tr>
<tr><td colspan=2><output id="list"></output><div id="divList"></div></td>
<td><div id="divProcessing" style="display:none">
		<img src="images/Processing.gif">
		 
	</div></td></tr>
</table>
			
<p style="color:red" id="demo"></p>
</div>
   	
						
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn-danger" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
        
        
</form>


<script language="Javascript">
function validate(){
	if(document.getElementById('files').files.length==0){
	alert("Please Select PA Data File");
	}
	document.getElementById('btnSubmit').disabled=true;
	$("#fileDiv").addClass("disabledbutton");

	$("#divProcessing").css("display","block");
	
	return true;
}
function go(){
	document.getElementById('btnSubmit').disabled=false;
	return true;
}
</script>
</body>
</html>
