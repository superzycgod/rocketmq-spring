package cn.zycgod.rocketmq.demo.service;

import cn.zycgod.rocketmq.demo.msg.DemoMsg;

public interface DemoService {
	
	void sendMsg(DemoMsg msg);
	
	void sendTransactionMsg(DemoMsg msg);
	
}
