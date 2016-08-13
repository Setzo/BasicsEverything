package cookiestst;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CookieTest() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		out.println("<html>");
		
		Cookie[] cookies = request.getCookies();
		
		if(cookies == null) {
			out.println("No Cookies yet. <br>");
			
		} else {
		
			for(Cookie c : cookies) {
				
				out.println(c.getName() + " : " + c.getValue() + "<br>");
			}
		}
		Cookie cookie = new Cookie("user", "Setzo");
		cookie.setMaxAge(3600);
		
		response.addCookie(cookie);
		
		out.println("Cookie set.<br>");
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
