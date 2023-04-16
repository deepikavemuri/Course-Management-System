package com.cms.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cms.dao.UserDAO;
import com.cms.dto.Room;
import com.cms.dto.User;

@WebServlet("/courseDisplay")
public class courseDisplay extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("userId"));
		String userType = request.getParameter("userType"); 
		
		
		UserDAO userdao = new UserDAO();
		ArrayList<Room> rooms = new ArrayList<>();
		if(userType.equals("instructor")) {
			rooms = userdao.getInstructorCourses(id);
			if(rooms != null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("instructorCourses.jsp");
				request.setAttribute("rooms", rooms);
				dispatcher.forward(request, response);
			}
			
			else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("invalidUser.jsp");
				dispatcher.include(request, response);
			}
		}
	
		
		else if(userType.equals("student")) {
			rooms = userdao.getStudentCourses(id);
			System.out.println(rooms);
			if(rooms != null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("studentCourses.jsp");
				request.setAttribute("rooms", rooms);
				dispatcher.forward(request, response);
			}
			
			else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("invalidUser.jsp");
				dispatcher.include(request, response);
			}
		}
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
