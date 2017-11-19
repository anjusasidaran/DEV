package com.pa.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.ibm.json.java.JSON; 
import com.ibm.json.java.JSONArray; 
import com.ibm.json.java.JSONObject;
import com.pa.dao.DeliveryDAO;
import com.pa.util.ReadExcel;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


/**
 * Servlet implementation class SimpleServlet
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
 	public String ReconFileLoc = null;
		HttpSession session = null;
	File fileName = null;
	String message = "Successfully loaded PA data file";
	String sessionID = null;
	PrintWriter out = null;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     	
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        out = response.getWriter();
        
        if (isMultipart) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);

            try {
             File storeFile = null;
           	 File fileName = null;
           	 File path = null;
           	 File newPath = null;
                List items = upload.parseRequest(request);
                Iterator iterator = items.iterator();
                String root = getServletContext().getRealPath("/");
                path = new File(root+"/"+"uploads");
                
                if(path.exists()){
                	File [] files = path.listFiles();
  					if (files.length>0){
  						for (File file : files) {
  							file.delete();
  						}
  					}
                }
                while (iterator.hasNext()) {
                    FileItem item = (FileItem) iterator.next();
                    if (!item.isFormField()) {
                        fileName = new File(item.getName());  
                  
                        String extension = null;
                        String filePath = null;
                        int i = fileName.getName().lastIndexOf('.');
                        if (i > 0) {
                            extension = fileName.getName().substring(i+1);
                            filePath = path + File.separator + "DataLoad"+"."+extension;
                        }
                        
                        System.out.println("file path :"+filePath);
                      if (!path.exists()) {
                       	 System.out.println("path:"+path);
                            boolean status = path.mkdirs();
                                               
                           
                            storeFile = new File(filePath);
                            System.out.println("storeFile1:"+storeFile+status);
                            
                            item.write(storeFile); 
                   
                        }      
                        else if(path.exists()){
                        	
                            storeFile = new File(filePath);
                           
                                item.write(storeFile); 
                                
                        }
                      ReadExcel.uploadData(filePath);
                       
                    }
                }
         	
                response.setContentType("text/html");
                response.getWriter().print("Hello World!");   
                request.setAttribute("message","Data Uploaded successfully !!!");
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
            }
            catch(Exception e){
            	e.printStackTrace();
            }
            
        } }

    
     }
	           
	


