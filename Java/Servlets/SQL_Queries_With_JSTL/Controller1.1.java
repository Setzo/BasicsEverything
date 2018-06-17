package controllerr;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Controller() {
        super();
    }

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        Map<String, String> actionMap = new HashMap<String, String>();
        actionMap.put("img", "/image.jsp");
        actionMap.put("home", "/home.jsp");

        if (action == null || !actionMap.containsKey(action)) {
            action = "home";
        }

        request.getRequestDispatcher(actionMap.get(action)).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}
