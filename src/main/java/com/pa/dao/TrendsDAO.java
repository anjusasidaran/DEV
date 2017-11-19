package com.pa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import com.pa.model.DeliveryData;
import com.pa.model.TrendsData;
import com.pa.util.DBConnection;

public class TrendsDAO {

	public static void insertData( String Trends_PA_Name,ArrayList <TrendsData> trendsDataList ) throws Exception {
		
  	    PreparedStatement statement = null; 
  		Connection connection = null; 
  	   
        	  try { 
        			connection = DBConnection.getConnection(); 
        			
        		      for(TrendsData trendsData:trendsDataList) {
        		         		    	  
        		    	String sqlCheck = "select count(*) from PA_TRENDS where PA_NAME=?  and MONTH=?";
        		    	statement = connection.prepareStatement(sqlCheck);
        		    	statement.setString(1, Trends_PA_Name);
        		 	    statement.setString(2, trendsData.getMONTH());
       		     	    
        		    	ResultSet rs = statement.executeQuery();
        		    	rs.next();
        		    	int count = rs.getInt(1);
        		    	//System.out.println("count:"+count);
        		    	if (count > 0) {
        		    		String sqlDelete = "delete from PA_TRENDS where  PA_NAME=?  and MONTH=?";
            		    	statement = connection.prepareStatement(sqlDelete);
            		    	statement.setString(1, Trends_PA_Name);
            		 	    statement.setString(2, trendsData.getMONTH());
           		     	   int delCount = statement.executeUpdate();
           		     	  // System.out.println("delCount:"+delCount);
        		    	}
        		    	String sql="insert into PA_TRENDS (PA_NAME,MONTH,PREVIOUS_YEAR,CURRENT_YEAR,PREVIOUS_HC_TOTAL,CURRENT_HC_TOTAL,PREVIOUS_YTD_REVENUE,CURRENT_YTD_REVENUE,PREVIOUS_YTD_GP_PER,CURRENT_YTD_GP_PER,PREVIOUS_SINGNINGS,CURRENT_SIGNINGS,PREVIOUS_CSAT,CURRENT_CSAT,PREVIOUS_BAND_MIX,CURRENT_BAND_MIX,PREVIOUS_ATTRITION,CURRENT_ATTRITION,PREVIOUS_GH_PER,CURRENT_GH_PER) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        		    	
        		     	   statement = connection.prepareStatement(sql);
        		     	 //  System.out.println("statement:"+sql);
        		     	   statement.setString(1, Trends_PA_Name);
       		     	   statement.setString(2, trendsData.getMONTH());
       		     	   statement.setString(3, trendsData.getPREVIOUS_YEAR());
       		     	   statement.setString(4, trendsData.getCURRENT_YEAR());
       		     	   statement.setString(5, trendsData.getPREVIOUS_HC_TOTAL());
       		     	   	//statement.setString(6, trendsData.getHC_GD());
       		     		statement.setString(6, trendsData.getCURRENT_HC_TOTAL());
       		     		statement.setString(7, trendsData.getPREVIOUS_YTD_REVENUE());
			       		statement.setString(8, trendsData.getCURRENT_YTD_REVENUE());
			       		statement.setString(9, trendsData.getPREVIOUS_YTD_GP_PER());
			       		statement.setString(10, trendsData.getCURRENT_YTD_GP_PER());
			       		statement.setString(11, trendsData.getPREVIOUS_SINGNINGS());
			       		statement.setString(12, trendsData.getCURRENT_SIGNINGS());
			       		statement.setString(13, trendsData.getPREVIOUS_CSAT());
			       		statement.setString(14, trendsData.getCURRENT_CSAT());
			       		statement.setString(15, trendsData.getPREVIOUS_BAND_MIX());
			       		statement.setString(16, trendsData.getCURRENT_BAND_MIX());
			       		statement.setString(17, trendsData.getPREVIOUS_ATTRITION());
			       		statement.setString(18, trendsData.getCURRENT_ATTRITION());
			       		statement.setString(19, trendsData.getPREVIOUS_GH_PER());
			       		
			       		statement.setString(20, trendsData.getCURRENT_GH_PER());
       		     	   
				     	   int status = statement.executeUpdate();
				     	  
        		      }
        		 
  		  //HC,HC_GD,CURRENT_REV_TARGET,REV_YTD_ACTUAL_IN_MILL,CURRENT_REV_EAC,CURRENT_GP_TARGET,CURRENT_GP_YTD,CURRENT_EAC_GP,REV_TARGET_IN_MILL,REV_FORECAST_IN_MILL,REV_ACTUAL_IN_M,GP_PER_TARGET,GP_PER_ACTUAL,SIGNINGS,CSAT,CSAT_TREND_IND,LEAN_TARGET,LEAN_SAVINGS,LEAN_SAVINGS_EAC,BAND_MIX,SOLUTIONED_BAND_MIX,ATTRITION,CHECK_PO_COMP,GH_PER,STAFFING_REVENUE_IMPACT,COUNT_PROJ_ENHANCE_NEWDEV,IDEA_BAG_INSIDE_OUT,BAG_TCV_PLAND_USD,TCV_REALIZED_USD,VLI_IMPL_STATUS,CBKI_AUTO_LIME_SURVEY,KEY_ACCOUNT_CHALLENGE,MITIGATION_ACTION_PLAN) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"); 
  	  
  	
  	  } 
        	  
        	  catch(Exception e) {
        		  System.out.println("Error in try:"+e);
        				  e.printStackTrace();
        	  }
        	  finally {
        		  connection.close();
        	  }
		
	}
	public static  ResultSet getTrends(Connection connection) throws SQLException {
		 PreparedStatement statement = null; 
			
			ResultSet rs = null;
	     	  try { 
	     		  		connection = DBConnection.getConnection();    
	     		  		String sqlCheck = "select * from PA_TRENDS";
	     		    	statement = connection.prepareStatement(sqlCheck);
	     		    	rs = statement.executeQuery();
	     		    	
	     		     } 
	            	  
	            	  catch(Exception e) {
	            		  System.out.println("Error in try:"+e);
	            				  e.printStackTrace();
	            	  }
	            	  
	     	  return rs;
	    		
	}
}
