package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.User;

public class PassObjects extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public PassObjects() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user1 = new User("User 1", 1);
        User user2 = new User("User 2", 2);
        User user3 = new User("User 3", 3);

        //REQUEST SCOPE BEAN
        request.setAttribute("user1", user1);

        //SESSION SCOPE BEAN
        request.getSession().setAttribute("user2", user2);

        //APPLICATION SCOPE BEAN
        getServletContext().setAttribute("user3", user3);

        Map<String, String> map = new HashMap<String, String>();

        map.put("key", "value");
        map.put("key0", "value0");

        request.setAttribute("map1", map);

        request.setAttribute("link", "<a href='https://projecteuler.net/'>click</a>");

        List<User> list = new LinkedList<User>();
        list.add(new User("user1", 1));
        list.add(new User("user2", 2));
        list.add(new User("user3", 3));

        request.getSession().setAttribute("list1", list);

        request.getRequestDispatcher("/receive-objects.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}
