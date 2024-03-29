package com.tim40.tim40.email.service;

import java.util.List;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.tim40.tim40.dto.MailListSubscribersDTO;
import com.tim40.tim40.email.model.Mail;

@Service
public class MailService implements IMailService {

    private final JavaMailSender javaMailSender;
    private static final Logger logger = LoggerFactory.getLogger(MailService.class);
    private static final String MAIL_FROM = "ursaminor1777@gmail.com";
    @Value("${custom.addr}")
    private String ADDR;
    @Value("${custom.fport}")
    private String PORT;

    @Autowired
    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
//ne menjati ovo
    @Override
    public boolean sendMail(Mail mail) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeHelper = new MimeMessageHelper(mimeMessage, true);

            mimeHelper.setSubject(mail.getMailSubject());
            mimeHelper.setFrom(MAIL_FROM);
            mimeHelper.setTo(mail.getMailTo());
            mimeHelper.setText(mail.getMailContent());
            javaMailSender.send(mimeHelper.getMimeMessage());
            return true;
        } catch (Exception e) {
            logger.error("Exception while sending mail: {}", e.getMessage());
            return false;
        }
    }


	@Override
	public boolean sendMultipleEmails(List<Mail> mails) {
		 MimeMessage mimeMessage = javaMailSender.createMimeMessage();
	        try {
	            MimeMessageHelper mimeHelper = new MimeMessageHelper(mimeMessage, true);

//	            mimeHelper.setSubject(mail.getMailSubject());
//	            mimeHelper.setFrom(MAIL_FROM);
//	            mimeHelper.setTo(mail.getMailTo());
//	            mimeHelper.setText(mail.getMailContent());
	            javaMailSender.send(mimeHelper.getMimeMessage());
	            return true;
	        } catch (Exception e) {
	            logger.error("Exception while sending mail: {}", e.getMessage());
	            return false;
	        }
	}
}
