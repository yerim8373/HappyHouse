package com.ssafy.happyhouse.dto;

public class Message {
	int messageno; 
	String sender;
	String receiver;
	String title;
	String content;
	String regtime;
	String readstate;
	
	public int getMessageno() {
		return messageno;
	}
	public void setMessageno(int messageno) {
		this.messageno = messageno;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegtime() {
		return regtime;
	}
	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}
	public String getReadstate() {
		return readstate;
	}
	public void setReadstate(String readstate) {
		this.readstate = readstate;
	}
	
	public Message(int messageno, String sender, String receiver, String title, String content, String regtime,
			String readstate) {
		super();
		this.messageno = messageno;
		this.sender = sender;
		this.receiver = receiver;
		this.title = title;
		this.content = content;
		this.regtime = regtime;
		this.readstate = readstate;
	}
	
	public Message() {
		super();
	}
	
	@Override
	public String toString() {
		return "Message [messageno=" + messageno + ", sender=" + sender + ", receiver=" + receiver + ", title=" + title
				+ ", content=" + content + ", regtime=" + regtime + ", readstate=" + readstate + "]";
	}
}
