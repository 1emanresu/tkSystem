package com.tkSystem.test;

import com.tkSystem.dao.entity.TkGood;
import com.tkSystem.dao.mapper.*;
import com.tkSystem.tools.RedisUtil;
/**
 * 用户注销登陆测试
 */
import com.tkSystem.tools.ToolsUtil;
import com.tkSystem.tools.WyMap;

import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.J2Cache;
import redis.clients.jedis.Jedis;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class J2Cachetest {

	@Test
	public void   test1(){
		    CacheChannel cache = J2Cache.getChannel();
		    
		    //缓存操作
		    cache.set("default", "2", "Hello J2Cache");
		    System.out.println(cache.get("default", "2"));
		    cache.evict("default", "2");
		    System.out.println(cache.get("default", "2"));
		    
		    cache.close();
		}
	
	@Test
	public void   get(){
		try {
			String[] conf = { "mybatis-config.xml", "applicationContext.xml" };
			ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
			TkGoodMapper  tk_good=ac.getBean("tkGoodMapper",TkGoodMapper.class);
			WyMap wyMap=new WyMap();
			wyMap.put("tkGoodId", "111");
			wyMap.put("tkGoodName", "111");
			wyMap.put("tkGoodType", "111");
			wyMap.put("tkGoodPrice", "111");
			wyMap.put("tkGoodInsertTime", "111");
			wyMap.put("tkGoodAmount", "111");
			TkGood updata=tk_good.selectByPrimaryKey(wyMap);
			System.out.println(updata.getTkGoodName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void   put(){
		try {
			String[] conf = { "mybatis-config.xml", "applicationContext.xml" };
			ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
			TkGoodMapper  tk_good=ac.getBean("tkGoodMapper",TkGoodMapper.class);
			WyMap wyMap=new WyMap();
			wyMap.put("tkGoodId", "111");
			wyMap.put("tkGoodName", "123");
			wyMap.put("tkGoodType", "123");
			wyMap.put("tkGoodPrice", "123");
			wyMap.put("tkGoodInsertTime", "123");
			wyMap.put("tkGoodAmount", "123");
			int updata=tk_good.updateByPrimaryKeySelective(wyMap);
			System.out.println(updata);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void   del(){
		try {
			String[] conf = { "mybatis-config.xml", "applicationContext.xml" };
			ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
			TkGoodMapper  tk_good=ac.getBean("tkGoodMapper",TkGoodMapper.class);
			WyMap wyMap=new WyMap();
			wyMap.put("tkGoodId", "111");
			wyMap.put("tkGoodName", "111");
			wyMap.put("tkGoodType", "111");
			wyMap.put("tkGoodPrice", "111");
			wyMap.put("tkGoodInsertTime", "111");
			wyMap.put("tkGoodAmount", "111");
			int updata=tk_good.deleteByPrimaryKey(wyMap);
			System.out.println(updata);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
