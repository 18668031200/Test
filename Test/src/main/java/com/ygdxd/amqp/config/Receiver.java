package com.ygdxd.amqp.config;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;

@Service
public class Receiver {
	
	public static final Logger log = LoggerFactory.getLogger(Receiver.class);
	
	@RabbitListener(queues = "queue")
	@Transactional(readOnly = false)
	public void listenAndSend(Map<String, Object> map){
		log.info("rabbitqp begin");
		System.out.println(JSON.toJSON(map));
	}

}
