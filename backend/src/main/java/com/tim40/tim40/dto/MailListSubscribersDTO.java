package com.tim40.tim40.dto;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// ne menjati nista 

public class MailListSubscribersDTO {
	private String mailFrom;
    private List<String> mailTo;
    private String mailCc;
    private String mailBcc;
    private String mailSubject;
    private String mailContent;
    private String contentType;
    private List <Object> attachments;
    
	public MailListSubscribersDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MailListSubscribersDTO(String mailFrom, List<String> mailTo, String mailCc, String mailBcc,
			String mailSubject, String mailContent, String contentType, List<Object> attachments) {
		super();
		this.mailFrom = mailFrom;
		this.mailTo = mailTo;
		this.mailCc = mailCc;
		this.mailBcc = mailBcc;
		this.mailSubject = mailSubject;
		this.mailContent = mailContent;
		this.contentType = contentType;
		this.attachments = attachments;
	}
	public String getMailFrom() {
		return mailFrom;
	}
	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}
	public List<String> getMailTo() {
		return mailTo;
	}
	public void setMailTo(List<String> mailTo) {
		this.mailTo = mailTo;
	}
	public String getMailCc() {
		return mailCc;
	}
	public void setMailCc(String mailCc) {
		this.mailCc = mailCc;
	}
	public String getMailBcc() {
		return mailBcc;
	}
	public void setMailBcc(String mailBcc) {
		this.mailBcc = mailBcc;
	}
	public String getMailSubject() {
		return mailSubject;
	}
	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}
	public String getMailContent() {
		return mailContent;
	}
	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public List<Object> getAttachments() {
		return attachments;
	}
	public void setAttachments(List<Object> attachments) {
		this.attachments = attachments;
	}
    
}




