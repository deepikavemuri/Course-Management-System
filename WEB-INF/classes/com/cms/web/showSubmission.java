package com.cms.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cms.dao.UserDAO;

@WebServlet("/showSubmission")
public class showSubmission extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String studentName = request.getParameter("student");
		int rid = Integer.parseInt(request.getParameter("rid").toString());
		int aid = Integer.parseInt(request.getParameter("aid").toString());
		UserDAO userdao = new UserDAO();
		String data = userdao.readStudentBlob(rid, aid, studentName);
		RequestDispatcher dispatcher = request.getRequestDispatcher("showFile.jsp");
		request.setAttribute("data", data);
		dispatcher.forward(request, response);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
