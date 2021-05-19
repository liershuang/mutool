package com.mutool.mail.strategy.impl;

import com.mutool.mail.entity.Mail;
import com.mutool.mail.enums.TemplateTypeEnum;
import com.mutool.mail.strategy.MailStrategy;
import freemarker.template.Template;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Locale;
import java.util.Map;

/**
 * @Description: 自定义模板格式发送邮件
 *
 * @author TongWei.Chen 2018-06-15 16:49:18
 * @Project common-boot-email
 */
@Component
public class TemplateMailStrategy implements MailStrategy {
    private static final Logger LOG = LogManager.getLogger("bizLog");

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private TemplateEngine templateEngine;
    @Value("${mutool.mail.template.type:freemarker}")
    private String templateType;
    @Autowired
    private FreeMarkerConfigurer configurer;

    @Override
    public void sendMail(String from, Mail mail) {
        String htmlContent = processTemplateContent(templateType, mail.getTemplateName(), mail.getMaps());
        final MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
        try {
            message.setFrom(from);
            message.setTo(mail.getTo());
            message.setSubject(mail.getTitle());
            message.setText(htmlContent, true);
            javaMailSender.send(mimeMessage);
            LOG.info("模板邮件已经发送{}。", mail.getTo());
        } catch (MessagingException e) {
            e.printStackTrace();
            LOG.error("发送模板邮件【{}】时发生异常！", mail.getTemplateName());
        }
    }

    /**
     * 解析模板
     * todo 改为策略模式
     * @param templateType
     * @param templateName
     * @param params
     * @return
     */
    private String processTemplateContent(String templateType, String templateName, Map<String, Object> params){
        if(TemplateTypeEnum.FREEMARKER.getCode().equals(templateType)){
            try {
                Template template = configurer.getConfiguration().getTemplate(templateName);
                return FreeMarkerTemplateUtils.processTemplateIntoString(template, params);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(TemplateTypeEnum.THYMELEAF.getCode().equals(templateType)){
            final Context ctx = new Context(new Locale(""));
            if (null != params && params.size() != 0) {
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    ctx.setVariable(entry.getKey(), entry.getValue());
                }
            }
            return templateEngine.process(templateName, ctx);
        }
        return "";
    }
}
