package com.tim40.tim40.dto;

public class ChangePasswordDTO {

	private String password;
    private Long id;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    
    public ChangePasswordDTO() { }

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
    

}
