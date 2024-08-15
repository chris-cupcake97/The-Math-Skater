package themathskater.Model;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class MailUtil {
	
	public static void sendmail(String recepient)throws Exception{
		System.out.println("Preparing to send mail");
		Properties properties=new Properties();
		properties.put("mail.smtp.auth", "true");//defines if authentication is needed
		properties.put("mail.smtp.starttls.enable", "true");//tls encryption
		properties.put("mail.smtp.host", "smtp.gmail.com");//mail server
		properties.put("mail.smtp.port", "587");//port
		
		String myAccount="themathskater@gmail.com";
		String password="math1997.";
		
	 Session session=Session.getInstance(properties, new Authenticator() {
		 @Override
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(myAccount,password);
		}
	 });
		
		
	   Message message=prepareMessage(session,myAccount,recepient);
	   Transport.send(message);
	   System.out.println("mail sent succesfuly!");
	}

	private static Message prepareMessage(Session session, String myAccount, String recepient) {
		try {
			Message message= new MimeMessage(session);
			 message.setFrom(new InternetAddress(myAccount));
		     message.setRecipient(Message.RecipientType.TO,new InternetAddress(recepient));
			 message.setSubject("welcome");
			message.setText("Account Registered Succesfuly!");
			return message;
		}
		catch(Exception e) {
			Logger.getLogger(MailUtil.class.getName()).log(Level.SEVERE,null,e);
		}
		return null;
		}
	}

