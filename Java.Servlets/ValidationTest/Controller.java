package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.User;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Controller() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if(action == null) {
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			
		} else if(action.equals("login")) {
			
			request.setAttribute("email", "");
			request.setAttribute("password", "");
			request.setAttribute("message", "");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			
		} else {
			//TODO request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if(action == null) {
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			
		} else if(action.equals("dologin")) {
			
			User user = new User();
			
			user.setEmail(request.getParameter("email"));
			user.setPassword(request.getParameter("password"));
			
			if(user.isValid()) {
				request.getRequestDispatcher("/logged.jsp").forward(request, response);
				
			} else {
				request.setAttribute("email", request.getParameter("email"));
				request.setAttribute("password", request.getParameter("password"));
				request.setAttribute("message", user.getValidationErrorMessage());
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
			
		} else {
			//TODO request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

}
