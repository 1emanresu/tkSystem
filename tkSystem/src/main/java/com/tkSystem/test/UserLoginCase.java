package com.tkSystem.test;

import com.tkSystem.dao.mapper.*;
import com.tkSystem.tools.ToolsUtil;
import com.tkSystem.tools.WyMap;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class UserLoginCase {

	@Test
	public void   test1(){
		String md5456=ToolsUtil.getMD5("456");
		String md5=ToolsUtil.getMD5("456");
		
		try {
			String[] conf = { "mybatis-config.xml", "applicationContext.xml" };
			ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
			TkUserMapper  userDao=ac.getBean("tkUserMapper",TkUserMapper.class);
			System.out.println(userDao);
			WyMap wyMap=new WyMap();
			wyMap.put("tkUserName", "456");
			wyMap.put("tkUserPassword", md5456);
			wyMap.put("newtkUserPassword", "123");
			
			System.out.println(userDao.updateByPasswordSelective(wyMap));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	@Test
	public void   getBeans(){
		try {
			String[] conf = { "mybatis-config.xml", "applicationContext.xml" };
			ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
			String [] beansName=ac.getBeanDefinitionNames();
			for (int i = 0; i < beansName.length; i++) {
				System.out.println(beansName[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}


}
