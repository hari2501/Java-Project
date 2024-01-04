package com.secure.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.secure.Database.Database;
import com.secure.crypto.AES;

/**
 * Servlet implementation class PermissionResponseServlet
 */
@WebServlet("/PermissionResponseServlet")
public class PermissionResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PermissionResponseServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

		String filename = request.getParameter("ffilename");
		String master = request.getParameter("master");
		String status = request.getParameter("status");

		String decryptMasterKey = "";
		System.out.println("filename::"+filename+":masterkey::"+master+":status::"+status);

		Connection con = Database.createConnection();
		AES aes = new AES();

		int result = 0;
		HttpSession session = request.getSession();
		try {
			PreparedStatement ps1 = con.prepareStatement("SELECT * FROM permission where FileName='"+filename+"'");
			ResultSet rs = ps1.executeQuery();
			while(rs.next()){
				String Acceptance = rs.getString("Acceptance");
				System.out.println("$$$$$$$:"+Acceptance);
				if(Acceptance.equals("Accepted")){
					String accept = "";
					System.out.println("success");
					session.setAttribute(accept,"Already Accepted");
					//response.sendRedirect("DataRequestByIAM.jsp?Accept=1");

					RequestDispatcher dd= request.getRequestDispatcher("DataRequestByIAM.jsp?Accept=1");
					dd.forward(request, response);
				}
				else if(Acceptance.equals("Rejected")){
					System.out.print("###");
									response.sendRedirect("DataRequestByIAM.jsp?Reject=1");
				}
				
			}
			if(status.equals("Accepted")){
//				decryptMasterKey = aes.decrypt(master);
				System.out.println("Decrypt::"+decryptMasterKey);
				response.sendRedirect("DataRequestByIAM.jsp");
				//				throw new Exception("dghas");
			}
			
			else{
//				decryptMasterKey = aes.encrypt99(decryptMasterKey);
				response.sendRedirect("DataRequestByIAM.jsp");
			}

			System.out.println("decrypt:"+decryptMasterKey);

			PreparedStatement ps = con.prepareStatement("update permission set masterKey='"+master+"',Acceptance='"+status+"' where fileName='"+filename+"'");

			result = ps.executeUpdate();
			if(result != 0){
				response.sendRedirect("DataRequestByIAM.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
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
