package com.secure.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.secure.Interface.Interface;
import com.secure.implementation.Implementation;

/**
 * Servlet implementation class DataSharingLoginServlet
 */
@WebServlet("/DataSharingLoginServlet")
public class DataSharingLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataSharingLoginServlet() {
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
		// TODO Auto-generated method stub
		doGet(request, response);
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		System.out.println("username::"+username+"::Password::"+password);

		
		Interface inter = new Implementation();
		int t = inter.datasharingLogin(username, password);
		if(t!=0){
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			response.sendRedirect("DataSharingHome.jsp");
		}
	}

}
