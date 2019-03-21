package ua.com.danit.utils;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailSender {

        final String username = "forsmtp90";
        final String password = "w12zw12z";
        private Properties props;

    public MailSender(){
        this.props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.user", username);
        props.put("mail.smtp.password", password);
    }

    public void sendMessage(String recipient, String text) {
            Session session = Session.getInstance(props);
            Message message = new MimeMessage(session);
            try {
                message.setFrom(new InternetAddress(username));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(recipient));
                message.setSubject("Access email on tinder");
                message.setContent(text, "text/html");
                Transport transport = session.getTransport("smtp");
                transport.connect("smtp.gmail.com", username, password);
                transport.sendMessage(message, message.getAllRecipients());
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
    }

