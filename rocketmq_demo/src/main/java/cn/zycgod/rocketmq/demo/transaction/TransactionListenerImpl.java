package cn.zycgod.rocketmq.demo.transaction;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;

import cn.zycgod.rocketmq.demo.dao.MessageTransactionDao;
import cn.zycgod.rocketmq.demo.entity.MessageTransaction;
import lombok.extern.slf4j.Slf4j;

/**
 * 事务消息监听
 * @author zhangyanchao
 *
 */
@RocketMQTransactionListener(txProducerGroup = "demo")
@Slf4j
public class TransactionListenerImpl implements RocketMQLocalTransactionListener{
	
	private Map<String, Integer> countHashMap = new ConcurrentHashMap<>();
	
	@Autowired
	private MessageTransactionDao mtDao;
	
	/**
	 * 执行本地事务（该方法与本地事务方法在一个事务中）
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
		log.info("executeLocalTransaction for {}" , msg);
		
		MessageTransaction mt = new MessageTransaction();
		mt.setBid((String) msg.getHeaders().get(MessageConst.PROPERTY_KEYS));
		mtDao.save(mt);
		
		return RocketMQLocalTransactionState.UNKNOWN;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
		log.info("checkLocalTransaction for {}" , msg);
		
		String bid = (String) msg.getHeaders().get(MessageConst.PROPERTY_KEYS);
		
		if(mtDao.findByBid(bid) != null) {
			log.info("Found msg in DB , Commit RocketMQ Transaction!");
			return RocketMQLocalTransactionState.COMMIT;
		}
		
		int count = countHashMap.getOrDefault(bid, 0) + 1;
		if(count == 1) {
			countHashMap.remove(bid);
			log.info("CheckLocalTransaction more than 1 times, Rollback RocketMQ Transaction!");
			return RocketMQLocalTransactionState.ROLLBACK;
		}
		
		countHashMap.put(bid, count);
		
		log.info("Not found msg in DB , Wait DB Transaction commit!");
		return RocketMQLocalTransactionState.UNKNOWN;
	}

}
