package com.allen.message.sending.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.allen.message.forwarding.process.dto.MessageDTO;
import com.allen.message.forwarding.process.feign.MessageReceiveClient;
import com.allen.tool.result.BaseResult;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

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

	@Autowired
	private RestTemplate restTemplate;

	@PostMapping("/send")
	public BaseResult<Object> send() {
		MessageDTO message = new MessageDTO();
		message.setMessageNo("202005120001");
		return messageReceiveClient.receive(message);
	}

	/**
	 * 验证Hystrix
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("/send/{messageNo}")
	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD") }, fallbackMethod = "sendFailure")
	public BaseResult<Object> send(@PathVariable("messageNo") String messageNo) {
		MessageDTO message = new MessageDTO();
		message.setMessageNo(messageNo);
		return restTemplate.postForObject("http://allen-message-forwarding-server/mf/process/message/receive", message,
				BaseResult.class);
	}

	/**
	 * 发送消息Hystrix fallback方法
	 * 
	 * @param messageDTO 消息对象
	 * @return 响应对象
	 */
	public BaseResult<Object> sendFailure(String messageNo) {
		BaseResult<Object> baseResult = new BaseResult<>();
		baseResult.setStatus(BaseResult.STATUS_SYSTEM_FAILURE);
		baseResult.setMessage("系统繁忙，请稍后再试");
		MessageDTO message = new MessageDTO();
		message.setMessageNo(messageNo);
		baseResult.setData(message);
		return baseResult;
	}
}
