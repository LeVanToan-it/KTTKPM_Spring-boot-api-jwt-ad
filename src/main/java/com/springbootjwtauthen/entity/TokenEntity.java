package com.springbootjwtauthen.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="t_token")
@Getter
@Setter
public class TokenEntity extends BaseEntity {
	@Column(length = 1000)
	private String token;
	
	@Column(name="tokenexpdate")
	private Date tokenExpDate;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getTokenExpDate() {
		return tokenExpDate;
	}

	public void setTokenExpDate(Date tokenExpDate) {
		this.tokenExpDate = tokenExpDate;
	}
	
	
}