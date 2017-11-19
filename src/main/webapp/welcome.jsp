<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.sql.*"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<font color="red"><p>Welcome,</p>
<p>
<%
String message = (String)request.getAttribute("message");
if(message!=null)
{
out.println(message);
}

%>
</p>
</font>
</body>
</html>