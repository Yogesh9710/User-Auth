package com.yesdosoft.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yesdosoft.dao.UserDao;

/**
 * Servlet implementation class ShowUser
 */
@WebServlet("/ShowUser")
public class ShowUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ResultSet rs=UserDao.getUser();
			PrintWriter out=response.getWriter();
			out.print("<html><body><table><tr><th>userid </th><th>country </th><th>email </th><th>gender </th><th>mobileNo </th><th>name </th><th>password </th></tr>");
			while(rs.next()) {
				out.print("<tr>");
				out.print("<td>"+rs.getInt(1)+"</td");
				out.print("<td>"+rs.getString(2)+"</td>");
				out.print("<td>"+rs.getString(3)+"</td>");
				out.print("<td>"+rs.getString(4)+"</td>");
				out.print("<td>"+rs.getDouble(5)+"</td");
				out.print("<td>"+rs.getString(6)+"</td>");
				out.print("<td>"+rs.getString(7)+"</td>");
				
				out.print("</tr>");
				
			}
			out.print("</table></body></html>");
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
