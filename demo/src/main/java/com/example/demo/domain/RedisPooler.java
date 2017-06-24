package com.example.demo.domain;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


//@Component
public class RedisPooler {

	private JedisPool  jedisPool;
	
	public RedisPooler(JedisPool jedisPool){
		if(jedisPool == null){
			jedisPool = new JedisPool(new JedisPoolConfig(), "localhost");
		}
	}

	public Jedis getRedisPooler(){
		return jedisPool.getResource();
	}
	
}
