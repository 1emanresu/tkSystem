package com.tkSystem.tools;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {
	// jedis连接池
	static JedisPool pool;
	// jedis 实例
	static Jedis jedis;
	// jedis 实例
	static List tokenList;
	// jedis连接池 实例初始化
	static {
		// jedis连接地址信息 地址 112.74.179.74 端口 6379
		pool = getInstance();
		jedis = initJedis();
		// setTokenList();
	}

	public static synchronized JedisPool getInstance() {
		if (pool == null) {
			synchronized (JedisPool.class) {
				if (pool == null) {
					JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
					// jedis连接池连接最大个数
					jedisPoolConfig.setMaxTotal(8);
					pool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379);
				}
			}
		}
		return pool;
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
	public static long saveToken(String tkUserToken) {
		return jedis.lpush("tokenList", tkUserToken);
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
		return jedis.lrem("tokenList", 0, tkUserToken);
	}

	// 从服务器获得tokenList集合方法
	public static List getTokenList() {
		setTokenList();
		return tokenList;
	}

	static void setTokenList() {
		tokenList = jedis.lrange("tokenList", 0, jedis.llen("tokenList"));
	}

	public static int getTokenState(String token) {
		return getTokenList().indexOf(token);
	}

	public static void main(String[] args) {
		new Runnable() {
			int k = 0;

			public void run() {
				while (1 == 1) {

					System.out.println("1");
					System.out.println(RedisUtil.valiToken("36c77990c8584246856dd7b56e0bb8ea", "152205226151757"));
					k++;
				}
			}

			@Override
			protected void finalize() throws Throwable {
				// TODO Auto-generated method stub
				System.out.println(k);
				super.finalize();
			}
		}.run();
		;
		new Runnable() {
			int k = 0;

			public void run() {
				while (1 == 1) {
					System.out.println("2");
					System.out.println(RedisUtil.valiToken("36c77990c8584246856dd7b56e0bb8ea", "152205226151757"));
					k++;
				}
			}

			@Override
			protected void finalize() throws Throwable {
				// TODO Auto-generated method stub
				System.out.println(k);
				super.finalize();
			}
		}.run();
		;

	}
}
