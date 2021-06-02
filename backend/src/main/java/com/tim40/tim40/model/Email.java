package com.tim40.tim40.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "emails")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Email {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private boolean isRead;
	
	@Column
	private String sender;
	
	@ManyToOne
    @JoinColumn(name = "usersender_id", nullable = false)
	private User userSender;
	
	@Column
	private String receiver;
	
	@ManyToOne
    @JoinColumn(name = "userreceiver_id", nullable = false)
	private User userReceiver;
	
	@Column
	private String content;
}
