package com.itbank.vo;

public class AlarmVO {


	private int receiver;
	private String movePage;
	private String context;
	private String time;
	private int readChk;


	public int getReceiver() {
		return receiver;
	}
	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}
	public String getMovePage() {
		return movePage;
	}
	public void setMovePage(String movePage) {
		this.movePage = movePage;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getReadChk() {
		return readChk;
	}
	public void setReadChk(int readChk) {
		this.readChk = readChk;
	}
}
