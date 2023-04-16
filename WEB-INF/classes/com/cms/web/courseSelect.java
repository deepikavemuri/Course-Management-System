package com.cms.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cms.dao.UserDAO;
import com.cms.dto.Forum;

@WebServlet("/courseSelect")
public class courseSelect extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int rid = Integer.parseInt(request.getParameter("rid").toString());
		int userId = Integer.parseInt(request.getParameter("userId"));
		String userName = request.getParameter("FName");
		String userType = request.getParameter("userType");
		String rname = request.getParameter("rname");
		System.out.println(rname);
		
		UserDAO userdao = new UserDAO();
		ArrayList<Forum> forum = new ArrayList<>();
		forum = userdao.displayForum(rid);
	//	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
	//	Date date = new Date();
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("courseMainPage.jsp");
		request.setAttribute("forum", forum);
		request.setAttribute("rid", rid);
		request.setAttribute("rname", rname);
		//request.setAttribute("date", date);
		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}