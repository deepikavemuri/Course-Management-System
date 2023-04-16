package com.cms.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import javax.servlet.RequestDispatcher;
import com.cms.dao.UserDAO;
import com.cms.dto.User;

@WebServlet("/signIn")
public class signIn extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email1");
		String password = request.getParameter("password1");
		String table_name = request.getParameter("userType");
		
		PrintWriter out = response.getWriter();
		UserDAO userdao = new UserDAO();
		User user = userdao.checkUser(table_name, email, password);
		
		if(user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loggedUserId", user.getUserId());
			session.setAttribute("loggedUserFname", user.getFullName());
			session.setAttribute("loggedUserEmail", user.getEmail());
			session.setAttribute("loggedUserPassword", user.getPassword());
			session.setAttribute("loggedUserType", user.getTable_name());
			RequestDispatcher dispatcher = request.getRequestDispatcher(table_name + "Home.jsp");
			request.setAttribute("user", user);
			dispatcher.forward(request, response);
		}
		
		else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("invalidUser.jsp");
			dispatcher.include(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
