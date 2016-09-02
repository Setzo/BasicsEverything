package maill;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

    public static void main(String[] args) {

        Properties prop = new Properties();

        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");

        String email = "LOGIN@GMAIL.COM";
        String password = "PASSWORD";

        Authenticator auth = new Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password);
            }

        };

        Session session = Session.getDefaultInstance(prop, auth);

        Message msg = new MimeMessage(session);

        try {
            msg.setSubject("Database Test");
            msg.setText("Random text");
            msg.setFrom(new InternetAddress("LOGIN@GMAIL.COM", "GMAIL_REAL_NAME"));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress("EMAIL_ADDRESS_TO_MAIL_TO"));

            Transport.send(msg);

        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println("Done");
    }

}
