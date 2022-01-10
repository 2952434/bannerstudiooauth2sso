package club.bannerstudio.bannerstudiooauth2sso.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;

/**
 * @Author: Ljx
 * @Date: 2021/5/22 9:24
 * 发邮件类
 */
@Component
public class SendMail {

    @Autowired
    JavaMailSender javaMailSender;

    int max = 99999;
    int min = 10000;
    Random random = new Random();

    /**
     * 忘记密码时验证邮箱
     *
     * @param email 被发送的邮箱
     * @return String
     */
    public String sendSimpleMail(String email) {
        int s = random.nextInt(max - min + 1) + min;
        String code = String.valueOf(s);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("找回密码");
        message.setFrom("2952434583@qq.com");
        message.setTo(email);
        message.setSentDate(new Date());
        message.setText("你的验证码是：" + code);
        javaMailSender.send(message);
        return code;
    }
}
    