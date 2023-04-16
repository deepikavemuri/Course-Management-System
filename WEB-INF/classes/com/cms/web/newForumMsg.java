package com.cms.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cms.dao.UserDAO;

@WebServlet("/newForumMsg")
public class newForumMsg extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int fid = Integer.parseInt(request.getParameter("rid").toString());
		int userId = Integer.parseInt(request.getParameter("userId").toString());
		String FName = request.getParameter("FName");
		String userType = request.getParameter("userType");
		String newMsg = request.getParameter("newMsg");
		String rname = request.getParameter("rname");
		
		UserDAO userdao = new UserDAO();
		if(newMsg != "") {
			int x = userdao.newForumMessage(fid, userId, newMsg, FName, userType);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/courseSelect");
			request.setAttribute("rid", fid);
			request.setAttribute("rname", rname);
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
