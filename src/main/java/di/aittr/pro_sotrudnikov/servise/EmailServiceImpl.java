package di.aittr.pro_sotrudnikov.servise;

import di.aittr.pro_sotrudnikov.domen.entity.Sotrudnik;
import di.aittr.pro_sotrudnikov.servise.interfaces.ConfirmationServise;
import di.aittr.pro_sotrudnikov.servise.interfaces.EmailServise;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmailServiceImpl implements EmailServise {

    private final JavaMailSender sender;
    private final Configuration mailConfig;
    private final ConfirmationServise confirmationService;

    public EmailServiceImpl(JavaMailSender sender, Configuration mailConfig, ConfirmationServise confirmationService) {
        this.sender = sender;
        this.mailConfig = mailConfig;
        this.confirmationService = confirmationService;

        mailConfig.setDefaultEncoding("UTF-8");
        mailConfig.setTemplateLoader(new ClassTemplateLoader(EmailServiceImpl.class, "/mail/"));
    }


    @Override
    public void sendConfirmationEmail(Sotrudnik sotrudnik) {

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
        String text = generateConfirmationEmail(sotrudnik);

        try {
            helper.setFrom("89042480071s@gmail.com");
            helper.setTo(sotrudnik.getEmail());
            helper.setSubject("Registration");
            helper.setText(text, true);

            sender.send(message);

        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    private String generateConfirmationEmail(Sotrudnik sotrudnik) {
        try {
            Template template = mailConfig.getTemplate("confirm_reg_mail.ftlh");
            String code = confirmationService.generateConfirmationCode(sotrudnik);

            Map<String, Object> params = new HashMap<>();
            params.put("name", sotrudnik.getImya());
            params.put("link", "http://localhost:8080/register/" + code);

            return FreeMarkerTemplateUtils.processTemplateIntoString(template, params);

        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}
