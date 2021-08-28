package com.tim40.tim40.email.service;

import java.util.List;

import com.tim40.tim40.dto.MailListSubscribersDTO;
import com.tim40.tim40.email.model.Mail;

public interface IMailService {
    boolean sendMail(Mail mail);
	boolean sendMultipleEmails(List<Mail> mails);
   
}
