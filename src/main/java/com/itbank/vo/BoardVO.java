package com.itbank.vo;

import java.util.Date;

public class BoardVO {
	private int teamid;
	private int memberid; 
	private int num;              
	private String writer; 
	private Date time;
	private String context;
	private int favorit;
	private String inherence;
	
	public int getTeamid() {
		return teamid;
	}
	public void setTeamid(int teamid) {
		this.teamid = teamid;
	}
	public int getMemberid() {
		return memberid;
	}
	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public int getFavorit() {
		return favorit;
	}
	public void setFavorit(int favorit) {
		this.favorit = favorit;
	}
	public String getInherence() {
		return inherence;
	}
	public void setInherence(String inherence) {
		this.inherence = inherence;
	}
	
}
