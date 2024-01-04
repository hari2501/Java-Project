package com.secure.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.secure.Database.Database;

/**
 * Servlet implementation class DownloadServlet
 */
@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadServlet() {
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
		String originalContent = request.getParameter("originalContent");
		System.out.println("Filename::"+filename+"::originalContent::"+originalContent);
		String accept = "";
		Connection con = Database.createConnection();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM receiverrequest where FileName='"+filename+"'");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				accept = rs.getString("Acceptance");
				
			}
			System.out.println("Acceptance::"+accept);
			
			int result = 0;
			
			if(accept.equals("Accepted By IAM")){
				HttpSession session = request.getSession();
				   String UserID=session.getAttribute("usernamebyreceiver").toString();
				   Date date = new Date();  
				   SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy HH:mm:ss z");  
				   String strDate = formatter.format(date);  
				   PreparedStatement ps1 = con.prepareStatement("insert into filedownload(UserID, Status,CreationDate)values(?,?,?)");
				   ps1.setString(1, UserID);
				   ps1.setString(2, "Download");
				   ps1.setString(3, strDate);
				   
				   result = ps1.executeUpdate();
				   
				   byte[] bytes = originalContent.getBytes();
				   System.out.println("Bytes:::::"+bytes);

				   response.reset();

				   OutputStream out1 = response.getOutputStream();

				   response.setContentType("application/pdf");
				   response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\".doc");
				   out1.write(bytes);
				   out1.flush();
				   out1.close();
				
			}
			else{
				response.sendRedirect("Download.jsp?success=0");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
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
