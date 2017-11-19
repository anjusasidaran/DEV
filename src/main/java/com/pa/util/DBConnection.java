package com.pa.util;

import java.io.IOException;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import com.ibm.json.java.JSON;
import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONObject;

public class DBConnection {
  /* public static Connection getConnection() throws Exception  { 
  	  Map<String, String> env = System.getenv(); 
  	   
  	  if (env.containsKey("VCAP_SERVICES")) { 
  	   // we are running on cloud foundry, let's grab the service details from vcap_services 
  	   JSONObject vcap = (JSONObject) JSON.parse(env.get("VCAP_SERVICES")); 
  	   JSONObject service = null; 
  	    
  	   // We don't know exactly what the service is called, but it will contain "elephantsql" 
  	   for (Object key : vcap.keySet()) { 
  	    String keyStr = (String) key; 
  	    if (keyStr.toLowerCase().contains("elephantsql")) { 
  	     service = (JSONObject) ((JSONArray) vcap.get(keyStr)).get(0); 
  	     break; 
  	    } 
  	   } 
  	    
  	   if (service != null) { 
  	    JSONObject creds = (JSONObject) service.get("credentials"); 
  	    URI uri = URI.create((String) creds.get("uri")); 
  	    String url = "jdbc:postgresql://" + uri.getHost() + ":" + uri.getPort() + uri.getPath(); 
  	    String username = uri.getUserInfo().split(":")[0]; 
  	    String password = uri.getUserInfo().split(":")[1]; 
  	  
  	    return DriverManager.getConnection(url, username, password); 
  	  
  	   } 
  	   System.out.println("got connection...");
  	  } 
  	   
  	  throw new Exception("No ElephantSQL service URL found. Make sure you have bound the correct services to your app."); 
  	 }*/
	/*public static Connection getConnection() {
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:db2://localhost:50000/SAMPLE", "db2admin", "db2admin");
			//System.out.println("Connection got");
			return con;
		} catch (Exception ex) {
			System.out.println(ex);
			System.out.println("Database.getConnection() Error -->"
					+ ex.getMessage());
			return null;
		}
	}*/
	public static Connection getConnection() {
		try {
			Class.forName("packy.db.elephantsql.com");
			Connection con = DriverManager.getConnection(
					"postgres://qwknxnyy:QdTs1yS_WrWAlmyYTIipaRw1dF6EiIu3@packy.db.elephantsql.com:5432/qwknxnyy", "qwknxnyy", "QdTs1yS_WrWAlmyYTIipaRw1dF6EiIu3");
			//System.out.println("Connection got");
			return con;
		} catch (Exception ex) {
			System.out.println(ex);
			System.out.println("Database.getConnection() Error -->"
					+ ex.getMessage());
			return null;
		}
	}
}
