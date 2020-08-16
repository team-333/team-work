package com.itbank.vo;

import java.util.Date;

public class BoardCommentVO {

	private int teamid;
	private int memberid;
	private int num;
	private int cmtnum;
	private String writer;
	private String context;
	private Date time;
	
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
	public int getCmtnum() {
		return cmtnum;
	}
	public void setCmtnum(int cmtnum) {
		this.cmtnum = cmtnum;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}

	
}
