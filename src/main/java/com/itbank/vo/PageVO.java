package com.itbank.vo;

public class PageVO {
	private int page;
	private int StartNum;
	private int EndNum;
	private int teamid;
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getStartNum() {
		return StartNum;
	}
	public void setStartNum(int startNum) {
		StartNum = startNum;
	}
	public int getEndNum() {
		return EndNum;
	}
	public void setEndNum(int endNum) {
		EndNum = endNum;
	}
	public int getTeamid() {
		return teamid;
	}
	public void setTeamid(int teamid) {
		this.teamid = teamid;
	}
}
