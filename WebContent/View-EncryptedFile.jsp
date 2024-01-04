<%@page import="com.secure.crypto.AES"%>
<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->


<style type="text/css">

body
{

 background: linear-gradient(to right, #008FCA, #11AEEF); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
  
}
	
.contact-form{
    background: #fff;
    margin-top: 10%;
    margin-bottom: 5%;
    width: 70%;
    border-radius: 20px;
}

.contact-form .form-control
{
    border-radius:1rem;
}
.contact-image
{
    text-align: center;
}
.contact-image img{
    border-radius: 6rem;
    width: 11%;
    margin-top: -3%;
    transform: rotate(29deg);
	}
.contact-form form{
    padding: 14%;
}
.contact-form form .row{
    margin-bottom: -7%;
}
.contact-form h3{
    margin-bottom: 8%;
    margin-top: -10%;
    text-align: center;
    color: #0062cc;
}
.contact-form .btnContact {
    width: 50%;
    border: none;
    border-radius: 1rem;
    padding: 1.5%;
    background: #dc3545;
    font-weight: 600;
    color: #fff;
    cursor: pointer;
}
.btnContactSubmit
{
    width: 50%;
    border-radius: 1rem;
    padding: 1.5%;
    color: #fff;
    background-color: #0062cc;
    border: none;
    cursor: pointer;
   
}

.btnContact{

 
}

.text-area{

}

.btn{
	margin-top: -5px;

}

.row{
display: flex;
    flex-direction: column;
    align-items: center;

}


</style>
</head>
<body>

<%

Random r = new Random();
String key="ABCDEF123456789";
char c=key.charAt(r.nextInt(key.length()));
char c1=key.charAt(r.nextInt(key.length()));
char c2=key.charAt(r.nextInt(key.length()));
char c3=key.charAt(r.nextInt(key.length()));
char c4=key.charAt(r.nextInt(key.length()));
char c5=key.charAt(r.nextInt(key.length()));
String secret = c+""+c1+""+c2+""+c3+""+c4+""+c5;
System.out.println("The Value of secret is " +secret);


String datasharing = session.getAttribute("username").toString();

String FileName = request.getParameter("FileName");
String KeyWord = request.getParameter("keyWord");
String orginalContent = request.getParameter("text");

String SecretKey=AES.encrypt99(secret);

String decryptKey=AES.decrypt(SecretKey);

System.out.print("Key"+SecretKey);

String encrypted = AES.encrypt99(orginalContent);

System.out.print("Encrypted Content"+encrypted);


String encryptvalue = new String(encrypted);

String decrypted = AES.decrypt(encryptvalue);

int length=encryptvalue.length();
int lenthsize = length/2;
int len = encryptvalue.length(); 
int n = 2;  
int temp = 0, chars = len/n;  
String[] equalStr = new String [n];  

if(len % n != 0) {  
    System.out.println("Sorry this string cannot be divided into "+ n +" equal parts.");  
} 

else {  
    for(int i = 0; i < len; i = i+chars) {  
        //Dividing string in n equal part using substring()  
        String part = encryptvalue.substring(i, i+chars);  
        equalStr[temp] = part;  
        temp++;  
    }  
    
    System.out.println(n + " equal parts of given string are ");  
    for(int i = 0; i < equalStr.length; i++)
    {  
        System.out.println(equalStr[i]);  
        
     }  
    }  




%>

<div class="container contact-form">
            <div class="contact-image">
                <img src="https://image.ibb.co/kUagtU/rocket_contact.png" alt="rocket_contact"/>
            </div>
            <form method="post" action="Upload.jsp">
                <h3>SendData</h3>
               <div class="row">
                    <div class="col-md-6">
                         <span class="menu-title"><b>FileName</b></span><br/>
                    
                        <div class="form-group">
                            <input type="text" name="fileName"   class="form-control" value="<%=FileName %>" placeholder="File Name *" value="" readonly />
                        </div>
                        <span class="menu-title"><b>Keyword</b></span><br/>
                        		
                        <div class="form-group">
                            <input type="text" name="keyWord"   class="form-control" value="<%=KeyWord%>" placeholder="KeyWord *" value="" readonly />
                        </div>
                        <span class="menu-title"><b>MasterKey</b></span><br/>
                        
                         <div class="form-group">
                            <input type="text" name="key" class="form-control"  value="<%=SecretKey%>" placeholder="KeyWord *" value="" readonly />
                        </div>
                 
                 
                     </div>
                    <div class="col-md-6">
                    <span class="menu-title"><b>Encrypted Data Block 1</b></span><br/>
                        <div class="form-group">
                            <textarea name="content1" class="form-control" placeholder="Your Message *" style="width: 100%; height: 100px;" readonly><%= equalStr[0] %></textarea>
                        </div>
                    </div>
                    
                    <div class="col-md-6">
                     <span class="menu-title"><b>Encrypted Data Block 2</b></span><br/>
                        <div class="form-group">
                            <textarea name="content2" class="form-control" placeholder="Your Message *" style="width: 100%; height: 100px;" readonly><%= equalStr[1] %></textarea>
                        </div>
                    </div>
                    
                     <div class="col-md-6">
                        <div class="form-group">
                            <textarea name="orginalContent" class="form-control text-area" style="display:none" placeholder="Your Message *" style="width: 100%; height: 100px; " readonly><%= orginalContent %></textarea>
                        </div>
                    </div>
                    <input type="submit" name="btnSubmit" class="btn btn-danger" value="Submit" />
                     
                  
                </div>
                       
            </form>
</div>



</body>
</html>