package com.yesdosoft.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionValidate
 */
@WebServlet("/SessionValidate")
public class SessionValidate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionValidate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	boolean firsttime=false;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		if(login(email,password)) {
			HttpSession session=request.getSession(false);
			if(session!=null) {
			session.setAttribute("firsttime", firsttime);
			session.setAttribute("email", email);
			}
		
			RequestDispatcher rd=request.getRequestDispatcher("Welcome");
			rd.forward(request, response);
		}
		else {
			response.getWriter().print("Sorry!! email and password error");
			RequestDispatcher rd1=request.getRequestDispatcher("login.jsp");
			rd1.include(request, response);
		}
	}
	boolean login ( String email, String password) {
        String url = "jdbc:mysql://localhost:3306/yesdosoft22?allowPublicKeyRetrieval=true&useSSL=false";
        String user = "root";
        String dbpassword = "Yog@1984";
        try {
            //load a driver 
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con =
                    DriverManager.getConnection(url, user, dbpassword);            
            Statement s = con.createStatement();
            
            String sql = "select email from tbluser"
                    + " where email = '"+email+"' and password = '"+password+"'";
            ResultSet rs = s.executeQuery(sql);
            
            if ( rs.next() ) {
                rs.close();
                s.close();
                con.close();
                return true;
            }
            rs.close();
            s.close();
            con.close();
        }catch(ClassNotFoundException e) {
            System.out.println(e.toString());
        }catch(SQLException e) {
            System.out.println(e.toString());
        }catch(Exception e) {
            System.out.println(e.toString());
        }
        return false;
    }

}
