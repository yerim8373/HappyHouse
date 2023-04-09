package com.ssafy.happyhouse.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.Message;
import com.ssafy.happyhouse.mapper.MessageMapper;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageMapper messageMapper;
	
	@Override
	public List<Message> getReceiveMessageList(String id) {
		return messageMapper.getReceiveMessageList(id);
	}

	@Override
	public List<Message> getSendMessageList(String id) {
		return messageMapper.getSendMessageList(id);
	}

	@Override
	public boolean sendMessage(Message message) {
		return messageMapper.sendMessage(message) == 1;
	}

	@Override
	public Message readMessage(int messageno, String id) {
		// 읽음표시하고 상세정보 가져오기
		Map<String, String> map = new HashMap();
		map.put("messageno", Integer.toString(messageno));
		map.put("receiver", id);
		messageMapper.readMessage(map);
		return messageMapper.selectMessage(messageno);
	}

	@Override
	public boolean deleteSendMessage(int messageno) {
		return messageMapper.deleteSendMessage(messageno) == 1;
	}

	@Override
	public boolean deleteRecvMessage(int messageno) {
		return messageMapper.deleteRecvMessage(messageno) == 1;
	}

}
