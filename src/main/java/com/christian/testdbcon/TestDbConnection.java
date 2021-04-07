package com.christian.testdbcon;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestDbConnection
 */
@WebServlet("/TestDbConnection")
public class TestDbConnection extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//setup connection variables
		String user= "springstudent";
		String pass="springstudent";
	
		String jdbcUrl= "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false&serverTimezone=UTC";
		String driver ="com.mysql.cj.jdbc.Driver";
		//get a connection to db
		try {
			
			PrintWriter out = response.getWriter();
			
			out.println("Connecting to database: "+ jdbcUrl);
			
			Class.forName(driver);
			
			
			Connection myConn= DriverManager.getConnection(jdbcUrl,user,pass);
			
			out.println("Connection successful");
			myConn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}finally {
			
		}
	}

}
