package com.pa.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pa.dao.DeliveryDAO;
import com.pa.util.DBConnection;

/**
 * Servlet implementation class DeliveryServlet
 */
@WebServlet("/Delivery")
public class DeliveryServlet extends HttpServlet {
    

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
 
        String table ="<html><head>  <LINK href='css/style.css' type=text/css rel=Stylesheet></link><link href='css/font-awesome.css' rel='stylesheet'></link><style>" + 
        		"a:link {color:#FFA500;} </style></head><body><script src='js/Common.js'></script>"
        		
        		+ "<p><a href='index.jsp'>Home</a></p><p><center>PA Delivery Data</center></p><table border='previous'><tr><td>PA Name</td><td>PA Manager</td><td>Account Name</td><td>Account Manager</td><td>Year</td><td>Month</td><td>HC - GD</td>" + 
        		"<td> Rev Target ($M)</td>" + 
        		"<td>Rev-  YTD Actual in Mill </td>" + 
        		"<td> Rev- EAC </td>" + 
        		"<td>GP% Target </td>" + 
        		"<td> GP % YTD</td>" + 
        		"<td> EAC GP%</td>" + 
        		"<td>Q2 Rev Target ($ in Mill)</td>" + 
        		"<td>Q2  Rev Forecast ($ in Mill)</td>" + 
        		"<td>Q2 Rev Actual ($ in m)</td>" + 
        		"<td>Q2 GP % Target</td>" + 
        		"<td>Q2 GP % Actual</td>" + 
        		"<td>Q2 EAC GP</td>" + 
        		"<td>Signings ($ in Mill)</td>" + 
        		"<td>CSAT</td>" + 
        		"<td>CSAT Trend indicator</td>" + 
        		"<td>Lean Target ($ in M)</td>" + 
        		"<td>Lean Savings($)</td>" + 
        		"<td> Lean Savings(EAC) ($)</td>" + 
        		"<td>BandMix</td>" + 
        		"<td>Solutioned Band Mix</td>" + 
        		"<td>Attrition (%)</td>" + 
        		"<td>Check Point Completion (%)</td>" + 
        		"<td>GH (%)</td>" + 
        		"<td>Staffing /Revenue Impact</td>" + 
        		"<td>Count of Projects/Enhancements/New Dev in progress</td>" + 
        		"<td># Ideas in BAG - Inside out</td>" + 
        		"<td>BAG TCV Planned USD Millions</td>" + 
        		"<td>TCV Realized  USD Millions</td>" + 
        		"<td>VLI Implementation Status</td>" + 
        		"<td>CBKI - Automation Using Lime Survey tool</td>" + 
        		"<td>Key  Account Challenges</td>" + 
        		"<td>Mitigation Action Plan</td>" + 
        		"</tr>";
       
        ResultSet rs;
        Connection connection = null;
		try {
			connection = DBConnection.getConnection();    
			rs = DeliveryDAO.getDelivery(connection);
			while(rs.next()){
				table=table+"<tr><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td>"
						+"<td>"+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(7)+"</td><td>"+rs.getString(8)+"</td>"
						+"<td>"+rs.getString(9)+"</td><td>"+rs.getString(10)+"</td><td>"+rs.getString(11)+"</td><td>"+rs.getString(12)+"</td>"
						+"<td>"+rs.getString(13)+"</td><td>"+rs.getString(14)+"</td><td>"+rs.getString(15)+"</td><td>"+rs.getString(16)+"</td>"
						+"<td>"+rs.getString(17)+"</td><td>"+rs.getString(18)+"</td><td>"+rs.getString(19)+"</td><td>"+rs.getString(20)+"</td>"
						+"<td>"+rs.getString(21)+"</td><td>"+rs.getString(22)+"</td><td>"+rs.getString(23)+"</td><td>"+rs.getString(24)+"</td><td>"+rs.getString(25)+"</td>"
						+"<td>"+rs.getString(26)+"</td><td>"+rs.getString(27)+"</td><td>"+rs.getString(28)+"</td><td>"+rs.getString(29)+"</td>"
						+"<td>"+rs.getString(30)+"</td><td>"+rs.getString(31)+"</td><td>"+rs.getString(32)+"</td><td>"+rs.getString(33)+"</td>"
						+"<td>"+rs.getString(34)+"</td><td>"+rs.getString(35)+"</td><td>"+rs.getString(36)+"</td><td>"+rs.getString(37)+"</td>"
						+"<td>"+rs.getString(38)+"</td><td>"+rs.getString(39)+"</td><td>"+rs.getString(40)+"</td></tr>";
			}
			table=table+"</table></body></html>";
			response.getWriter().print(table);
			connection.close();
	        
		} catch (Exception e) {
	
			e.printStackTrace();
		}
		
    }

}
