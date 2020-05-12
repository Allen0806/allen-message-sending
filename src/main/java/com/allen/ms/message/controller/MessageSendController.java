package com.allen.ms.message.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.allen.mf.message.process.dto.MessageDTO;
import com.allen.mf.message.process.feign.MessageReceiveClient;
import com.allen.tool.result.BaseResult;

/**
 *
 *
 * @author Allen
 * @date 2020年5月12日
 * @since 1.0.0
 */
@RestController
@RequestMapping("/ms")
public class MessageSendController {
	
	@Autowired
	private MessageReceiveClient messageReceiveClient;

	@PostMapping("/send")
	@ResponseBody
	public BaseResult<Object> send(){
		MessageDTO message = new MessageDTO();
		message.setMessageNo("202005120001");
		return messageReceiveClient.receive(message);
	}
}
