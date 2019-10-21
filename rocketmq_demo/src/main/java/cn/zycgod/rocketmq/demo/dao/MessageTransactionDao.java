package cn.zycgod.rocketmq.demo.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import cn.zycgod.rocketmq.demo.entity.MessageTransaction;


public interface MessageTransactionDao extends JpaRepository<MessageTransaction, Long>{
	
	MessageTransaction findByBid(String bid);

}
