package com.cms.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cms.dao.UserDAO;

@WebServlet("/assignment")
public class assignment extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("userId").toString());
		int rid = Integer.parseInt(request.getParameter("rid").toString());
		String userType = request.getParameter("userType");
		String rname = request.getParameter("rname");
		
		UserDAO userdao = new UserDAO();
		
		if(userType.equals("instructor")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("instructorAssignment.jsp");
			request.setAttribute("rid", rid);
			request.setAttribute("rname", rname);
			dispatcher.forward(request, response);
		}
		
		else {
			ArrayList<String> fileNames = new ArrayList<String>();
			ArrayList<Integer> fileIds = new ArrayList<Integer>(); 
    		fileNames = userdao.viewAssignments(rid);
    		fileIds = userdao.getFileIds(rid);
			RequestDispatcher dispatcher = request.getRequestDispatcher("uploadedAssignments.jsp");
			request.setAttribute("rid", rid);
			request.setAttribute("y", 2);
			request.setAttribute("fileNames", fileNames);
			request.setAttribute("fileIds", fileIds);
			request.setAttribute("rname", rname);
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
