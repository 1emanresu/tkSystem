package com.tkSystem.test;

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
public class UserLogoutCase {

	@Test
	public void   test1(){
		int rets =RedisUtil.getTokenState("4042ee1c9d7743ddb81bc508518c66ae");
		System.out.println(rets);
		System.exit(-1);
		RedisUtil.logoutToken("8990d1f4eddf4a719801417c4fd865d5");
		 List list=RedisUtil.getTokenList();
	        for(int i=0; i<list.size(); i++) {
	            System.out.println("列表项为: "+list.get(i));
	        }
		System.exit(-1);
		RedisUtil.saveToken(ToolsUtil.getUUID());
		RedisUtil.saveToken(ToolsUtil.getUUID());
		RedisUtil.saveToken(ToolsUtil.getUUID());
		
		
	        System.exit(-1);
		try {
			long ret=RedisUtil.logoutToken("0ba0649bd6db4936b88fd7361e389205");
			System.out.println(ret);
		
		} catch (Exception e) {
			e.printStackTrace( );
		}
		 System.exit(-1);
		
        System.out.println("连接成功");
        //存储数据到列表中
        // 获取存储的数据并输出
       
       
		try {
			String[] conf = { "mybatis-config.xml", "applicationContext.xml" };
			ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
			TkUserMapper  userDao=ac.getBean("tkUserMapper",TkUserMapper.class);
			WyMap wyMap=new WyMap();
			wyMap.put("tkUserToken", "78d076b610af4dc7b5a00f89a64aadaa");
			int updata=userDao.distroyToken(wyMap);
			System.out.println(updata);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
}
