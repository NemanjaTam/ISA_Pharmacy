package com.tim40.tim40.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tim40.tim40.dto.MailDTO;
import com.tim40.tim40.email.model.Mail;
import com.tim40.tim40.email.service.MailService;

@RestController
@RequestMapping(value = "api/email")
public class EmailController {
	
	private MailService emailService;
	
	@Autowired
	public EmailController(MailService emailService) {
		this.emailService = emailService;
	}
	
	@PostMapping(value = "/email-sent", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean requestAbsence(@RequestBody MailDTO emailDTO) throws Exception{
		Mail mail = new Mail(emailDTO);
		return emailService.sendMail(mail);
	}
}
