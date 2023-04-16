package com.cms.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.cms.dao.UserDAO;
 
@WebServlet("/FileUpload")
@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB
public class FileUpload extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int x = Integer.parseInt(request.getParameter("x").toString());
    	int rid = Integer.parseInt(request.getParameter("rid").toString());
    	HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("loggedUserId");
		String rname = request.getParameter("rname");
		
		InputStream inputStream = null;
    	UserDAO userdao = new UserDAO();
    	if(x == 2) {
    		String fileName = request.getParameter("fileName");    		
    		Part filePart = request.getPart("file");
    		if (filePart != null) {
    			System.out.println(filePart.getName());
    			System.out.println(filePart.getSize());
    			System.out.println(filePart.getContentType());
    			inputStream = filePart.getInputStream();
    		}         
    		userdao.uploadAssignment(inputStream, userId, fileName, rid); 
    		RequestDispatcher dispatcher = request.getRequestDispatcher("instructorAssignment.jsp");
    		request.setAttribute("rid", rid);
			request.setAttribute("rname", rname);
    		dispatcher.forward(request, response);
    	}
    	else if(x == 1) {
    		ArrayList<String> fileNames = new ArrayList<String>();
    		ArrayList<Integer> fileIds = new ArrayList<Integer>(); 
    		fileNames = userdao.viewAssignments(rid);
    		fileIds = userdao.getFileIds(rid);
			RequestDispatcher dispatcher = request.getRequestDispatcher("uploadedAssignments.jsp");
			request.setAttribute("rid", rid);
			request.setAttribute("y", 1);
			request.setAttribute("fileNames", fileNames);
			request.setAttribute("rname", rname);
			request.setAttribute("fileIds", fileIds);
			dispatcher.forward(request, response);
    	}
    	else if(x == 3){
    		int aid = Integer.parseInt(request.getParameter("fileId"));
    		Part filePart = request.getPart("file");
    		ArrayList<String> fileNames = new ArrayList<String>();
    		ArrayList<Integer> fileIds = new ArrayList<Integer>(); 
    		fileNames = userdao.viewAssignments(rid);
    		fileIds = userdao.getFileIds(rid);
    		if (filePart != null) {
    			System.out.println(filePart.getName());
    			System.out.println(filePart.getSize());
    			System.out.println(filePart.getContentType());
    			inputStream = filePart.getInputStream();
    		}         
    		userdao.studentSubmit(inputStream, userId, aid, rid); 
    		RequestDispatcher dispatcher = request.getRequestDispatcher("uploadedAssignments.jsp");
    		request.setAttribute("rid", rid);
    		request.setAttribute("rname", rname);
    		request.setAttribute("fileNames", fileNames);
    		request.setAttribute("y", 2);
    		request.setAttribute("fileIds", fileIds);
    		dispatcher.forward(request, response);
    	}
    	else {
    		int aid = Integer.parseInt(request.getParameter("fileId"));
    		ArrayList<String> students = new ArrayList<String>();
    		students = userdao.viewStudentUpload(aid, rid);
    		RequestDispatcher dispatcher = request.getRequestDispatcher("studentSubmissionsList.jsp");
    		request.setAttribute("rid", rid);
    		request.setAttribute("aid", aid);
    		request.setAttribute("rname", rname);
    		request.setAttribute("students", students);
    		dispatcher.forward(request, response);
    	}
    }
}