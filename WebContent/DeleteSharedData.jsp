<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%

String filename = request.getParameter("ffilename");

System.out.print("filename::"+filename);

int result = 0;

try {
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/secure_cipertext","root","root");
	Statement st = con.createStatement();
	PreparedStatement ps = con.prepareStatement("DELETE  from senddata where fileName = '"+filename+"'");

	result = ps.executeUpdate();
	
	if(result != 0){
		response.sendRedirect("ViewSharedData.jsp?success=1");
	}

}catch(Exception e) {
	e.printStackTrace();
}

%>

</body>
</html>