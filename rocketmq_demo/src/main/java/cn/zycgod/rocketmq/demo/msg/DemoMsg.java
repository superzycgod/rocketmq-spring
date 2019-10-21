package cn.zycgod.rocketmq.demo.msg;

import java.io.Serializable;

public class DemoMsg implements Serializable{

	private static final long serialVersionUID = 6461377019600024750L;
	
	private Integer msgId;
	
	private String text;
	
	public DemoMsg() {
		super();
	}

	public DemoMsg(Integer msgId, String text) {
		super();
		this.msgId = msgId;
		this.text = text;
	}

	public Integer getMsgId() {
		return msgId;
	}

	public void setMsgId(Integer msgId) {
		this.msgId = msgId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "DemoMsg [msgId=" + msgId + ", text=" + text + "]";
	}

}
