package cn.zycgod.rocketmq.demo;

import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.protocol.ResponseCode;

public class SwitchCaseTests {

	public static void main(String[] args) {

		int code = ResponseCode.FLUSH_DISK_TIMEOUT;

		switch (code) {
		case ResponseCode.FLUSH_DISK_TIMEOUT:{
			System.out.println("FLUSH_DISK_TIMEOUT");
		}
		case ResponseCode.FLUSH_SLAVE_TIMEOUT:{
			System.out.println("FLUSH_SLAVE_TIMEOUT");
		}
		case ResponseCode.SLAVE_NOT_AVAILABLE: {
			System.out.println("SLAVE_NOT_AVAILABLE");
		}
		case ResponseCode.SUCCESS: {
			SendStatus sendStatus = SendStatus.SEND_OK;
			switch (code) {
			case ResponseCode.FLUSH_DISK_TIMEOUT:
				System.out.println("FLUSH_DISK_TIMEOUT.1");
				sendStatus = SendStatus.FLUSH_DISK_TIMEOUT;
				break;
			case ResponseCode.FLUSH_SLAVE_TIMEOUT:
				System.out.println("FLUSH_SLAVE_TIMEOUT.1");
				sendStatus = SendStatus.FLUSH_SLAVE_TIMEOUT;
				break;
			case ResponseCode.SLAVE_NOT_AVAILABLE:
				System.out.println("SLAVE_NOT_AVAILABLE.1");
				sendStatus = SendStatus.SLAVE_NOT_AVAILABLE;
				break;
			case ResponseCode.SUCCESS:
				System.out.println("SUCCESS.1");
				sendStatus = SendStatus.SEND_OK;
				break;
			default:
				assert false;
				break;
			}

		}
		default:
			break;
		}
	}

}
