package com.ygdxd.security.confg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configurable
public class RedisTokenStoreConfiguration {
	
	@Bean
	@Autowired
	public TokenStore tokenStore(JedisConnectionFactory jedisConnectionFactory){
		return new InMemoryTokenStore();
	}

}
