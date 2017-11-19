package com.pa.util;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.pa.dao.DeliveryDAO;
import com.pa.dao.TrendsDAO;
import com.pa.model.DeliveryData;
import com.pa.model.TrendsData;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class ReadExcel {

	static LinkedHashMap<String,ArrayList <DeliveryData>> deliveryDataMap = null;
	static ArrayList <DeliveryData> deliveryDataList = null;
	static ArrayList <TrendsData> trendsDataList = null;
	  
    public static  void uploadData(String fileName) throws Exception {
        File f = new File(fileName);
      
        String PA_Manager = null;
        String PA_Name = null;
        String PA_Name1 = null;
        
        
      
        try {
        
            Workbook wb=Workbook.getWorkbook(f);
            
            int Num = wb.getNumberOfSheets();
            String month = null;
            String year = null;
            for (int i = 0; i < Num; i++) {
            	
            	  int blankCount = 0; 
            
                Sheet s = wb.getSheet(i);
                String sheetName = s.getName();
                System.out.println("sheetName:"+sheetName);
                
                int row = s.getRows();
                int col = s.getColumns();
                if(sheetName.contains("PA Delivery")) {
                	 month = (sheetName.split("-")[1]).trim().split(" ")[0];
                    year = (sheetName.split("-")[1]).trim().split(" ")[1];
                    deliveryDataMap = new LinkedHashMap<String,ArrayList <DeliveryData>>();
                for(int i1=0; i1<row;i1++) {
                 
                	DeliveryData deliveryData = new DeliveryData();	
                	String PA_Account = null;
                	String PA_Account_Manager = null;
                	String 	HC =null;
                    String 	HC_GD =null;
                    String 	CURRENT_REV_TARGET =null;
                    String 	REV_YTD_ACTUAL_IN_MILL =null;
                    String 	CURRENT_REV_EAC =null;
                    String 	CURRENT_GP_TARGET =null;
                    String 	CURRENT_GP_YTD =null;
                    String 	CURRENT_EAC_GP =null;
                    String 	REV_TARGET_IN_MILL =null;
                    String 	REV_FORECAST_IN_MILL =null;
                    String 	REV_ACTUAL_IN_M =null;
                    String 	GP_PER_TARGET =null;
                    String 	GP_PER_ACTUAL =null;
                    String 	EAC_GP =null;
                    String 	SIGNINGS =null;
                    String 	CSAT =null;
                    String 	CSAT_TREND_IND =null;
                    String 	LEAN_TARGET = null;
                    String 	LEAN_SAVINGS =null;
                    String 	LEAN_SAVINGS_EAC =null;
                    String 	BAND_MIX =null;
                    String 	SOLUTIONED_BAND_MIX =null;
                    String 	ATTRITION =null;
                    String 	CHECK_POINT_COMP =null;
                    String 	GH_PER =null;
                    String 	STAFFING_REVENUE_IMPACT =null;
                    String 	COUNT_PROJ_ENHANCE_NEWDEV =null;
                    String 	IDEA_BAG_INSIDE_OUT =null;
                    String 	BAG_TCV_PLAND_USD =null;
                    String 	TCV_REALIZED_USD =null;
                    String 	VLI_IMPL_STATUS =null;
                    String 	CBKI_AUTO_LIME_SURVEY =null;
                    String 	KEY_ACCOUNT_CHALLENGE =null;
                    String 	MITIGATION_ACTION_PLAN =null;
                	if(blankCount >=3) {
                		break;
                	}
                	 boolean status =false;
                    for(int j=0;j<col;j++) {
                    	Cell c;
                    	if(j==0) {
                    		
                    		 c =s.getCell(j, i1);
                    		 PA_Name = c.getContents().trim().trim();
                    		 if(!PA_Name.isEmpty()) {
                    			 status = true; 
                    			//System.out.println("PA_Name:"+PA_Name);
                    		 }
                    		
                    	}
                      
                       if(j==0) {
                    	   c =s.getCell(j, i1); 
                    	   PA_Account = c.getContents().trim();
                    	   PA_Account = PA_Account.replaceAll("\\r|\\n", "");
                    	   if(!(c.getContents().trim().contains("Account"))){
                    	   if(!(PA_Account.isEmpty())) {
                          // System.out.println("PA_Account:"+PA_Account); 
                    	   }
                    	   }
                       }
                       if(j==1) {
                    	   c =s.getCell(j, i1); 
                    	   if( c.getContents().trim().isEmpty() && !(PA_Name.isEmpty())) {
                    		   blankCount++;
                    		  // System.out.println("blankCount:"+blankCount);
                    		deliveryDataList = new ArrayList <DeliveryData>();
                    		PA_Name1=PA_Name;
                    		PA_Name1 = PA_Name1.replaceAll("\\r|\\n", "");
                    		
                    		
                    		i1++;
                    	   }
                    	   if(!( c.getContents().trim().isEmpty())) {
                    		   if(!(c.getContents().trim().contains("Account"))){
                    		   PA_Account_Manager =  c.getContents().trim();
                    		   PA_Account_Manager = PA_Account_Manager.replaceAll("\\r|\\n", "");
                    		// System.out.println("PA_Account_Manager:"+PA_Account_Manager);
                    		  
                    		   }
                    	   }
                    	   if(PA_Name.isEmpty() ) {
                    		   c =s.getCell(1, (i1-1));
                    		   PA_Manager = c.getContents().trim();
                    		   PA_Manager = PA_Manager.replaceAll("\\r|\\n", "");
                    		   if(!(PA_Manager.isEmpty()) && PA_Manager!=null && blankCount<=2)
                    			//System.out.println("PA Manager Name:"+PA_Manager);
                    		   // System.out.println("deliveryDataList:"+deliveryDataList.size());
                    		  //  deliveryDataMap.put(PA_Name1+","+PA_Manager, deliveryDataList);
                    		   DeliveryDAO.insertData(PA_Manager,deliveryDataList);
                    		   if(blankCount==2) {
                    			   blankCount++;
                    		   }
                    	   }
                    	  
                       }
                      
                       /*if(j==2) {
                    	   c =s.getCell(j, i1); 
                    	  if(!(c.getContents().trim().contains("HC"))){
                    		  HC = c.getContents().trim();
                    		  HC = HC.replaceAll("\\r|\\n", "");
                    		  System.out.println("HC:"+HC);
                    	  }
                    	   
                       }
                                */              
                 		   if(j==2) {
                           c =s.getCell(j, i1); 
                           if(!(c.getContents().trim().contains("HC"))){
                           HC_GD = c.getContents().trim();
                           HC_GD = HC_GD.replaceAll("\\r|\\n", "");
                           //System.out.println("HC_GD:"+HC_GD);
                           }
                            }
                 		   if(j==3) {
                           c =s.getCell(j, i1); 
                           if(!(c.getContents().trim().contains("Rev"))){
                           CURRENT_REV_TARGET = c.getContents().trim();
                           CURRENT_REV_TARGET = CURRENT_REV_TARGET.replaceAll("\\r|\\n", "");
                           }
                            }
                 		   if(j==4) {
                           c =s.getCell(j, i1); 
                           if(!(c.getContents().trim().contains("Rev"))){
                           REV_YTD_ACTUAL_IN_MILL = c.getContents().trim();
                           REV_YTD_ACTUAL_IN_MILL = REV_YTD_ACTUAL_IN_MILL.replaceAll("\\r|\\n", "");
                           }
                            }
                 		   if(j==5) {
                           c =s.getCell(j, i1); 
                           if(!(c.getContents().trim().contains("Rev"))){
                           CURRENT_REV_EAC = c.getContents().trim();
                           CURRENT_REV_EAC = CURRENT_REV_EAC.replaceAll("\\r|\\n", "");
                           }
                            }
                 		   if(j==6) {
                           c =s.getCell(j, i1); 
                           if(!(c.getContents().trim().contains("Target"))){
                           CURRENT_GP_TARGET = c.getContents().trim();
                           CURRENT_GP_TARGET = CURRENT_GP_TARGET.replaceAll("\\r|\\n", "");
                           }
                            }
                 		   if(j==7) {
                           c =s.getCell(j, i1); 
                           if(!(c.getContents().trim().contains("YTD"))){
                           CURRENT_GP_YTD = c.getContents().trim();
                           CURRENT_GP_YTD = CURRENT_GP_YTD.replaceAll("\\r|\\n", "");
                           }
                            }
                 		   if(j==8) {
                           c =s.getCell(j, i1); 
                           if(!(c.getContents().trim().contains("EAC"))){
                           CURRENT_EAC_GP = c.getContents().trim();
                           CURRENT_EAC_GP = CURRENT_EAC_GP.replaceAll("\\r|\\n", "");
                           }
                            }
                 		   if(j==9) {
                           c =s.getCell(j, i1); 
                           if(!(c.getContents().trim().contains("Rev"))){
                           REV_TARGET_IN_MILL = c.getContents().trim();
                           REV_TARGET_IN_MILL = REV_TARGET_IN_MILL.replaceAll("\\r|\\n", "");
                           }
                            }
                 		   if(j==10) {
                           c =s.getCell(j, i1); 
                           if(!(c.getContents().trim().contains("Rev"))){
                           REV_FORECAST_IN_MILL = c.getContents().trim();
                           REV_FORECAST_IN_MILL = REV_FORECAST_IN_MILL.replaceAll("\\r|\\n", "");
                           }
                            }
                 		   if(j==11) {
                           c =s.getCell(j, i1); 
                           if(!(c.getContents().trim().contains("GP"))){
                          // REV_ACTUAL_IN_M 
                        	   REV_ACTUAL_IN_M= c.getContents().trim();
                        	   REV_ACTUAL_IN_M = REV_ACTUAL_IN_M.replaceAll("\\r|\\n", "");
                           }
                            }
                 		   if(j==12) {
                           c =s.getCell(j, i1); 
                           if(!(c.getContents().trim().contains("GP"))){
                        	   GP_PER_TARGET = c.getContents().trim();
                        	   GP_PER_TARGET = GP_PER_TARGET.replaceAll("\\r|\\n", "");
                           }
                 		   }
                           if(j==13) {
                               c =s.getCell(j, i1); 
                               if(!(c.getContents().trim().contains("GP"))){
                            	   GP_PER_ACTUAL = c.getContents().trim();
                            	   GP_PER_ACTUAL = GP_PER_ACTUAL.replaceAll("\\r|\\n", "");
                               }
                            }
                           if(j==14) {
                               c =s.getCell(j, i1); 
                               if(!(c.getContents().trim().contains("GP"))){
                            	   EAC_GP = c.getContents().trim();
                            	   EAC_GP = EAC_GP.replaceAll("\\r|\\n", "");
                               }
                            }
                 		   if(j==15) {
                           c =s.getCell(j, i1); 
                           if(!(c.getContents().trim().contains("Signings"))){
                        	   SIGNINGS = c.getContents().trim();
                        	   SIGNINGS = SIGNINGS.replaceAll("\\r|\\n", "");
                           }
                            }
                 		   if(j==16) {
                           c =s.getCell(j, i1); 
                           if(!(c.getContents().trim().contains("CSAT"))){
                        	   CSAT = c.getContents().trim();
                        	   CSAT = CSAT.replaceAll("\\r|\\n", "");
                        	   //System.out.println("CSAT:"+CSAT);
                           }
                            }
                 		   if(j==17) {
                           c =s.getCell(j, i1); 
                           if(!(c.getContents().trim().contains("CSAT"))){
                        	   CSAT_TREND_IND = c.getContents().trim();
                        	   CSAT_TREND_IND = CSAT_TREND_IND.replaceAll("\\r|\\n", "");
                           }
                            }
                 		   if(j==18) {
                           c =s.getCell(j, i1);
                           if(!(c.getContents().trim().contains("Lean"))){
                        	   LEAN_TARGET = c.getContents().trim();
                        	   LEAN_TARGET = LEAN_TARGET.replaceAll("\\r|\\n", "");
                           }
                            }
                 		   if(j==19) {
                           c =s.getCell(j, i1); 
                           if(!(c.getContents().trim().contains("Lean"))){
                        	   LEAN_SAVINGS = c.getContents().trim();
                        	   LEAN_SAVINGS = LEAN_SAVINGS.replaceAll("\\r|\\n", "");
                           }
                            }
                 		   if(j==20) {
                           c =s.getCell(j, i1); 
                           if(!(c.getContents().trim().contains("Lean"))){
                        	   LEAN_SAVINGS_EAC = c.getContents().trim();
                        	   LEAN_SAVINGS_EAC = LEAN_SAVINGS_EAC.replaceAll("\\r|\\n", "");
                           }
                            }
                 		   if(j==21) {
                           c =s.getCell(j, i1); 
                           if(!(c.getContents().trim().contains("Band"))){
                        	   BAND_MIX = c.getContents().trim();
                        	   BAND_MIX = BAND_MIX.replaceAll("\\r|\\n", "");
                           }
                            }
                 		   if(j==22) {
                           c =s.getCell(j, i1); 
                           if(!(c.getContents().trim().contains("Band"))){
                        	   SOLUTIONED_BAND_MIX = c.getContents().trim();
                        	   SOLUTIONED_BAND_MIX = SOLUTIONED_BAND_MIX.replaceAll("\\r|\\n", "");
                           }
                            }
                 		   if(j==23) {
                           c =s.getCell(j, i1); 
                           if(!(c.getContents().trim().contains("Attrition"))){
                        	   ATTRITION = c.getContents().trim();
                        	   ATTRITION = ATTRITION.replaceAll("\\r|\\n", "");
                           }
                            }
                 		   if(j==24) {
                           c =s.getCell(j, i1); 
                           if(!(c.getContents().trim().contains("Check"))){
                        	   CHECK_POINT_COMP = c.getContents().trim();
                        	   CHECK_POINT_COMP = CHECK_POINT_COMP.replaceAll("\\r|\\n", "");
                           }
                            }
                 		   if(j==25) {
                           c =s.getCell(j, i1); 
                           if(!(c.getContents().trim().contains("GH"))){
                        	   GH_PER = c.getContents().trim();
                        	   GH_PER = GH_PER.replaceAll("\\r|\\n", "");
                           }
                            }
                 		   if(j==26) {
                           c =s.getCell(j, i1); 
                           if(!(c.getContents().trim().contains("Staffing"))){
                        	   STAFFING_REVENUE_IMPACT = c.getContents().trim();
                        	   STAFFING_REVENUE_IMPACT = STAFFING_REVENUE_IMPACT.replaceAll("\\r|\\n", "");
                           }
                            }
                 		   if(j==27) {
                           c =s.getCell(j, i1); 
                           if(!(c.getContents().trim().contains("Count"))){
                        	   COUNT_PROJ_ENHANCE_NEWDEV = c.getContents().trim();
                        	   COUNT_PROJ_ENHANCE_NEWDEV = COUNT_PROJ_ENHANCE_NEWDEV.replaceAll("\\r|\\n", "");
                           }
                            }
                 		   if(j==28) {
                           c =s.getCell(j, i1); 
                           if(!(c.getContents().trim().contains("BAG"))){
                        	   IDEA_BAG_INSIDE_OUT = c.getContents().trim();
                        	   IDEA_BAG_INSIDE_OUT = IDEA_BAG_INSIDE_OUT.replaceAll("\\r|\\n", "");
                           }
                            }if(j==29) {
                           c =s.getCell(j, i1); 
                           if(!(c.getContents().trim().contains("TCV"))){
                        	   BAG_TCV_PLAND_USD = c.getContents().trim();
                        	   BAG_TCV_PLAND_USD = BAG_TCV_PLAND_USD.replaceAll("\\r|\\n", "");
                           }
                            }
                 		   if(j==30) {
                           c =s.getCell(j, i1); 
                           if(!(c.getContents().trim().contains("TCV"))){
                        	   TCV_REALIZED_USD = c.getContents().trim();
                        	   TCV_REALIZED_USD = TCV_REALIZED_USD.replaceAll("\\r|\\n", "");
                           }
                            }
                 		   if(j==31) {
                           c =s.getCell(j, i1); 
                           if(!(c.getContents().trim().contains("VLI"))){
                        	   VLI_IMPL_STATUS = c.getContents().trim();
                        	   VLI_IMPL_STATUS = VLI_IMPL_STATUS.replaceAll("\\r|\\n", "");
                           }
                            }
                 		   if(j==32) {
                           c =s.getCell(j, i1); 
                          // if(!(c.getContents().trim().contains("CBK"))){
                        	   CBKI_AUTO_LIME_SURVEY = c.getContents().trim();
                        	   CBKI_AUTO_LIME_SURVEY = CBKI_AUTO_LIME_SURVEY.replaceAll("\\r|\\n", "");
                        	 //  System.out.println("CBKI_AUTO_LIME_SURVEY:"+CBKI_AUTO_LIME_SURVEY);
                          // }
                            }
                 		   if(j==33) {
                           c =s.getCell(j, i1); 
                           //if(!(c.getContents().trim().contains("Key"))){
                        	   KEY_ACCOUNT_CHALLENGE = c.getContents().trim();
                        	  // System.out.println("KEY_ACCOUNT_CHALLENGE:"+KEY_ACCOUNT_CHALLENGE);
                        	   KEY_ACCOUNT_CHALLENGE = KEY_ACCOUNT_CHALLENGE.replaceAll("\\r|\\n", "");
                        	  
                           //}
                            }
                 		   if(j==34) {
                           c =s.getCell(j, i1); 
                           //if(!(c.getContents().trim().contains("Action Plan"))){
                        	   MITIGATION_ACTION_PLAN = c.getContents().trim();
                        	  // System.out.println("MITIGATION_ACTION_PLAN:"+MITIGATION_ACTION_PLAN);
                        	   MITIGATION_ACTION_PLAN = MITIGATION_ACTION_PLAN.replaceAll("\\r|\\n", "");
                        	 
                          // }
                            }
                 		   
                 					   
                        //String text = c.getContents().trim();
                    }
                   // System.out.println("PA_Name:"+PA_Name1);
                   //	System.out.println("PA_Account:"+PA_Account);	
                	//System.out.println("PA_Account_Manager:"+PA_Account_Manager);
                    //System.out.println(month+year);
                
                    if(PA_Account!=null && PA_Account!="" && PA_Account_Manager!=null ) {
                    	//System.out.println("PA_Name:"+PA_Name1);
                    	//System.out.println("PA_Account:"+PA_Account);	
                    	//System.out.println("PA_Account_Manager:"+PA_Account_Manager);
                    	
                    	deliveryData.setPA_Name(PA_Name1);
                    	deliveryData.setPA_Account(PA_Account);	
                    	deliveryData.setPA_Account_Manager(PA_Account_Manager);
                    	  deliveryData.setATTRITION(ATTRITION);
                    	  deliveryData.setBAG_TCV_PLAND_USD(BAG_TCV_PLAND_USD);
                    	  deliveryData.setBAND_MIX(BAND_MIX);
                    	  deliveryData.setCBKI_AUTO_LIME_SURVEY(CBKI_AUTO_LIME_SURVEY);
                    	  deliveryData.setCHECK_POINT_COMP(CHECK_POINT_COMP);
                    	  deliveryData.setCOUNT_PROJ_ENHANCE_NEWDEV(COUNT_PROJ_ENHANCE_NEWDEV);
                    	  deliveryData.setCSAT(CSAT);
                    	  deliveryData.setCSAT_TREND_IND(CSAT_TREND_IND);
                    	  deliveryData.setCURRENT_EAC_GP(CURRENT_EAC_GP);
                    	  deliveryData.setCURRENT_GP_TARGET(CURRENT_GP_TARGET);
                    	  deliveryData.setCURRENT_GP_YTD(CURRENT_GP_YTD);
                    	  deliveryData.setCURRENT_REV_EAC(CURRENT_REV_EAC);
                    	  deliveryData.setCURRENT_REV_TARGET(CURRENT_REV_TARGET);
                    	  deliveryData.setGH_PER(GH_PER);
                    	  deliveryData.setGP_PER_ACTUAL(GP_PER_ACTUAL);
                    	  deliveryData.setGP_PER_TARGET(GP_PER_TARGET);
                    	  deliveryData.setEAC_GP(EAC_GP);
                    	 // deliveryData.setHC(HC);
                    	  deliveryData.setHC_GD(HC_GD);
                    	  deliveryData.setIDEA_BAG_INSIDE_OUT(IDEA_BAG_INSIDE_OUT);
                    	  deliveryData.setKEY_ACCOUNT_CHALLENGE(KEY_ACCOUNT_CHALLENGE);
                    	  deliveryData.setLEAN_SAVINGS(LEAN_SAVINGS);
                    	  deliveryData.setLEAN_SAVINGS_EAC(LEAN_SAVINGS_EAC);
                    	  deliveryData.setLEAN_TARGET(LEAN_TARGET);
                    	  deliveryData.setMITIGATION_ACTION_PLAN(MITIGATION_ACTION_PLAN);
                    	  deliveryData.setPA_Account(PA_Account);
                    	  deliveryData.setPA_Account_Manager(PA_Account_Manager);
                    	  deliveryData.setREV_ACTUAL_IN_M(REV_ACTUAL_IN_M);
                    	  deliveryData.setREV_FORECAST_IN_MILL(REV_FORECAST_IN_MILL);
                    	  deliveryData.setREV_TARGET_IN_MILL(REV_TARGET_IN_MILL);
                    	  deliveryData.setREV_YTD_ACTUAL_IN_MILL(REV_YTD_ACTUAL_IN_MILL);
                    	  deliveryData.setSIGNINGS(SIGNINGS);
                    	  deliveryData.setSOLUTIONED_BAND_MIX(SOLUTIONED_BAND_MIX);
                    	  deliveryData.setSTAFFING_REVENUE_IMPACT(STAFFING_REVENUE_IMPACT);
                    	  deliveryData.setTCV_REALIZED_USD(TCV_REALIZED_USD);
                    	  deliveryData.setVLI_IMPL_STATUS(VLI_IMPL_STATUS);
                    	  deliveryData.setMonth(month);
                    	  deliveryData.setYear(year);
                    	//  System.out.println("hr:"+month+year);
                    	  deliveryDataList.add(deliveryData);
                    	  
                    	 
                }
                    
                    }
                
               // System.out.println("deliveryDataList size:"+deliveryDataList.size());
               // DeliveryDAO.insertData(deliveryDataMap);
                      
            }
                if(sheetName.contains("Trends") && !(sheetName.contains("Life"))) {

                    
                	String Trends_PA_Name = sheetName.split("-")[1];
                	//System.out.println("trends:"+Trends_PA_Name);
                	trendsDataList = new ArrayList <TrendsData>();
                  	 String PREVIOUS_YEAR = null ;
                	 String CURRENT_YEAR  = null ;
                	for(int i1=0; i1<row;i1++) {
                 TrendsData trendsData = new TrendsData();	
            
            	 String MONTH = null ;
     
            	 String PREVIOUS_HC_TOTAL = null;
            	 String HC_GD = null ;
            	 String CURRENT_HC_TOTAL = null ;
            	 String PREVIOUS_YTD_REVENUE = null ;
            	 String CURRENT_YTD_REVENUE = null ;
            	 String PREVIOUS_YTD_GP_PER = null ;
            	 String CURRENT_YTD_GP_PER = null ;
            	 String PREVIOUS_SINGNINGS = null ;
            	 String CURRENT_SIGNINGS = null ;
            	 String PREVIOUS_CSAT = null ;
            	 String CURRENT_CSAT = null ;
            	 String PREVIOUS_BAND_MIX = null ;
            	 String CURRENT_BAND_MIX = null ;
            	 String PREVIOUS_ATTRITION = null ;
            	 String CURRENT_ATTRITION = null;
            	 String PREVIOUS_GH_PER = null ;
            	 String CURRENT_GH_PER = null ;
            	 if(blankCount >=3) {
             		break;
             	}
             
                 for(int j=0;j<col;j++) {
                 	Cell c;
                 	
                   
                    if(j==0) {
                 	   c =s.getCell(j, i1); 
                 	  if( c.getContents().trim().isEmpty()) {
               		   blankCount++;
               		 break;
                 	  }
                 	  MONTH = c.getContents().trim();
                 	 MONTH = MONTH.replaceAll("\\r|\\n", "");
                 	 
                 	
                 	   }
                    if(j==1) {
                  	   c =s.getCell(j, i1); 
                  	 PREVIOUS_HC_TOTAL = c.getContents().trim();
                  	  if(PREVIOUS_HC_TOTAL.contains("HC")) {
                  		PREVIOUS_YEAR  = PREVIOUS_HC_TOTAL.replaceAll("[^0-9.]","");
               
                  		  
                  	  }
                  	PREVIOUS_HC_TOTAL = PREVIOUS_HC_TOTAL.replaceAll("\\r|\\n", "");
                  	
                  	   }
                    /*if(j==2) {
                  	   c =s.getCell(j, i1); 
                  	 HC_GD = c.getContents().trim();
                  	HC_GD = HC_GD.replaceAll("\\r|\\n", "");
                  	
                  	   }*/
                    if(j==2) {
                  	   c =s.getCell(j, i1); 
                  	 CURRENT_HC_TOTAL = c.getContents().trim();
                  	if(CURRENT_HC_TOTAL.contains("HC")) {
                  		CURRENT_YEAR  = CURRENT_HC_TOTAL.replaceAll("[^0-9.]","");
                  		//System.out.println("CURRENT_YEAR:"+CURRENT_YEAR);
                  		  
                  	  }
                  	CURRENT_HC_TOTAL = CURRENT_HC_TOTAL.replaceAll("\\r|\\n", "");
                  	
                  	   }
                    if(j==3) {
                  	   c =s.getCell(j, i1); 
                  	 PREVIOUS_YTD_REVENUE = c.getContents().trim();
                  	PREVIOUS_YTD_REVENUE = PREVIOUS_YTD_REVENUE.replaceAll("\\r|\\n", "");
                  	
                  	   }
                    if(j==4) {
                  	   c =s.getCell(j, i1); 
                  	 CURRENT_YTD_REVENUE = c.getContents().trim();
                  	CURRENT_YTD_REVENUE = CURRENT_YTD_REVENUE.replaceAll("\\r|\\n", "");
                  	
                  	   }
                    if(j==5) {
                  	   c =s.getCell(j, i1); 
                  	 PREVIOUS_YTD_GP_PER = c.getContents().trim();
                  	PREVIOUS_YTD_GP_PER = PREVIOUS_YTD_GP_PER.replaceAll("\\r|\\n", "");
                  	
                  	   }
                    if(j==6) {
                  	   c =s.getCell(j, i1); 
                  	 CURRENT_YTD_GP_PER = c.getContents().trim();
                  	CURRENT_YTD_GP_PER = CURRENT_YTD_GP_PER.replaceAll("\\r|\\n", "");
                  	
                  	   }
                    if(j==7) {
                  	   c =s.getCell(j, i1); 
                  	 PREVIOUS_SINGNINGS = c.getContents().trim();
                  	PREVIOUS_SINGNINGS = PREVIOUS_SINGNINGS.replaceAll("\\r|\\n", "");
                  	
                  	   }
                    if(j==8) {
                  	   c =s.getCell(j, i1); 
                  	 CURRENT_SIGNINGS = c.getContents().trim();
                  	CURRENT_SIGNINGS = CURRENT_SIGNINGS.replaceAll("\\r|\\n", "");
                  	
                  	   }
                    PREVIOUS_CSAT ="";
                    CURRENT_CSAT="";
                    /*if(j==10) {
                  	   c =s.getCell(j, i1); 
                  	 PREVIOUS_CSAT = c.getContents().trim();
                  	PREVIOUS_CSAT = PREVIOUS_CSAT.replaceAll("\\r|\\n", "");
                  	
                  	   }
                    if(j==11) {
                  	   c =s.getCell(j, i1); 
                  	 CURRENT_CSAT = c.getContents().trim();
                  	CURRENT_CSAT = CURRENT_CSAT.replaceAll("\\r|\\n", "");
                  	
                  	   }*/
                    if(j==9) {
                  	   c =s.getCell(j, i1); 
                  	 PREVIOUS_BAND_MIX = c.getContents().trim();
                  	PREVIOUS_BAND_MIX = PREVIOUS_BAND_MIX.replaceAll("\\r|\\n", "");
                  	
                  	   }
                    if(j==10) {
                  	   c =s.getCell(j, i1); 
                  	 CURRENT_BAND_MIX = c.getContents().trim();
                  	CURRENT_BAND_MIX = CURRENT_BAND_MIX.replaceAll("\\r|\\n", "");
                  	
                  	   }
                    if(j==11) {
                  	   c =s.getCell(j, i1); 
                  	 PREVIOUS_ATTRITION = c.getContents().trim();
                  	PREVIOUS_ATTRITION = PREVIOUS_ATTRITION.replaceAll("\\r|\\n", "");
                  	
                  	   }
                    if(j==12) {
                  	   c =s.getCell(j, i1); 
                  	 CURRENT_ATTRITION = c.getContents().trim();
                  	CURRENT_ATTRITION = CURRENT_ATTRITION.replaceAll("\\r|\\n", "");
                  	
                  	   }
                    if(j==13) {
                  	   c =s.getCell(j, i1); 
                  	 PREVIOUS_GH_PER = c.getContents().trim();
                  	PREVIOUS_GH_PER = PREVIOUS_GH_PER.replaceAll("\\r|\\n", "");
                  	
                  	   }
                    if(j==14) {
                  	   c =s.getCell(j, i1); 
                  	 CURRENT_GH_PER = c.getContents().trim();
                  	CURRENT_GH_PER = CURRENT_GH_PER.replaceAll("\\r|\\n", "");
                  	
                  	   }
                 }
                  
                    if((!(MONTH.contains("Account"))) && !(MONTH.isEmpty())) {
                   		//System.out.println("PREVIOUS_YEAR:"+PREVIOUS_YEAR);
                    	trendsData.setCURRENT_ATTRITION(CURRENT_ATTRITION);
                    	trendsData.setCURRENT_BAND_MIX(CURRENT_BAND_MIX);
                    	trendsData.setCURRENT_CSAT(CURRENT_CSAT);
                    	trendsData.setCURRENT_GH_PER(CURRENT_GH_PER);
                    	trendsData.setCURRENT_HC_TOTAL(CURRENT_HC_TOTAL);
                    	trendsData.setCURRENT_SIGNINGS(CURRENT_SIGNINGS);
                    	trendsData.setCURRENT_YEAR(CURRENT_YEAR);
                    	trendsData.setCURRENT_YTD_GP_PER(CURRENT_YTD_GP_PER);
                    	trendsData.setCURRENT_YTD_REVENUE(CURRENT_YTD_REVENUE);
                    	trendsData.setHC_GD(HC_GD);
                    	trendsData.setMONTH(MONTH);
                    	trendsData.setPA_NAME(Trends_PA_Name);
                    	trendsData.setPREVIOUS_ATTRITION(PREVIOUS_ATTRITION);
                    	trendsData.setPREVIOUS_BAND_MIX(PREVIOUS_BAND_MIX);
                    	trendsData.setPREVIOUS_CSAT(PREVIOUS_CSAT);
                    	trendsData.setPREVIOUS_GH_PER(PREVIOUS_GH_PER);
                    	trendsData.setPREVIOUS_HC_TOTAL(PREVIOUS_HC_TOTAL);
                    	trendsData.setPREVIOUS_SINGNINGS(PREVIOUS_SINGNINGS);
                    	trendsData.setPREVIOUS_YEAR(PREVIOUS_YEAR);
                    	trendsData.setPREVIOUS_YTD_GP_PER(PREVIOUS_YTD_GP_PER);
                    	trendsData.setPREVIOUS_YTD_REVENUE(PREVIOUS_YTD_REVENUE);
                    	trendsDataList.add(trendsData);
                    	
                    }
                    
                   
                
                 
                	}
                	 
                	TrendsDAO.insertData(Trends_PA_Name,trendsDataList);
                
                }
                if(sheetName.contains("Trends") && sheetName.contains("Life")) {
                
                	String Trends_PA_Name = sheetName.split("-")[1];
                	//System.out.println("trends:"+Trends_PA_Name);
                	trendsDataList = new ArrayList <TrendsData>();
                  	 String PREVIOUS_YEAR = null ;
                	 String CURRENT_YEAR  = null ;
                	for(int i1=0; i1<row;i1++) {
                 TrendsData trendsData = new TrendsData();	
            
            	 String MONTH = null ;
     
            	 String PREVIOUS_HC_TOTAL = null;
            	 String HC_GD = null ;
            	 String CURRENT_HC_TOTAL = null ;
            	 String PREVIOUS_YTD_REVENUE = null ;
            	 String CURRENT_YTD_REVENUE = null ;
            	 String PREVIOUS_YTD_GP_PER = null ;
            	 String CURRENT_YTD_GP_PER = null ;
            	 String PREVIOUS_SINGNINGS = null ;
            	 String CURRENT_SIGNINGS = null ;
            	 String PREVIOUS_CSAT = null ;
            	 String CURRENT_CSAT = null ;
            	 String PREVIOUS_BAND_MIX = null ;
            	 String CURRENT_BAND_MIX = null ;
            	 String PREVIOUS_ATTRITION = null ;
            	 String CURRENT_ATTRITION = null;
            	 String PREVIOUS_GH_PER = null ;
            	 String CURRENT_GH_PER = null ;
            	 if(blankCount >=3) {
             		break;
             	}
             
                 for(int j=0;j<col;j++) {
                 	Cell c;
                 	
                   
                    if(j==0) {
                 	   c =s.getCell(j, i1); 
                 	  if( c.getContents().trim().isEmpty()) {
               		   blankCount++;
               		 break;
                 	  }
                 	  MONTH = c.getContents().trim();
                 	 MONTH = MONTH.replaceAll("\\r|\\n", "");
                 	 
                 	
                 	   }
                    if(j==1) {
                  	   c =s.getCell(j, i1); 
                  	 PREVIOUS_HC_TOTAL = c.getContents().trim();
                  	  if(PREVIOUS_HC_TOTAL.contains("HC")) {
                  		PREVIOUS_YEAR  = PREVIOUS_HC_TOTAL.replaceAll("[^0-9.]","");
               
                  		  
                  	  }
                  	PREVIOUS_HC_TOTAL = PREVIOUS_HC_TOTAL.replaceAll("\\r|\\n", "");
                  	
                  	   }
                    /*if(j==2) {
                  	   c =s.getCell(j, i1); 
                  	 HC_GD = c.getContents().trim();
                  	HC_GD = HC_GD.replaceAll("\\r|\\n", "");
                  	
                  	   }*/
                    if(j==2) {
                  	   c =s.getCell(j, i1); 
                  	 CURRENT_HC_TOTAL = c.getContents().trim();
                  	if(CURRENT_HC_TOTAL.contains("HC")) {
                  		CURRENT_YEAR  = CURRENT_HC_TOTAL.replaceAll("[^0-9.]","");
                  		//System.out.println("CURRENT_YEAR:"+CURRENT_YEAR+CURRENT_HC_TOTAL);
                  		  
                  	  }
                  	CURRENT_HC_TOTAL = CURRENT_HC_TOTAL.replaceAll("\\r|\\n", "");
                  	
                  	   }
                    if(j==3) {
                  	   c =s.getCell(j, i1); 
                  	 PREVIOUS_YTD_REVENUE = c.getContents().trim();
                  	PREVIOUS_YTD_REVENUE = PREVIOUS_YTD_REVENUE.replaceAll("\\r|\\n", "");
                  	
                  	   }
                    if(j==4) {
                  	   c =s.getCell(j, i1); 
                  	 CURRENT_YTD_REVENUE = c.getContents().trim();
                  	CURRENT_YTD_REVENUE = CURRENT_YTD_REVENUE.replaceAll("\\r|\\n", "");
                  	
                  	   }
                    if(j==5) {
                  	   c =s.getCell(j, i1); 
                  	 PREVIOUS_YTD_GP_PER = c.getContents().trim();
                  	PREVIOUS_YTD_GP_PER = PREVIOUS_YTD_GP_PER.replaceAll("\\r|\\n", "");
                  	
                  	   }
                    if(j==6) {
                  	   c =s.getCell(j, i1); 
                  	 CURRENT_YTD_GP_PER = c.getContents().trim();
                  	CURRENT_YTD_GP_PER = CURRENT_YTD_GP_PER.replaceAll("\\r|\\n", "");
                  	
                  	   }
                    if(j==7) {
                  	   c =s.getCell(j, i1); 
                  	 PREVIOUS_SINGNINGS = c.getContents().trim();
                  	PREVIOUS_SINGNINGS = PREVIOUS_SINGNINGS.replaceAll("\\r|\\n", "");
                  	
                  	   }
                    if(j==8) {
                  	   c =s.getCell(j, i1); 
                  	 CURRENT_SIGNINGS = c.getContents().trim();
                  	CURRENT_SIGNINGS = CURRENT_SIGNINGS.replaceAll("\\r|\\n", "");
                  	
                  	   }
                    if(j==9) {
                  	   c =s.getCell(j, i1); 
                  	 PREVIOUS_CSAT = c.getContents().trim();
                  	PREVIOUS_CSAT = PREVIOUS_CSAT.replaceAll("\\r|\\n", "");
                  	
                  	   }
                    if(j==10) {
                  	   c =s.getCell(j, i1); 
                  	 CURRENT_CSAT = c.getContents().trim();
                  	CURRENT_CSAT = CURRENT_CSAT.replaceAll("\\r|\\n", "");
                  	
                  	   }
                    if(j==11) {
                  	   c =s.getCell(j, i1); 
                  	 PREVIOUS_BAND_MIX = c.getContents().trim();
                  	PREVIOUS_BAND_MIX = PREVIOUS_BAND_MIX.replaceAll("\\r|\\n", "");
                  	
                  	   }
                    if(j==12) {
                  	   c =s.getCell(j, i1); 
                  	 CURRENT_BAND_MIX = c.getContents().trim();
                  	CURRENT_BAND_MIX = CURRENT_BAND_MIX.replaceAll("\\r|\\n", "");
                  	
                  	   }
                    if(j==13) {
                  	   c =s.getCell(j, i1); 
                  	 PREVIOUS_ATTRITION = c.getContents().trim();
                  	PREVIOUS_ATTRITION = PREVIOUS_ATTRITION.replaceAll("\\r|\\n", "");
                  	
                  	   }
                    if(j==14) {
                  	   c =s.getCell(j, i1); 
                  	 CURRENT_ATTRITION = c.getContents().trim();
                  	CURRENT_ATTRITION = CURRENT_ATTRITION.replaceAll("\\r|\\n", "");
                  	
                  	   }
                    if(j==15) {
                  	   c =s.getCell(j, i1); 
                  	 PREVIOUS_GH_PER = c.getContents().trim();
                  	PREVIOUS_GH_PER = PREVIOUS_GH_PER.replaceAll("\\r|\\n", "");
                  	
                  	   }
                    if(j==16) {
                  	   c =s.getCell(j, i1); 
                  	 CURRENT_GH_PER = c.getContents().trim();
                  	CURRENT_GH_PER = CURRENT_GH_PER.replaceAll("\\r|\\n", "");
                  	//System.out.println("CURRENT_GH_PER:"+CURRENT_GH_PER);
                  	   }
                 }
                  
                    if((!(MONTH.contains("Account"))) && !(MONTH.isEmpty())) {
                   		//System.out.println("PREVIOUS_YEAR:"+PREVIOUS_YEAR);
                    	trendsData.setCURRENT_ATTRITION(CURRENT_ATTRITION);
                    	trendsData.setCURRENT_BAND_MIX(CURRENT_BAND_MIX);
                    	trendsData.setCURRENT_CSAT(CURRENT_CSAT);
                    	trendsData.setCURRENT_GH_PER(CURRENT_GH_PER);
                    	trendsData.setCURRENT_HC_TOTAL(CURRENT_HC_TOTAL);
                    	trendsData.setCURRENT_SIGNINGS(CURRENT_SIGNINGS);
                    	trendsData.setCURRENT_YEAR(CURRENT_YEAR);
                    	trendsData.setCURRENT_YTD_GP_PER(CURRENT_YTD_GP_PER);
                    	trendsData.setCURRENT_YTD_REVENUE(CURRENT_YTD_REVENUE);
                    	trendsData.setHC_GD(HC_GD);
                    	trendsData.setMONTH(MONTH);
                    	trendsData.setPA_NAME(Trends_PA_Name);
                    	trendsData.setPREVIOUS_ATTRITION(PREVIOUS_ATTRITION);
                    	trendsData.setPREVIOUS_BAND_MIX(PREVIOUS_BAND_MIX);
                    	trendsData.setPREVIOUS_CSAT(PREVIOUS_CSAT);
                    	trendsData.setPREVIOUS_GH_PER(PREVIOUS_GH_PER);
                    	trendsData.setPREVIOUS_HC_TOTAL(PREVIOUS_HC_TOTAL);
                    	trendsData.setPREVIOUS_SINGNINGS(PREVIOUS_SINGNINGS);
                    	trendsData.setPREVIOUS_YEAR(PREVIOUS_YEAR);
                    	trendsData.setPREVIOUS_YTD_GP_PER(PREVIOUS_YTD_GP_PER);
                    	trendsData.setPREVIOUS_YTD_REVENUE(PREVIOUS_YTD_REVENUE);
                    	trendsDataList.add(trendsData);
                    	
                    }
                    
                   
                
                 
                	}
                	 
                	TrendsDAO.insertData(Trends_PA_Name,trendsDataList);
                }
            
        }
        }catch (Exception e) {
            e.getMessage();
        }
        
       // System.out.println("deliveryDataMap size:"+deliveryDataMap.size());
       // System.out.println("trendsDataList:"+trendsDataList.size());
        System.out.println("completed");
     
    }

	
	
   
  	  
}