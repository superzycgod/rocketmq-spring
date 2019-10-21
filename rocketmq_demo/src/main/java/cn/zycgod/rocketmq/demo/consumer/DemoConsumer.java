package cn.zycgod.rocketmq.demo.consumer;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

import cn.zycgod.rocketmq.demo.msg.DemoMsg;
import lombok.extern.slf4j.Slf4j;

@Service
@RocketMQMessageListener(topic = "demo-topic-1", consumerGroup = "demo-consumer_demo-topic-1")
@Slf4j
public class DemoConsumer implements RocketMQListener<DemoMsg>{

	@Override
	public void onMessage(DemoMsg message) {
		log.info("收到消息 {}",message.toString());
	}

}
