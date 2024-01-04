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
import com.sun.javafx.tk.quantum.MasterTimer;
import com.sun.org.apache.xpath.internal.compiler.Keywords;

/**
 * Servlet implementation class GetPermissionRequestServlet
 */
@WebServlet("/GetPermissionRequestServlet")
public class GetPermissionRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetPermissionRequestServlet() {
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
		String masterkey = request.getParameter("master");
		
		System.out.print("filename::"+filename+"masterkey::"+masterkey);
		
		String keyword = "";
		String hashblock = "";
		
		Date  date = new Date();
		String currentdate = date.toGMTString();
		
		Connection con = Database.createConnection();
		
		int result = 0;
		
		// FETCHING THE DATA
		try {
			
			PreparedStatement ps = con.prepareStatement("SELECT * FROM senddata where FileName='"+filename+"'");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				 keyword = rs.getString("keywords");
				 hashblock = rs.getString("BlockHash");
			}
			
			System.out.println("keyWord::"+keyword+":hash:"+hashblock);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		//INSERT THE DATA
		String Acceptance = "waiting";
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO permission VALUES(?,?,?,?,?,?)");
			ps.setString(1, currentdate);
			ps.setString(2, filename);
			ps.setString(3, keyword);
			ps.setString(4, masterkey);
			ps.setString(5, Acceptance);
			ps.setString(6, hashblock);
			
			result  = ps.executeUpdate();
			
			if(result != 0){
				response.sendRedirect("PermissionStatus.jsp");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
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
