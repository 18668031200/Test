package com.ygdxd.amqp.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;


@Configuration
public class ProducerConfig {
	
	@Bean
	AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory){
		return new RabbitAdmin(connectionFactory());
	}

	@Bean
	public org.springframework.amqp.rabbit.connection.ConnectionFactory connectionFactory() {
		// TODO Auto-generated method stub
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
		cachingConnectionFactory.setPublisherConfirms(true);
		return cachingConnectionFactory;
	}
	
	@Bean
	public Queue queue(RabbitAdmin rabbitAdmin){
		Queue queue = new Queue("queue",true);
		rabbitAdmin.declareQueue(queue);
		return queue;
	}
	
	@Bean
	public TopicExchange topicExchange(RabbitAdmin rabbitAdmin){
		TopicExchange topicExchange = new TopicExchange("TopicExchange");
		rabbitAdmin.declareExchange(topicExchange);
		return topicExchange;
	}
	
	@Bean
	public Binding binding(Queue queue , TopicExchange exchange , RabbitAdmin rabbitAdmin){
		Binding binding=BindingBuilder.bind(queue).to(exchange).with("routingKey");
		rabbitAdmin.declareBinding(binding);
		return binding;
	}
	
//	@Bean
//    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//    //必须是prototype类型
//    public RabbitTemplate rabbitTemplate() {
//        RabbitTemplate template = new RabbitTemplate(connectionFactory());
//        return template;
//    }
	
	
	@Bean
    public RabbitMessagingTemplate rabbitMessagingTemplate(RabbitTemplate rabbitTemplate) {
        RabbitMessagingTemplate rabbitMessagingTemplate = new RabbitMessagingTemplate();
        rabbitMessagingTemplate.setMessageConverter(jackson2Converter());
        rabbitMessagingTemplate.setRabbitTemplate(rabbitTemplate);
        return rabbitMessagingTemplate;
    }

	@Bean
    public MappingJackson2MessageConverter jackson2Converter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        return converter;
    }
	

}
