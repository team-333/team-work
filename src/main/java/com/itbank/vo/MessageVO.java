package com.itbank.vo;

import java.util.List;

public class MessageVO {

 private int teamId;
 private int sender;
 private List<Integer> receiverList;
 private String context;
 private String time;
 private int receiver;
 private int readChk;
 
 
public int getTeamId() {
	return teamId;
}
public void setTeamId(int teamId) {
	this.teamId = teamId;
}
public int getSender() {
	return sender;
}
public void setSender(int sender) {
	this.sender = sender;
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
public int getReceiver() {
	return receiver;
}
public void setReceiver(int receiver) {
	this.receiver = receiver;
}


public int getReadChk() {
	return readChk;
}
public void setReadChk(int readChk) {
	this.readChk = readChk;
}
public List<Integer> getReceiverList() {
	return receiverList;
}
public void setReceiverList(List<Integer> receiverList) {
	this.receiverList = receiverList;
}

	
}
