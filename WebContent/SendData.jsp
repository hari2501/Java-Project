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

			<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>



<style type="text/css">

body
{
    background: linear-gradient(to right, #008FCA, #11AEEF); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
}
.contact-form
{
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
.contact-image img
{
    border-radius: 6rem;
    width: 11%;
    margin-top: -3%;
    transform: rotate(29deg);
}
.contact-form form
{
    padding: 14%;
}
.contact-form form .row
{
    margin-bottom: -7%;
}
.contact-form h3
{
    margin-bottom: 8%;
    margin-top: -10%;
    text-align: center;
    color: #0062cc;
}
.contact-form .btnContact 
{
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


</style>
</head>
<body>

<div class="container contact-form">
            <div class="contact-image">
                <img src="https://image.ibb.co/kUagtU/rocket_contact.png" alt="rocket_contact"/>
            </div>
            <form method="post" action="View-EncryptedFile.jsp">
                <h3 style="text-transform: uppercase;" >Send Data</h3>
               <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <input type="text" name="FileName" class="form-control" placeholder="FileName *" value="" />
                        </div>
                        
                        <div class="form-group">
                            <input type="text" name="keyWord" class="form-control" placeholder="Keyword *" value="" />
                        </div>
       
                        <div class="form-group">
                            <input type="submit" name="btnSubmit" class="btnContact" value="Submit" />
                        </div>
                    </div>
                    
                    <div class="col-md-6">
                        <div class="form-group">
                            <textarea name="text" class="form-control" placeholder="Your Content *" style="width: 100%; height: 100px;"></textarea>
                        </div>
                    </div>
                </div>
            </form>
	</div>

<script type="text/javascript">


if(test=${param.success==1}){
			
			console.log("File Uploaded");
			swal("Success!", "Data Sent!", "success");

			
		}

</script>


</body>
</html>