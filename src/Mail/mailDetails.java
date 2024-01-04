package Mail;

public class mailDetails {
	
	private String recipient;
	private String msgbody;
	private String subject;
	private String attachment;
	
	public mailDetails() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public mailDetails(String recipient, String msgbody, String subject, String attachment) {
		super();
		this.recipient = recipient;
		this.msgbody = msgbody;
		this.subject = subject;
		this.attachment = attachment;
	}



	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getMsgbody() {
		return msgbody;
	}
	public void setMsgbody(String msgbody) {
		this.msgbody = msgbody;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	
	
	
}
