package com.mutool.mail.strategy.impl;

import com.mutool.mail.entity.Mail;
import com.mutool.mail.strategy.MailStrategy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * @Description: 简单文本发送邮件
 *
 * @author TongWei.Chen 2018-06-15 16:46:50
 * @Project common-boot-email
 */
@Component
public class SimpleMailStrategy implements MailStrategy {
    private static final Logger LOG = LogManager.getLogger("bizLog");

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendMail(String from, Mail mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        try {
            message.setFrom(from);
            message.setTo(mail.getTo());
            message.setSubject(mail.getTitle());
            message.setText(mail.getContent());
            javaMailSender.send(message);
            LOG.info("纯文本的邮件已经发送给【{}】。", mail.getTo());
        } catch (Exception e) {
            LOG.error("纯文本邮件发送时发生异常！", e);
        }
    }

}
