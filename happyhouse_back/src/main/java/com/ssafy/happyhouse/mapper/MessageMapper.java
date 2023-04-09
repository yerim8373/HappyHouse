package com.ssafy.happyhouse.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.Message;

@Mapper
public interface MessageMapper {
	public List<Message> getReceiveMessageList(String receiver);
	public List<Message> getSendMessageList(String sender);
	public int sendMessage(Message message);
	public int readMessage(Map<String, String> map);
	public Message selectMessage(int messageno);
	public int deleteSendMessage(int messageno);
	public int deleteRecvMessage(int messageno);
}
