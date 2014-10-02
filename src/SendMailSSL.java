import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.*;
import javax.mail.*;


public class SendMailSSL 
{
	public static void main(String[] args)
	{
		String to = "*******@google.com";
		final String from = "******@google.com";
		final String pw = "************";
		//get the session object 
		Properties props = new Properties();
		props.put("mail.smtp.host","smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator()
		{//inside of Authenticator constructor 
			protected PasswordAuthentication getPasswordAuthentication(){return new PasswordAuthentication(from, pw);}
		}/*end of constructor*/);//end of getDefaultInstance method
		try
		{
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Hello!");
			message.setText("HEY! HEY! HEY! this is a test of sending an email from java program!");
			
			//send message 
			Transport.send(message);
			System.out.println("message sent successfully");
			
		}catch(MessagingException e)
		{
			throw new RuntimeException(e);
		}
		
		
	}// main
		
}


