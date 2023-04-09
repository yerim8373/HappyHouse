package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.dto.Message;

public interface MessageService {
	public List<Message> getReceiveMessageList(String id);
	public List<Message> getSendMessageList(String id);
	public boolean sendMessage(Message message);
	public Message readMessage(int messageno, String id);
	public boolean deleteSendMessage(int messageno);
	public boolean deleteRecvMessage(int messageno);
}
