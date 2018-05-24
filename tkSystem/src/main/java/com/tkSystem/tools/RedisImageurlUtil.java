package com.tkSystem.tools;

import java.util.List;
import java.util.Random;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisImageurlUtil {
	// jedis连接池
	static JedisPool pool;
	// jedis 实例
	static Jedis jedis;
	// jedis 实例
	static List imageurlList;
	// jedis连接池 实例初始化
	static {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		// jedis连接池连接最大个数
		jedisPoolConfig.setMaxTotal(8);
		// jedis连接地址信息 地址 112.74.179.74 端口 6379
		pool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379);
		jedis = initJedis();
		// setimageurlList();
	}

	// jedis连接池创建
	public static Jedis initJedis() {
		try {
			return pool.getResource();
		} catch (Exception e) {
			return null;
		} finally {

			if (jedis != null) {

				jedis.close();

			}

		}

	}

	// jedis关闭实例
	public static void closeJedis() {
		if (jedis != null) {
			jedis.close();
		}
	}

	// 根据键名获取值
	public static String get(String key) {
		return jedis.get(key);
	}

	// Jedis保存键值对
	public static void set(String key, String val, int time) {
		jedis.set(key, val);
		jedis.expire(key, time);
	}

	// Jedis删除
	public static void delete(String... key) {
		jedis.del(key);
	}

	// Jedis连接池关闭
	static void closePool() {
		if (pool != null) {
			pool.close();
		}
	}

	// Jedis saveToken
	public static long saveImageurl (String imageurl) {
		return jedis.lpush("imageurlList", imageurl);
	}
	// Jedis saveToken

	public static long saveToken(String tkUserToken, String tkUserId) {
		jedis.set(tkUserId, tkUserToken);
		// jedis.expire(tkUserId, 1800)
		return 1;
		// return jedis.expire(tkUserId, 60);
	}

	// Jedis valiToken
	public synchronized static boolean valiToken(String tkUserToken, String tkUserId) {
		try {
			return jedis.get(tkUserId).equals(tkUserToken);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	// Jedis logoutToken
	public static long logoutToken(String tkUserToken) {
		return jedis.lrem("imageurlList", 0, tkUserToken);
	}

	// 从服务器获得imageurlList集合方法
	public static List getimageurlList() {
		setimageurlList();
		return imageurlList;
	}

	static void setimageurlList() {
		imageurlList = jedis.lrange("imageurlList", 0, jedis.llen("imageurlList"));
	}

	public static int getTokenState(String token) {
		return getimageurlList().indexOf(token);
	}

	public static void main(String[] args) {
		getImageUrlRandom();
	}

	static void priimageurlList() {
		List list = RedisImageurlUtil.getimageurlList();
		for (Object object : list) {
			String getS = object.toString();
			System.out.println(getS);
		}
	}
	public static String getImageUrlRandom() {
		List list = RedisImageurlUtil.getimageurlList();
		int Max=list.size();
		int Min=0;
		//产生Min-Max之间的数字
		 //  实现原理：
		int random= Integer.parseInt((Math.round(Math.random()*(Max-Min)+Min))+"");
		String url=list.get(random).toString();
		System.out.println(url);
		return url;
	}

}
