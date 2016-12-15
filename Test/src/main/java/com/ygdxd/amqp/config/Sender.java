package com.ygdxd.amqp.config;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender implements RabbitTemplate.ConfirmCallback{
	
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
    public Sender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setConfirmCallback(this); //rabbitTemplate如果为单例的话，那回调就是最后设置的内容
    }
	
	public void send(String id,Object content){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("id", id);
		map.put("content", content);
		CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
		rabbitTemplate.convertAndSend("TopicExchange","queue",map,correlationId);
		
	}

	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		// TODO Auto-generated method stub
		System.out.println(" 回调id:" + correlationData);
        if (ack) {
            System.out.println("消息成功");
        } else {
            System.out.println("消息消费:" + cause);
        }
		
	}

}
