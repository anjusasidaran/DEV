<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.sql.*"%>
<%
ResultSet rs = (ResultSet)session.getAttribute("deliveryData");
while(rs.next()){
	
	out.println(rs.getString(2));
}
%>