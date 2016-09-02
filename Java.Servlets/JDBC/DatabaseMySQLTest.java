package connector;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Connect extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Connect() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            out.print(e.getStackTrace().toString());
            out.print("<br>");
            return;
        }

        Connection con = null;

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdatabase", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
            out.print(e.getStackTrace().toString());
            out.print("<br>");
            return;
        }

        out.println("Connected! :D");

        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}
