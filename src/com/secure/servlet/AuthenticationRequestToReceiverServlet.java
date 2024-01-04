package com.secure.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.secure.Database.Database;
import com.secure.SHA512.Block;
import com.secure.SHA512.NoobChain;
import com.secure.crypto.AES;

/**
 * Servlet implementation class AuthenticationRequestToReceiverServlet
 */
@WebServlet("/AuthenticationRequestToReceiverServlet")
public class AuthenticationRequestToReceiverServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthenticationRequestToReceiverServlet() {
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
//		String secret = request.getParameter("secret");
		String keywords = request.getParameter("keyword");
		System.out.println(":filename::"+filename+":SecretKey::"+keywords);
		
		String Acceptance = "Accepted By IAM";
		String OriginalContent = "";
		String status = "";
		String secret = "";
		
		try {
			Connection con = Database.createConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM senddata where FileName='"+filename+"' and keywords='"+keywords+"'");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				OriginalContent = rs.getString("orginalContent");
			}
			System.out.println("OriginalContent::"+OriginalContent);
			
			PreparedStatement ps1 = con.prepareStatement("SELECT * FROM permission where FileName='"+filename+"' and keywords='"+keywords+"'");
			ResultSet rs1 = ps1.executeQuery();
			while(rs1.next()){
				secret = rs1.getString("masterkey");
				status = rs1.getString("Acceptance");
			}
			
			System.out.println("Status of Acceptance::"+status);
			
			ArrayList<Block> hashValue = new ArrayList<Block>();
			String[]  packetdata = new String[1];

			packetdata[0] = OriginalContent;

			NoobChain nc = new NoobChain();
			hashValue = nc.doblockchain(packetdata);
			
			
			
			System.out.println("decryptSecretKey:::"+secret);
			
			if(status.equals("Accepted")){
				int result = 0;
				System.out.println("Success::::");
				PreparedStatement ps2 = con.prepareStatement("update receiverrequest set Acceptance='"+Acceptance+"' , BlockHash='"+hashValue.get(0).hash+"',secretkey='"+secret+"' where FileName='"+filename+"'and keywords='"+keywords+"'");
				result = ps2.executeUpdate();
				System.out.println("PS2::"+ps2);
				if(result!=0){
					response.sendRedirect("AuthenticateRequest.jsp?success=1");
				}
			}
			else{
				response.sendRedirect("AuthenticateRequest.jsp?Error=1");
				throw new Exception("Go Get The Permission with DataSharing::");
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
