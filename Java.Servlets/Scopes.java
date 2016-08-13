package controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Scopes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Scopes() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//REQUEST SCOPE
		request.setAttribute("attribute", "request scope");
		String reqScope = (String)request.getAttribute("attribute");
		
		//SESSION SCOPE
		HttpSession session = request.getSession();
		session.setAttribute("attribute", "session scope");
		String sessScope = (String)session.getAttribute("attribute");
		
		//APPLICATION SCOPE
		ServletContext context = getServletContext();
		context.setAttribute("attribute", "app scope");
		String appScope = (String)context.getAttribute("attribute");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
