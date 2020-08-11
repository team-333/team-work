package com.itbank.vo;

/*MEMBERID	VARCHAR2(20 BYTE)
EMAIL	VARCHAR2(20 BYTE)
PASSWORD	VARCHAR2(20 BYTE)
USERNAME	VARCHAR2(20 BYTE)
ROOMNUMBER	VARCHAR2(20 BYTE)
TEAMIDS	VARCHAR2(1000 BYTE)
PICTUREURL	VARCHAR2(1000 BYTE)
INTRODUCE	VARCHAR2(1000 BYTE)*/

public class MembersVO {
	private	int memberId;
	private String username;
	private String password;
	private String email;
	private String pictureUrl;
	private String introduce;
	private String introduceContext;
	private String NullChk;
	
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPictureUrl() {
		return pictureUrl;
	}
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getIntroduceContext() {
		return introduceContext;
	}
	public void setIntroduceContext(String introduceContext) {
		this.introduceContext = introduceContext;
	}
	public String getNullChk() {
		return NullChk;
	}
	public void setNullChk(String nullChk) {
		NullChk = nullChk;
	}

	
}
