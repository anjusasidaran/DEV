package com.pa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.pa.model.DeliveryData;
import com.pa.util.DBConnection;

public class DeliveryDAO {
	//static Logger logger = Logger.getLogger(DeliveryDAO.class);
	
	/*public static void printSqlStatement(PreparedStatement preparedStatement, String sql) throws SQLException{
        String[] sqlArrya= new String[preparedStatement.getParameterMetaData().getParameterCount()];
        try {
               Pattern pattern = Pattern.compile("\\?");
               Matcher matcher = pattern.matcher(sql);
               StringBuffer sb = new StringBuffer();
               int indx = 1;  // Parameter begin with index 1
               while (matcher.find()) {
             matcher.appendReplacement(sb,String.valueOf(sqlArrya[indx]));
               }
               matcher.appendTail(sb);
              System.err.println("Executing Query [" + sb.toString() + "] with Database[" + "] ...");
               } catch (Exception ex) {
                   System.err.println("Executing Query [" + sql + "] with Database[" +  "] ...");
            }

    }*/


public static void insertData(String PA_Manager,ArrayList <DeliveryData> deliveryDataList ) throws Exception {
		
  	    PreparedStatement statement = null; 
  		Connection connection = null; 
  	   
        	  try { 
        			connection = DBConnection.getConnection(); 
        		  
        		      for(DeliveryData deliveryData:deliveryDataList) {
        		    	 // System.out.println("PA_Account = " + deliveryData.getPA_Account() );
        		    	 // System.out.println("HC = " + deliveryData.getHC_GD() ); 
        		    	  
        		    	String sqlCheck = "select count(*) from PA_DELIVERY where PA_ACCOUNT_NAME=? and YEAR=? and MONTH=?";
        		    	statement = connection.prepareStatement(sqlCheck);
        		    	statement.setString(1, deliveryData.getPA_Account());
        		 	    statement.setString(2, deliveryData.getYear());
       		     	    statement.setString(3, deliveryData.getMonth());
        		    	ResultSet rs = statement.executeQuery();
        		    	rs.next();
        		    	int count = rs.getInt(1);
        		    	//System.out.println("count:"+count);
        		    	if (count > 0) {
        		    		String sqlDelete = "delete from PA_DELIVERY where PA_ACCOUNT_NAME=? and YEAR=? and MONTH=?";
            		    	statement = connection.prepareStatement(sqlDelete);
            		    	statement.setString(1, deliveryData.getPA_Account());
            		 	    statement.setString(2, deliveryData.getYear());
           		     	    statement.setString(3, deliveryData.getMonth());
           		     	   int delCount = statement.executeUpdate();
           		     	   //System.out.println("delCount:"+delCount);
        		    	}
        		    	String sql="insert into PA_DELIVERY (PA_NAME,PA_MANAGER_NAME,PA_ACCOUNT_NAME,PA_ACCOUNT_MANAGER_NAME,YEAR,MONTH,HC_GD,CURRENT_REV_TARGET,REV_YTD_ACTUAL_IN_MILL,CURRENT_REV_EAC,CURRENT_GP_TARGET,CURRENT_GP_YTD,CURRENT_EAC_GP,REV_TARGET_IN_MILL,REV_FORECAST_IN_MILL,REV_ACTUAL_IN_M,GP_PER_TARGET,GP_PER_ACTUAL,EAC_GP,SIGNINGS,CSAT,CSAT_TREND_IND,LEAN_TARGET,LEAN_SAVINGS,LEAN_SAVINGS_EAC,BAND_MIX,SOLUTIONED_BAND_MIX,ATTRITION,CHECK_POINT_COMP,GH_PER,STAFFING_REVENUE_IMPACT,COUNT_PROJ_ENHANCE_NEWDEV,IDEA_BAG_INSIDE_OUT,BAG_TCV_PLAND_USD,TCV_REALIZED_USD,VLI_IMPL_STATUS,CBKI_AUTO_LIME_SURVEY,KEY_ACCOUNT_CHALLENGE,MITIGATION_ACTION_PLAN) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        		    	//String sql="insert into PA_DELIVERY1 (PA_NAME,PA_MANAGER_NAME,PA_ACCOUNT_NAME,PA_ACCOUNT_MANAGER_NAME,YEAR,MONTH,HC,HC_GD,CURRENT_REV_TARGET,REV_YTD_ACTUAL_IN_MILL,CURRENT_REV_EAC,CURRENT_GP_TARGET,CURRENT_GP_YTD,CURRENT_EAC_GP,REV_TARGET_IN_MILL,REV_FORECAST_IN_MILL,REV_ACTUAL_IN_M,GP_PER_TARGET,GP_PER_ACTUAL,SIGNINGS,CSAT,CSAT_TREND_IND,LEAN_TARGET,LEAN_SAVINGS,LEAN_SAVINGS_EAC,BAND_MIX,SOLUTIONED_BAND_MIX,ATTRITION,CHECK_POINT_COMP,GH_PER,STAFFING_REVENUE_IMPACT,COUNT_PROJ_ENHANCE_NEWDEV,IDEA_BAG_INSIDE_OUT) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        		    	 
        		    			
        		     	   statement = connection.prepareStatement(sql);
        		     	 // System.out.println("statement:"+sql);
        		     	   statement.setString(1, deliveryData.getPA_Name());
       		     	   statement.setString(2, PA_Manager);
       		     	   statement.setString(3, deliveryData.getPA_Account());
       		     	   statement.setString(4, deliveryData.getPA_Account_Manager());
       		     	   statement.setString(5, deliveryData.getYear());
       		     	   statement.setString(6, deliveryData.getMonth());
       		     	   //System.out.println("Month:"+deliveryData.getMonth());
	       		     	  // statement.setString(7, deliveryData.getHC());
       		     	   statement.setString(7, deliveryData.getHC_GD());
    		     	   statement.setString(8, deliveryData.getCURRENT_REV_TARGET());
    		     	   statement.setString(9, deliveryData.getREV_YTD_ACTUAL_IN_MILL());
    		     	   statement.setString(10, deliveryData.getCURRENT_REV_EAC());
	 		     	   statement.setString(11, deliveryData.getCURRENT_GP_TARGET());
	 		     	   statement.setString(12, deliveryData.getCURRENT_GP_YTD());
	 		     	   statement.setString(13, deliveryData.getCURRENT_EAC_GP());
	 		     	   statement.setString(14, deliveryData.getREV_TARGET_IN_MILL());
	 		     	   statement.setString(15, deliveryData.getREV_FORECAST_IN_MILL());
			     	   statement.setString(16, deliveryData.getREV_ACTUAL_IN_M());
			     	   statement.setString(17, deliveryData.getGP_PER_TARGET());
			     	   statement.setString(18, deliveryData.getGP_PER_ACTUAL());
			     	   statement.setString(19, deliveryData.getEAC_GP());
			     	   statement.setString(20, deliveryData.getSIGNINGS());
			     	   statement.setString(21, deliveryData.getCSAT());
			     	   statement.setString(22, deliveryData.getCSAT_TREND_IND());
			     	   statement.setString(23, deliveryData.getLEAN_TARGET());
			     	   statement.setString(24, deliveryData.getLEAN_SAVINGS());
			     	   statement.setString(25, deliveryData.getLEAN_SAVINGS_EAC());
			     	   statement.setString(26, deliveryData.getBAND_MIX());
			     	   statement.setString(27, deliveryData.getSOLUTIONED_BAND_MIX());
			     	   statement.setString(28, deliveryData.getATTRITION());
			     	   statement.setString(29, deliveryData.getCHECK_POINT_COMP());
			     	   statement.setString(30, deliveryData.getGH_PER());
			     	   statement.setString(31, deliveryData.getSTAFFING_REVENUE_IMPACT());
			     	   statement.setString(32, deliveryData.getCOUNT_PROJ_ENHANCE_NEWDEV());
			     	   statement.setString(33, deliveryData.getIDEA_BAG_INSIDE_OUT());
			     	  //System.out.println("deliveryData.getIDEA_BAG_INSIDE_OUT():"+deliveryData.getIDEA_BAG_INSIDE_OUT());
			     	   statement.setString(34, deliveryData.getBAG_TCV_PLAND_USD());
			     	   statement.setString(35, deliveryData.getTCV_REALIZED_USD());
			     	   statement.setString(36, deliveryData.getVLI_IMPL_STATUS());
			     	   statement.setString(37, deliveryData.getCBKI_AUTO_LIME_SURVEY());
			     	   statement.setString(38, deliveryData.getKEY_ACCOUNT_CHALLENGE());
			     	   statement.setString(39, deliveryData.getMITIGATION_ACTION_PLAN());
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

public static  ResultSet getDelivery(Connection connection ) throws SQLException {
	 PreparedStatement statement = null; 
		 
		ResultSet rs = null;
    	  try { 
    		  		
    		  		String sqlCheck = "select * from PA_DELIVERY";
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
