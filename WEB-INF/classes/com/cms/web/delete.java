package com.cms.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cms.dao.UserDAO;

@WebServlet("/delete")
public class delete extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int type = Integer.parseInt(request.getParameter("type").toString());
		int id = Integer.parseInt(request.getParameter("userId").toString());
		UserDAO userdao = new UserDAO();
		if(type == 1)
			userdao.deleteStudent(id);
		else if(type == 2)
			userdao.deleteInstructor(id);
		else
			userdao.deleteRoom(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("adminHome.jsp");
		dispatcher.forward(request, response);
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
