package com.tkSystem.test;

import com.tkSystem.dao.entity.TkGood;
import com.tkSystem.dao.mapper.*;
import com.tkSystem.tools.RedisUtil;
/**
 * 用户注销登陆测试
 */
import com.tkSystem.tools.ToolsUtil;
import com.tkSystem.tools.WyMap;

import redis.clients.jedis.Jedis;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class goodsTest {

	@Test
	public void   test1(){
		
       
		try {
			String[] conf = { "mybatis-config.xml", "applicationContext.xml" };
			ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
			TkGoodMapper  tk_good=ac.getBean("tkGoodMapper",TkGoodMapper.class);
			WyMap wyMap=new WyMap();
			wyMap.put("tkGoodId", "113");
			wyMap.put("tkGoodName", "111");
			wyMap.put("tkGoodType", "111");
			wyMap.put("tkGoodPrice", "111");
			wyMap.put("tkGoodInsertTime", "111");
			wyMap.put("tkGoodAmount", "111");
			int updata=tk_good.insert(wyMap);
			System.out.println(updata);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
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
