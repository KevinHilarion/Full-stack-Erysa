package com.erysa.system.erysasystem.SpringEmailDemo;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;
import com.erysa.system.erysasystem.servicio.EmailServicio;



@Service
public class EmailSenderService implements EmailServicio{

	@Override
    public void enviarEmail(String to, String subject, String body) throws MessagingException, UnsupportedEncodingException { 

    	Properties props = System.getProperties();
    	props.put("mail.transport.protocol", "smtp");
    	props.put("mail.smtp.port", "587");
    	
    	props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug","false");
        props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        Session session = Session.getDefaultInstance(props);
        session.setDebug(false);
        MimeMessage msg = new MimeMessage(session);
        msg.setSubject(subject);
        msg.setContent(body,"text/html; charset=utf-8");
        msg.setFrom(new InternetAddress("creacioneserysa2023@gmail.com","Creaciones Erysa"));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        Transport transport = session.getTransport();
        transport.connect("smtp.gmail.com", "creacioneserysa2023@gmail.com", "pwxibfxpmkwurdru");
        transport.sendMessage(msg, msg.getAllRecipients());
       
    }

	@Override
	public void enviarEmail2(String to, String subject, String body)
			throws MessagingException, UnsupportedEncodingException {
		Properties props = System.getProperties();
    	props.put("mail.transport.protocol", "smtp");
    	props.put("mail.smtp.port", "587");
    	
    	props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug","false");
        props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        Session session = Session.getDefaultInstance(props);
        session.setDebug(false);
        MimeMessage msg = new MimeMessage(session);
        msg.setSubject(subject);
        msg.setContent(body,"text/html; charset=utf-8");
        msg.setFrom(new InternetAddress("creacioneserysa2023@gmail.com","Creaciones Erysa"));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        Transport transport = session.getTransport();
        transport.connect("smtp.gmail.com", "creacioneserysa2023@gmail.com", "pwxibfxpmkwurdru");
        transport.sendMessage(msg, msg.getAllRecipients());
       
    }

}
