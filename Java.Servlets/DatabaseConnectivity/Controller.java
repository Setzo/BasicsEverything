package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import database.Account;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DataSource dataSource;

	public Controller() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {

		try {
			InitialContext initContext = new InitialContext();

			Context env = (Context) initContext.lookup("java:comp/env");

			dataSource = (DataSource) env.lookup("jdbc/testdatabase");

		} catch (NamingException e) {
			throw new ServletException();
		}
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Connection con = null;

		PrintWriter out = response.getWriter();
		
		try {
			con = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String action = request.getParameter("action");

		if (action == null) {
			out.println("Action is null");
		}
		
		if(action.equals("dologin")) {
			
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			request.setAttribute("email", email);
			request.setAttribute("password", "");
			
			Account acc = new Account(con);
			
			try {
				if(acc.login(email, password)) {
					request.getRequestDispatcher("/loginsuccess.jsp").forward(request, response);
					
				} else {
					request.setAttribute("message", "email or password not recognised");
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				}
			} catch (SQLException e) {
				System.err.println("fked up SQL statement");
			}
		}
		
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
