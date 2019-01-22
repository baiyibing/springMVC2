package com.test.domain;

public class User {
	private String loginname;
	private String password;
	private String username;
	private String headerImg;
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getHeaderImg() {
		return headerImg;
	}
	public void setHeaderImg(String headerImg) {
		this.headerImg = headerImg;
	}
	@Override
	public String toString() {
		return "User [loginname=" + loginname + ", password=" + password + ", username=" + username + ", headerImg="
				+ headerImg + "]";
	}
}
