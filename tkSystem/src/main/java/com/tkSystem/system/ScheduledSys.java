package com.tkSystem.system;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tkSystem.dao.mapper.TkSystemMapper;
import com.tkSystem.tools.ToolsUtil;
import com.tkSystem.tools.WyMap;

import java.text.SimpleDateFormat;

//@Component
public class ScheduledSys {
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	final Logger logger = Logger.getLogger("com.tkSystem.system");

	/** * 构造函数中打印初始化时间 */
	public ScheduledSys() {
		logger.info(String.format("Current time: %s", dateFormat.format(new Date())));

	}

	/**
	 * 每日凌晨更改刷新客户资源池
	 */
	@Scheduled(cron = "0 0 0 ? * *")
	public void testCron() throws Exception {
		System.out.println("0 0 0 ? * *");
		test();
		logger.info(String.format("Current time: %s", dateFormat.format(new Date())));
	}

	public void test() throws Exception {
		try {
			String[] conf = { "mybatis-config.xml", "applicationContext.xml" };
			ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
			TkSystemMapper TkSystemMapper = ac.getBean("tkSystemMapper", TkSystemMapper.class);
			// 公共客户
			WyMap wyMap1 = new WyMap();
			wyMap1.put("tk_client_type_id", "1");
			List<WyMap> list1 = TkSystemMapper.selectFreshdate(wyMap1);
			// 可成交客户
			WyMap wyMap2 = new WyMap();
			wyMap2.put("tk_client_type_id", "2");
			List<WyMap> list2 = TkSystemMapper.selectFreshdate(wyMap2);
			// 待跟进客户
			WyMap wyMap3 = new WyMap();
			wyMap3.put("tk_client_type_id", "3");
			List<WyMap> list3 = TkSystemMapper.selectFreshdate(wyMap3);
			System.out.println(list3.size());
			System.out.println(list2.size());
			System.out.println(list1.size());
			/*
			 * 七天后客户流入公共资源,用户共同拥有跟进权限
			 */
			String nowDate = ToolsUtil.getDate();
			for (WyMap wyMap : list1) {
				if (wyMap.get("tk_client_fresh_date") == null
						|| wyMap.get("tk_client_fresh_date").toString().isEmpty()) {
					fd(TkSystemMapper, wyMap);
					continue;
				}
				String tk_client_fresh_datewy = wyMap.get("tk_client_fresh_date").toString();
				long days = ToolsUtil.getAmongDateToDay(nowDate, tk_client_fresh_datewy);
				System.out.println(days);
				if (days > 6) {
					String tk_client_type_id, tk_client_id;
					fd(TkSystemMapper, wyMap);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void fd(TkSystemMapper TkSystemMapper, WyMap wyMap) {
		WyMap WyMap = new WyMap();
		WyMap.put("tk_client_type_id", "0");
		WyMap.put("tk_client_id", wyMap.get("tk_client_id").toString());
		TkSystemMapper.updateClienType(WyMap);
		System.out.println(WyMap);
	}
}
