package Mail;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.secure.Interface.EmailService;

public class SendAttachmentInEmail implements EmailService {
	
	  final String senderEmailID = "java.strydo@gmail.com";
	  final String senderPassword = "strydo2021";
	  final String emailSMTPserver = "smtp.gmail.com";
	  final String emailServerPort = "465";
	  final String emailServerSSL = "465";

	public static void main(String[] args) {}

	@Override
	public String sendSimpleMail(mailDetails details) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean sendMailWithAttachment(mailDetails details) {
		
	      // Recipient's email ID needs to be mentioned.
	      String to = "destinationemail@gmail.com";

	      // Sender's email ID needs to be mentioned
	      String from = "iotdata2022@gmail.com";

	      final String username = "iotdata2022@gmail.com";//change accordingly
	      final String password = "******";//change accordingly

	      // Assuming you are sending email through relay.jangosmtp.net
	      String host = "smtp.gmail.com";

	      Properties props = new Properties();
	      props.put("mail.smtp.user",senderEmailID);
		  props.put("mail.smtp.host", emailSMTPserver);
		  props.put("mail.smtp.port", emailServerPort);
		  props.put("mail.smtp.starttls.enable","true");
		  props.put("mail.smtp.auth", "true");
		  props.put("mail.smtp.socketFactory.port",emailServerSSL);
		  props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		  props.put("mail.smtp.socketFactory.fallback", "false");
	      // Get the Session object.
	      Session session = Session.getInstance(props,
	         new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication(username, password);
	            }
	         });

	      mailDetails mail = new mailDetails();
	      try {
	         // Create a default MimeMessage object.
	         Message message = new MimeMessage(session);
	    	 
	    	  
	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail.getRecipient()));

	         // Set Subject: header field
	         message.setSubject("Testing Subject");

	         // Create the message part
	         BodyPart messageBodyPart = new MimeBodyPart();

	         // Now set the actual message
	         messageBodyPart.setText("This is message body");

	         // Create a multipar message
	         Multipart multipart = new MimeMultipart();

	         // Set text message part
	         multipart.addBodyPart(messageBodyPart);

	         // Part two is attachment
	         messageBodyPart = new MimeBodyPart();
	         String filename = "/home/manisha/file.txt";
	         DataSource source = new FileDataSource(mail.getAttachment());
	         messageBodyPart.setDataHandler(new DataHandler(source));
	         messageBodyPart.setFileName(filename);
	         multipart.addBodyPart(messageBodyPart);

	         // Send the complete message parts
	         message.setContent(multipart);

	         // Send message
	         Transport.send(message);

	         System.out.println("Sent message successfully....");
	  
	      } catch (MessagingException e) {
	         throw new RuntimeException(e);
	      }
	   
		return true;
	}

}
