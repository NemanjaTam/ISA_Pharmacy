package com.tim40.tim40.email.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tim40.tim40.dto.MailDTO;
import com.tim40.tim40.dto.MailListSubscribersDTO;
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
	//	NE MENJATI OVO
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/email_to_subscribers", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean sendMessagesToAllSubscribers(@RequestBody MailListSubscribersDTO emailDTO) throws Exception{
		MailListSubscribersDTO dto = emailDTO;
//		System.out.println(dto.getMailFrom());
//		System.out.println(dto.getMailSubject());
		System.out.println(emailDTO.getMailContent() + " " +"hahah");
//		System.out.println(dto.getMailFrom());
//		System.out.println(dto.getMailFrom()); 
		List<Mail> mails = new ArrayList<Mail>();
//		System.out.println(dto.getMailTo().size());
		for(String email:emailDTO.getMailTo()){
			System.out.println(email);
			Mail mail = new Mail(dto.getMailFrom(),email,dto.getMailCc(),dto.getMailBcc(),
					dto.getMailSubject(),dto.getMailContent(),dto.getContentType(),dto.getAttachments());
			mails.add(mail);
//			System.out.println(mail.getMailTo());
			boolean check = emailService.sendMail(mail);
			if(!check) {
				System.out.println(check);
				return check;
			}
		}
		return true;
	}
	
}
