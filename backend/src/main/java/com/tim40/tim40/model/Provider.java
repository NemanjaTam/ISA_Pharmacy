package com.tim40.tim40.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "providers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Provider {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String name;
	@Column(nullable = false)
	private String info;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY,mappedBy="provider")
	private List<Offer> offers; 
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="userReceiver")
	private Set<Email> inbox = new HashSet<Email>();
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="userSender")
	private Set<Email> send = new HashSet<Email>();

}
