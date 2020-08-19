package com.itbank.vo;


/*
 * 	TEAMID		NUMBER
	TEAMNAME	VARCHAR2(200 BYTE)
	TEMAINFO	VARCHAR2(1000 BYTE)
	TEAMPUBLIC	NUMBER
	MEMBERNUM	NUMBER
	TEAMPICTURE	VARCHAR2(500 BYTE)
 * */

public class StudyVO {
	private int teamId;
	private String teamName;
	private String teamInfo;
	private int teamPublic;
	private int memberNum;
	private String teamPicture;
	private int delegate;
	private String deleteTime;
	
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getTeamInfo() {
		return teamInfo;
	}
	public void setTeamInfo(String teamInfo) {
		this.teamInfo = teamInfo;
	}
	public int getTeamPublic() {
		return teamPublic;
	}
	public void setTeamPublic(int teamPublic) {
		this.teamPublic = teamPublic;
	}
	public int getMemberNum() {
		return memberNum;
	}
	public void setMemberNum(int memberNum) {
		this.memberNum = memberNum;
	}
	public String getTeamPicture() {
		return teamPicture;
	}
	public void setTeamPicture(String teamPicture) {
		this.teamPicture = teamPicture;
	}
	public int getDelegate() {
		return delegate;
	}
	public void setDelegate(int delegate) {
		this.delegate = delegate;
	}
	public String getDeleteTime() {
		return deleteTime;
	}
	public void setDeleteTime(String deleteTime) {
		this.deleteTime = deleteTime;
	}
	
	
}
