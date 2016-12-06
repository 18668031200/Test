package com.ygdxd.redis.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
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
	@Bean
	public JedisConnectionFactory redisConnectionFactory(){
		JedisConnectionFactory jedisConnectionFactory=new JedisConnectionFactory();
		//host地址
		jedisConnectionFactory.setHostName("127.0.0.1");
		jedisConnectionFactory.setPort(6378);
		jedisConnectionFactory.setUsePool(true);
		jedisConnectionFactory.afterPropertiesSet();
		return jedisConnectionFactory;
	}
	@Bean
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
	
	/**
	 * 缂撳瓨绠＄悊鍣?
	 * @param redisTemplate
	 * @return
	 */
	@Bean
	public CacheManager cacheManager(RedisTemplate<String, Object> redisTemplate) {
		RedisCacheManager  cacheManager	=new RedisCacheManager(redisTemplate);
	//璁剧疆杩囨湡鏃堕棿
		cacheManager.setDefaultExpiration(10);
	return cacheManager;

	}

}
