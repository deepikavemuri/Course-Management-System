package com.cms.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cms.dao.UserDAO;

@WebServlet("/addNew")
public class addNew extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int type = Integer.parseInt(request.getParameter("type").toString());
		UserDAO userdao = new UserDAO();
		
		if(type == 1) {
			String FName = request.getParameter("FName"); 
			String email = request.getParameter("email"); 
			String password = request.getParameter("pwd"); 
			userdao.newStudent(FName, password, email);
		}
		else if(type == 2) {
			String FName = request.getParameter("FName"); 
			String email = request.getParameter("email"); 
			String password = request.getParameter("pwd"); 
			userdao.newInstructor(FName, password, email);
		}
		else {
			String rname = request.getParameter("rname");
			int iid = Integer.parseInt(request.getParameter("iid").toString());
			String rdesc = request.getParameter("rdesc");
			userdao.newRoom(rname, iid, rdesc);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("adminHome.jsp");
		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
