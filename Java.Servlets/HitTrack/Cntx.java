package controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Cntx extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Cntx() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext servletContext = getServletContext();
		
		Integer hits = (Integer)servletContext.getAttribute("hits");
		
		if(hits == null) {
			hits = 1;
			servletContext.setAttribute("hits", hits);
		} else {
			hits++;
			servletContext.setAttribute("hits", hits);
		}
		
		response.getWriter().print("<html>" + hits + "</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
