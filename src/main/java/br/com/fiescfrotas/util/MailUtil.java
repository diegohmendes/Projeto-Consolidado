package br.com.fiescfrotas.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

public class MailUtil {
	static Logger log = Logger.getLogger(MailUtil.class.getName());

	private static String USER_NAME = "senai.frotas@gmail.com";
	private static String PASSWORD = "ads12345@";

	public static void sentEmail(String subject, String body, String user) {
//		log.info("Enviando senha por e-mail para o usuário: " + user);

		String from = USER_NAME;
		String pass = PASSWORD;
		String[] to = { user };
//		String subject = "Sua nova senha para o Financas";
//		String body = ("Sua nova senha é " + newPass + " .");

		sendFromGMail(from, pass, to, subject, body);
	}

	private static void sendFromGMail(String from, String pass, String[] to, String subject, String body) {
		Properties props = System.getProperties();
		String host = "smtp.gmail.com";
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", pass);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(from));
			InternetAddress[] toAddress = new InternetAddress[to.length];

			for (int i = 0; i < to.length; i++) {
				toAddress[i] = new InternetAddress(to[i]);
			}

			for (int i = 0; i < toAddress.length; i++) {
				message.addRecipient(Message.RecipientType.TO, toAddress[i]);
			}

			message.setSubject(subject);
			message.setText(body);
			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, pass);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			
		} catch (AddressException e) {
			log.error("Erro de endereço: " + e);
			e.printStackTrace();
			
		} catch (MessagingException e) {
			log.error("Erro de mensagem: " + e);
			e.printStackTrace();
		}
	}

}
