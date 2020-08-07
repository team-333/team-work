package com.itbank.vo;

import java.util.List;

public class MessageVO {

 private int teamId;
 private int sender;
 private List<Integer> senderList;
 private String context;
 private String time;
 private int receiver;
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
public List<Integer> getSenderList() {
	return senderList;
}
public void setSenderList(List<Integer> senderList) {
	this.senderList = senderList;
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

}