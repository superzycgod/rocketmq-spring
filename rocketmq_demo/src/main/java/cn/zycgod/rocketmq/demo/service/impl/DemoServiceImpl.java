package cn.zycgod.rocketmq.demo.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.zycgod.rocketmq.demo.msg.DemoMsg;
import cn.zycgod.rocketmq.demo.service.DemoService;

@Service
public class DemoServiceImpl implements DemoService{
	
	private static Map<Integer, DemoMsg> transactionMap = new ConcurrentHashMap<Integer, DemoMsg>();
	
	@Resource
    private RocketMQTemplate rocketMQTemplate;
	
	@Override
	public void sendMsg(DemoMsg msg) {
		rocketMQTemplate.convertAndSend("demo-topic-1", msg);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void sendTransactionMsg(DemoMsg msg) {
		
		// create rocket msg
		Map<String, Object> headers = new HashMap<>();
		headers.put("bid", msg.getMsgId().toString());
		Message<DemoMsg> rocketMsg = MessageBuilder.createMessage(msg, new MessageHeaders(headers));
		
		// 发送事务消息
		rocketMQTemplate.sendMessageInTransaction("demo", "demo-topic-1", rocketMsg, null);
		
		if(msg.getMsgId() <= 0) {
			throw new RuntimeException("消息ID验证失败");
		}
	}

	@Override
	public DemoMsg queryMsg(Integer msgId) {
		return transactionMap.get(msgId);
	}

}
