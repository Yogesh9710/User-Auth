package com.yesdosoft.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.yesdosoft.pojo.User;


/**
 * Servlet implementation class UserSave
 */
@WebServlet("/SaveUser")
public class SaveUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveUser() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userid=Integer.parseInt(request.getParameter("id"));
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String gender=request.getParameter("gender");
		double mobileNo=Double.parseDouble(request.getParameter("mobileno"));
		String country=request.getParameter("country");
		 
		Configuration cfg=new Configuration();
		 cfg.configure("hibernate.cfg.xml");
		 
		 SessionFactory factory=cfg.buildSessionFactory();
		 
		 Session session=factory.openSession();
		 
		 Transaction tx=session.beginTransaction();
		 User u=new User();
		 u.setUserid(userid);
		 u.setName(name);
		 u.setEmail(email);
		 u.setPassword(password);
		 u.setGender(gender);
		 u.setMobileNo(mobileNo);
		 u.setCountry(country);
		 session.save(u);
		 tx.commit();
		 
		 factory.close();
		 RequestDispatcher rd=request.getRequestDispatcher("ShowUser");
			rd.forward(request, response);
	}

}
