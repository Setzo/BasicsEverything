package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Controller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		String pageToForward = null;
		
		if(action == null) {
			pageToForward = "/error.jsp";
		} else if (action.equals("login")) {
			pageToForward = "/login.jsp";
		} else if (action.equals("about")) {
			pageToForward = "/about.jsp";
		} else {
			pageToForward = "/error.jsp";
		}
		
		getServletContext().getRequestDispatcher(pageToForward).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
