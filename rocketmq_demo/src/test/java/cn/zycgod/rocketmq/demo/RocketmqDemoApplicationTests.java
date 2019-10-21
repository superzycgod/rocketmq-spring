package cn.zycgod.rocketmq.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import cn.zycgod.rocketmq.demo.msg.DemoMsg;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RocketmqDemoApplicationTests {
	
	@Resource
    private RocketMQTemplate rocketMQTemplate;
	
	private static int msgId = 0;
	
	@Test
	public void contextLoads() {
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.execute(new Runnable() {
			@Override
			public void run() {
				rocketMQTemplate.convertAndSend("demo-topic-1", new DemoMsg(msgId++, "测试消息"));
			}
		});
		
		executorService.execute(new Runnable() {
			@Override
			public void run() {
				rocketMQTemplate.sendMessageInTransaction("demo", "demo-topic-1", MessageBuilder.withPayload(new DemoMsg(msgId++, "事务消息测试")).build(), null);
			}
		});
		
	}

}
