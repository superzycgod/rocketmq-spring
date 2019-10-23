package cn.zycgod.rocketmq.demo.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.rocketmq.common.message.MessageConst;
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
		/*
		 * 最佳实现：
		 * 每个消息在业务层面的唯一标识码要设置到keys字段，方便将来定位消息丢失问题。服务器会为每个消息创建索引（哈希索引），应用可以通过topic、key来查询这条消息内容，以及消息被谁消费。由于是哈希索引，请务必保证key尽可能唯一，这样可以避免潜在的哈希冲突。
		 */
		headers.put(MessageConst.PROPERTY_KEYS, msg.getMsgId().toString());
		Message<DemoMsg> rocketMsg = MessageBuilder.createMessage(msg, new MessageHeaders(headers));
		
		// 发送事务消息
		rocketMQTemplate.sendMessageInTransaction("demo", "demo-topic-1", rocketMsg, null);
		
		if(msg.getMsgId() <= 0) {
			throw new RuntimeException("消息ID验证失败");
		}
	}

}
