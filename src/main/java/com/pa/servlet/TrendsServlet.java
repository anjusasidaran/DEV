package com.pa.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pa.dao.TrendsDAO;
import com.pa.util.DBConnection;

/**
 * Servlet implementation class TrendsServlet
 */
@WebServlet("/Trends")
public class TrendsServlet extends HttpServlet {
    

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
 
        String table ="<html><head>  <LINK href='css/style.css' type=text/css rel=Stylesheet></link><link href='css/font-awesome.css' rel='stylesheet'></link><style>" + 
        		"a:link {color:#FFA500;} </style></head><body><script src='js/Common.js'></script>"
        		
        		+ "<p><a href='index.jsp'>Home</a></p><p><center>PA Trends Data</center></p><table border='previous'><tr><td>PA Name</td><td>Month</td><td>Previous Year</td><td>Current Year</td><td>HC previous</td><td>HC current</td><td>YTD Revenue previous</td><td>YTD Revenue current</td>"
        		+"<td>YTD GP% previous</td><td>YTD GP% current</td><td>Signings previous</td><td>Signings current</td>"
        		+"<td>CSAT previous</td><td>CSAT current</td><td>BandMix previous</td><td>BandMix current</td>"
        		+"<td>Attrition (%) previous</td><td>Attrition (%) current</td><td>GH (%) previous</td><td>GH (%) current</td>"
        		+ "</tr>";
       
        ResultSet rs;
        Connection connection = null;
		try {
			connection = DBConnection.getConnection();   
			rs = TrendsDAO.getTrends(connection);
			while(rs.next()){
				table=table+"<tr><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td>"
						+"<td>"+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(7)+"</td><td>"+rs.getString(8)+"</td>"
						+"<td>"+rs.getString(9)+"</td><td>"+rs.getString(10)+"</td><td>"+rs.getString(11)+"</td><td>"+rs.getString(12)+"</td>"
						+"<td>"+rs.getString(13)+"</td><td>"+rs.getString(14)+"</td><td>"+rs.getString(15)+"</td><td>"+rs.getString(16)+"</td>"
						+"<td>"+rs.getString(17)+"</td><td>"+rs.getString(18)+"</td><td>"+rs.getString(19)+"</td><td>"+rs.getString(20)+"</td>"
						+"<td>"+rs.getString(21)+"</td></tr>";
			}
			
			table=table+"</table></body></html>";
			response.getWriter().print(table);
			connection.close();
	        
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
    }

}
