package cn.zycgod.rocketmq.demo;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.SmartLifecycle;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RocketmqDemoApplicationTests {
	
	@Autowired
	private GenericApplicationContext ac;
	
	
	@Test
	public void contextLoads() {
		// ac.registerBean(TestBean.class, this::getTestBean);
		// TestBean bean = ac.getBean(TestBean.class);
		// bean.start();
	}
	
	public TestBean getTestBean() {
		return new TestBean();
	}
	
	@Slf4j
	@Configuration
	public static class TestBean implements SmartLifecycle{
		
		private volatile boolean flag = false;
		
		public TestBean() {
			super();
		}
		
		@Override
		public boolean isAutoStartup() {
			log.info("TestBean isAutoStartup");
			return SmartLifecycle.super.isAutoStartup();
		}
		
		@Override
		public void stop(Runnable callback) {
			log.info("TestBean stop callback");
			SmartLifecycle.super.stop(callback);
		}

		@Override
		public void start() {
			log.info("TestBean Start");
			flag = true;
		}

		@Override
		public void stop() {
			log.info("TestBean stop");
			flag = false;
		}

		@Override
		public boolean isRunning() {
			log.info("TestBean isRunning");
			return flag;
		}
		
	}

}
