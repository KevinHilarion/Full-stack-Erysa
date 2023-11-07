package com.erysa.system.erysasystem.servicio;

import java.io.UnsupportedEncodingException;
import javax.mail.MessagingException;

public interface EmailServicio {

	 void enviarEmail(String to, String subject, String body) throws MessagingException, UnsupportedEncodingException;
	 
	 void enviarEmail2(String to, String subject, String body) throws MessagingException, UnsupportedEncodingException;
}
