package com.secure.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.secure.Database.Database;

/**
 * Servlet implementation class ReceiverRequestDataServlet
 */
@WebServlet("/ReceiverRequestDataServlet")
public class ReceiverRequestDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReceiverRequestDataServlet() {
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
		String keyword = request.getParameter("keyword");
		String orignal = request.getParameter("original");
		System.out.println("filename::"+filename+":master::"+master);

		String blockHash = "Waiting";
		String acceptance = "Waiting";
		String originalContent = "";
		System.out.println("blockHash::"+blockHash);

		int result = 0;

		try {

			Connection con = Database.createConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM senddata where FileName='"+filename+"' and masterkey='"+master+"'");
			ResultSet rs = ps.executeQuery();

			System.out.println("ps::"+ps);

			while(rs.next())
			{
				keyword = rs.getString("keywords");
				originalContent = rs.getString("orginalContent");
				System.out.println("blockHash::"+blockHash+":originalContent::"+originalContent);
			}

			Date date = new Date();
			String currentdate = date.toLocaleString();

			System.out.print("currentdate::"+currentdate);

			PreparedStatement ps1 = con.prepareStatement("INSERT INTO receiverrequest VALUES(?,?,?,?,?,?,?)");
			ps1.setString(1, currentdate);
			ps1.setString(2, filename);
			ps1.setString(3, keyword);
			ps1.setString(4, master);
			ps1.setString(5, orignal);
			ps1.setString(6, acceptance);
			ps1.setString(7, blockHash);
			result = ps1.executeUpdate();

			System.out.println("Value of result is ::"+result);

			if(result!=0){
				response.sendRedirect("ResponseStatus.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

	}

}
