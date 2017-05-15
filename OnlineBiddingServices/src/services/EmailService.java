package services;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class EmailService
{
	private String emailTo;
	private String emailFrom;
	private String host;
	private Properties properties;
	private Session session;
	private final String username = "onlinebiddingrad@gmail.com";
	private final String password = "RADWPL2016";
	
	public void setEmailTo(String to)
	{
		this.emailTo=to;
	}
	
	public void setEmailFrom(String from)
	{
		this.emailFrom=from;
	}
	
	public void setHost(String host)
	{
		this.host=host;
	}
	
	public void setProperties()
	{		
		properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "587");

	}
	
	public void setSession()
	{
		session = Session.getInstance(properties,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username,password);
					}
				});
	}
	
	public String getEmailTo()
	{
		return emailTo;
	}
	
	public String getEmailFrom()
	{
		return emailFrom;
	}
	
	public String getHost()
	{
		return host;
	}
	
	public Properties getObjProperties()
	{
		return properties;
	}
	
	public Session getCurrentSession()
	{
		return session;
	}
	
	public void sendEmail(String subject, String msg)
	{		
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(emailFrom));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(emailTo));
			message.setSubject(subject);
			message.setText(msg);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
	}

}
