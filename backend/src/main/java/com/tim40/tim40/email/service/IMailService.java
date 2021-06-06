package com.tim40.tim40.email.service;

import com.tim40.tim40.email.model.Mail;

public interface IMailService {
    boolean sendMail(Mail mail);
}
