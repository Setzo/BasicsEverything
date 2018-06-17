package sess;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Phone;

public class Session extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Session() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        Phone phone = (Phone) session.getAttribute("phone");

        if (phone == null) {
            phone = new Phone();
        }

        phone.setPhone(66666666);

        session.setAttribute("phone", phone);

        getServletContext().getRequestDispatcher("/phonenum.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}
