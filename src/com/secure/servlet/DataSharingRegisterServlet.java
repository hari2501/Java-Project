package com.secure.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.secure.Interface.Interface;
import com.secure.bean.Register;
import com.secure.implementation.Implementation;

/**
 * Servlet implementation class DataSharingRegisterServlet
 */
@WebServlet("/DataSharingRegisterServlet")
public class DataSharingRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataSharingRegisterServlet() {
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
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		
		System.out.println("username::"+username+"::Email::"+email);
		
		if(password.equals(confirmPassword)){
			Register reg = new Register();
			reg.setUsername(username);
			reg.setEmail(email);
			reg.setPassword(password);
			
			Interface inter = new Implementation();
			int t = inter.datasharingRegister(reg);
			if(t!=0){
				response.sendRedirect("DataSharingRegister.jsp");
			}
		}
	}

}
