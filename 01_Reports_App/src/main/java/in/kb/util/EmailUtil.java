package in.kb.util;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {

    @Autowired
    private JavaMailSender mailSender;

    public boolean sendMail(String subject, String body, String to){
        try {
            MimeMessage mimeMesg = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(mimeMesg);
            helper.setSubject(subject);
            helper.setText(body,true);
            helper.setTo(to);

            mailSender.send(mimeMesg);

        }catch (Exception e){
            e.printStackTrace();

        }

        return true;
    }
}
