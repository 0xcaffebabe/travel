package wang.ismy.travel.util;

import org.junit.Test;

import javax.mail.MessagingException;

import static org.junit.Assert.*;

public class MailUtilsTest {

    @Test
    public void send() throws MessagingException {

        MailUtils.send("715711877@qq.com","测试邮件","这是一封测试邮件");
    }
}