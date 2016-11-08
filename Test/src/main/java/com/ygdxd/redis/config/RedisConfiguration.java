package com.ygdxd.redis.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableCaching
public class RedisConfiguration {
	//连接redis
	public JedisConnectionFactory redisConnectionFactory(){
		JedisConnectionFactory jedisConnectionFactory=new JedisConnectionFactory();
		//host地址
		jedisConnectionFactory.setHostName("127.0.0.1");
		jedisConnectionFactory.setPort(6378);
		jedisConnectionFactory.setUsePool(true);
		jedisConnectionFactory.afterPropertiesSet();
		return jedisConnectionFactory;
	}
	
	public RedisTemplate<?,?> redisTemplate(JedisConnectionFactory jcf){
		RedisTemplate<?, ?> template = new RedisTemplate<>();
		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
		template.setConnectionFactory(redisConnectionFactory());
		template.setKeySerializer(stringRedisSerializer);
		template.setValueSerializer(jackson2JsonRedisSerializer);
		template.afterPropertiesSet();
		return template;
	}

}
