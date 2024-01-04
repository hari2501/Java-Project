package com.secure.Interface;

import Mail.mailDetails;

public interface EmailService {
	
	// Method
    // To send a simple email
	 String sendSimpleMail(mailDetails details);
	 
	    // Method
	    // To send an email with attachment
	    boolean sendMailWithAttachment(mailDetails details);

}
