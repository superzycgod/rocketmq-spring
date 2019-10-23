package cn.zycgod.rocketmq.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

/**
 * 事务消息表
 * @author zhangyanchao
 *
 */
@Data
@Entity
@Table(name = "t_message_transaction")
@ToString
public class MessageTransaction implements Serializable{
	
	private static final long serialVersionUID = 5904412203617946748L;
	
	@Id
    @Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String bid;

}
