<%@page import="com.secure.SHA512.NoobChain"%>
<%@page import="com.secure.SHA512.Block"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.secure.Database.Database"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%

ArrayList<Block> hashValue = new ArrayList<Block>();

String UserID=session.getAttribute("username").toString();
String FileName=request.getParameter("fileName");
String keywords=request.getParameter("keyWord");
String SecretKey=request.getParameter("key");
String orginalContent = request.getParameter("orginalContent");
String content1 = request.getParameter("content1");
String content2 = request.getParameter("content2");

String[]  packetdata = new String[1];

packetdata[0] = orginalContent;

NoobChain nc = new NoobChain();
hashValue = nc.doblockchain(packetdata);

Connection con = Database.createConnection();


if(orginalContent != null){
	
	Date date = new Date();
	SimpleDateFormat formatter = new SimpleDateFormat();
	
	String currentDate = formatter.format(date);
    PreparedStatement pst=con.prepareStatement("insert into senddata(UserID, FileName, keywords, masterkey, orginalContent,content1,content2,CreationDate,BlockHash)values(?,?,?,?,?,?,?,?,?)");
	
    pst.setString(1, UserID);
    pst.setString(2, FileName);
    pst.setString(3, keywords);
    pst.setString(4, SecretKey);
    pst.setString(5, orginalContent);
    pst.setString(6, content1);
    pst.setString(7, content2);
    pst.setString(8, currentDate);
    pst.setString(9, hashValue.get(0).hash);
    
    int result=pst.executeUpdate();
    
    if(result != 0)
    {	
    	response.sendRedirect("SendData.jsp?success=1");
    }
	
}



%>

</body>
</html>