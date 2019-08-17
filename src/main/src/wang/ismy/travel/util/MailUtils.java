package wang.ismy.travel.util;

import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.MailerBuilder;
import org.simplejavamail.mailer.config.TransportStrategy;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailUtils {

    private static final Mailer mailer = MailerBuilder
            .withSMTPServer("smtp.mxhichina.com", 25, "test@ismy.wang", "rm-rf1024")
            .withTransportStrategy(TransportStrategy.SMTP_TLS)
            .withSessionTimeout(10 * 1000)
            .clearEmailAddressCriteria() // turns off email validation
            .withDebugLogging(true)
            .buildMailer();

    public static void send(String to,String subject,String body) throws MessagingException{
        Email email = EmailBuilder.startingBlank()
                .from("ISMY", "test@ismy.wang")
                .to("N", to)
                .withSubject(subject)
                .withHTMLText(body)
                .buildEmail();

        mailer.sendMail(email);

    }
}
