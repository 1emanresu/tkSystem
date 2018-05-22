package com.tkSystem.test;

import com.tkSystem.dao.entity.TkGood;
import com.tkSystem.dao.entity.TkPlanExecuteClient;
import com.tkSystem.dao.entity.TkPunchcard;
import com.tkSystem.dao.entity.TkPunchcardFeedback;
import com.tkSystem.dao.mapper.*;
import com.tkSystem.tools.ImageUpload;
import com.tkSystem.tools.RedisUtil;
/**
 * 用户注销登陆测试
 */
import com.tkSystem.tools.ToolsUtil;
import com.tkSystem.tools.WyMap;

import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class punchCardTest {

	@Test
	public void   post(){
		try {
			List<String> array1= new ArrayList();
			array1.add("s");
			array1.add("s");
			array1.add("s");
			
			StringBuffer sb=new StringBuffer();
			System.out.println(org.apache.commons.lang.StringUtils.join(array1,";"));
			System.exit(-1);
			String tkPunchcardFeedbackUserId,tkPunchcardFeedbackPlanId,tkPunchCardPhoto;
			String[] conf = { "mybatis-config.xml", "applicationContext.xml" };
			ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
			TkPunchcardMapper  tkPunchcardMapper=ac.getBean("tkPunchcardMapper",TkPunchcardMapper.class);
			TkPunchcardFeedbackMapper  tkPunchcardFeedbackMapper=ac.getBean("tkPunchcardFeedbackMapper",TkPunchcardFeedbackMapper.class);
			TkUserMapper  tkUserMapper=ac.getBean("tkUserMapper",TkUserMapper.class);
			TkPlanMapper  tkPlanMapper=ac.getBean("tkPlanMapper",TkPlanMapper.class);
			WyMap w=new WyMap();
			w.put("tkUserName", "%1%");
			int szi=tkUserMapper.getEmployeeByName(w).size();
			System.out.println(szi);
			
			TkPunchcard wyMap=new TkPunchcard();
			wyMap.setTkPunchcardId(ToolsUtil.getUUID());
			wyMap.setTkPunchcardLoc("商业大街");
			wyMap.setTkPunchcardTime(ToolsUtil.getDate());
			String[] strs= {ToolsUtil.getMemberAvatar()};
			Object[] array = ImageUpload.uploadImg("",strs).toArray();
			tkPunchCardPhoto= org.apache.commons.lang.StringUtils.join(array);
			wyMap.setTkPunchCardPhoto(tkPunchCardPhoto);
			TkPunchcardFeedback wyMap1=new TkPunchcardFeedback();
			wyMap1.setTkPunchcardFeedbackId(ToolsUtil.getUUID());
			tkPunchcardFeedbackPlanId=tkPlanMapper.selectAll().get(0).get("tk_plan_id").toString();
			wyMap1.setTkPunchcardFeedbackPlanId(tkPunchcardFeedbackPlanId);
			WyMap record=new WyMap();
			tkPunchcardFeedbackUserId=tkUserMapper.selectAll().get(2).get("tk_user_id").toString();
			wyMap1.setTkPunchcardFeedbackUserId(tkPunchcardFeedbackUserId);
			System.out.println(tkPunchcardMapper.insertSelective(wyMap));
			System.out.println(tkPunchcardFeedbackMapper.insertSelective(wyMap1));
			System.out.println(String.format("Hi,%sHi,%sHi,%s", tkPunchcardFeedbackUserId,tkPunchcardFeedbackPlanId,tkPunchCardPhoto));
			System.exit(-1);
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void   get(){
		try {
			String[] conf = { "mybatis-config.xml", "applicationContext.xml" };
			ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
			TkPlanExecuteClientMapper  TkPlanExecuteClientMapper=ac.getBean("tkPlanExecuteClientMapper",TkPlanExecuteClientMapper.class);
			WyMap wyMap=new WyMap();
			wyMap.put("tkPlanExecuteClientId", "11");
			wyMap.put("tkPlanExecuteClientName", "11");
			TkPlanExecuteClient updata=TkPlanExecuteClientMapper.selectByPrimaryKey(wyMap);
			System.out.println(updata.getTkPlanExecuteClientName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void   put(){
		try {
			Date as = new Date(new Date().getTime()-24*60*60*1000);
			  SimpleDateFormat matter1 = new SimpleDateFormat("yyyy-MM-dd");
			  String time = matter1.format(as);
			  System.out.println(time);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            System.out.println(time); 
			System.exit(-1);
			String[] conf = { "mybatis-config.xml", "applicationContext.xml" };
			ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
			TkPlanMapper  TkPlanMapper=ac.getBean("tkPlanMapper",TkPlanMapper.class);
			WyMap wyMap=new WyMap();
			wyMap.put("tkPlanId", "2");
			String data="";
            data=time;
			wyMap.put("tkPlanTime", data);
			int updata=TkPlanMapper.updateByPrimaryKey(wyMap);
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
			TkPlanExecuteClientMapper  TkPlanExecuteClientMapper=ac.getBean("tkPlanExecuteClientMapper",TkPlanExecuteClientMapper.class);
			WyMap wyMap=new WyMap();
			wyMap.put("tkPlanExecuteClientId", "11");
			wyMap.put("tkPlanExecuteClientName", "11");
			int updata=TkPlanExecuteClientMapper.deleteByPrimaryKey(wyMap);
			System.out.println(updata);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
