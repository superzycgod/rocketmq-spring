package cn.zycgod.rocketmq.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cn.zycgod.rocketmq.demo.msg.DemoMsg;
import cn.zycgod.rocketmq.demo.service.DemoService;

@RestController
public class DemoController {
	
	@Autowired
	private DemoService demoServcie;
	
	@PostMapping("/msg")
	public ResponseEntity<Object> sendMsg(@RequestBody DemoMsg msg) {
		// 发送普通消息
		demoServcie.sendMsg(msg);
		return ResponseEntity.ok(msg);
	}
	
	@PostMapping("/transactionMsg")
	public ResponseEntity<Object> sendTransactionMsg(@RequestBody DemoMsg msg) {
		// 发送事务消息
		demoServcie.sendTransactionMsg(msg);
		return ResponseEntity.ok(msg);
	}
	
}
