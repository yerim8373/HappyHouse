package com.ssafy.happyhouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.Board;
import com.ssafy.happyhouse.dto.Message;
import com.ssafy.happyhouse.service.MessageService;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/message")
public class MessageController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private MessageService messageService;
	
	@GetMapping("/receive/{id}")
	public ResponseEntity<List<Message>> getReceiveMessageList(@PathVariable String id){
		logger.debug("id: {}", id);
		return new ResponseEntity<List<Message>>(messageService.getReceiveMessageList(id), HttpStatus.OK);
	}
	
	@GetMapping("/send/{id}")
	public ResponseEntity<List<Message>> getSendMessageList(@PathVariable String id){
		logger.debug("getid: {}", id);
		return new ResponseEntity<List<Message>>(messageService.getSendMessageList(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> sendMessage(@RequestBody Message message){
		logger.debug("message: {}", message);
		if (messageService.sendMessage(message)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/read/{messageno}/{id}")
	public ResponseEntity<Message> readMessage(@PathVariable int messageno, @PathVariable String id){
		logger.debug("messageno: {}", messageno);
		return new ResponseEntity<Message>(messageService.readMessage(messageno, id), HttpStatus.OK);
	}
	
	@GetMapping("/delsend/{messageno}")
	public ResponseEntity<String> deleteSendMessage(@PathVariable int messageno){
		logger.debug("delsendmessageno: {}", messageno);
		if (messageService.deleteSendMessage(messageno)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/delrecv/{messageno}")
	public ResponseEntity<String> deleteRecvMessage(@PathVariable int messageno){
		logger.debug("delrecvmessageno: {}", messageno);
		if (messageService.deleteRecvMessage(messageno)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
