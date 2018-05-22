package com.tkSystem.test;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.tkSystem.dao.entity.TkGood;
import com.tkSystem.dao.entity.TkPlanExecuteClient;
import com.tkSystem.dao.entity.TkUser;
import com.tkSystem.dao.mapper.*;
import com.tkSystem.tools.RedisUtil;
import com.tkSystem.tools.RetCode;
import com.tkSystem.tools.ToolsUtil;
import com.tkSystem.tools.WyMap;

import redis.clients.jedis.Jedis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.SmartDataSource;

import com.tkSystem.dao.mapper.*;

public class employeeTest {
	@Test
	public void test() throws Exception {
		try {
			Connection conn;
			Class.forName("com.mysql.jdbc.Driver");// 加载驱动类
			conn = DriverManager.getConnection(
					"jdbc:mysql://192.168.0.168:3306/tk_manage?useUnicode=true&characterEncoding=utf8", "root", "root");
			System.out.println(conn);
			/*
			 * try { // DataSourceTransactionManager txManager = new //
			 * DataSourceTransactionManager(getDataSource()); Connection conn;
			 * SmartDataSource dataSource = null; String[] conf = { "mybatis-config.xml",
			 * "applicationContext.xml" }; ApplicationContext ac = new
			 * ClassPathXmlApplicationContext(conf);
			 * com.mchange.v2.c3p0.ComboPooledDataSource djbc = (ComboPooledDataSource)
			 * ac.getBean("dataSourceOne"); conn = DataSourceUtils.getConnection(djbc);
			 * System.out.println(conn); conn.close();
			 * 
			 * Class.forName("com.mysql.jdbc.Driver");//加载驱动类 conn=DriverManager.
			 * getConnection("jdbc:mysql://192.168.0.168:3306/tk_manage","root","root");//（
			 * url数据库的IP地址，user数据库用户名，password数据库密码） conn.close();
			 * 
			 * } catch (Exception e) { throw new Exception(); }
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String sendPost(String url, Map<String, ?> paramMap) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";

		String param = "";
		Iterator<String> it = paramMap.keySet().iterator();

		while (it.hasNext()) {
			String key = it.next();
			param += key + "=" + paramMap.get(key) + "&";
		}

		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("Accept-Charset", "utf-8");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	@Test
	public void post1() {
		Long l = new Long("-592832000200");
		// System.out.println(l>0);
		for (int i = 0; i < 3; i++) {
			if (l > 0) {
				continue;
			}
			System.out.println(i);
		}
		/*
		 * if(l>0) { System.out.println("l>0"); }
		 */
		System.exit(-1);
		Runnable t1 = new Runnable() {
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					String url, result;
					url = "http://192.168.0.152:8080/tkSystem/user/getByCode";
					Map map = new HashMap();
					String js_code;
					js_code = "authorization_code";
					map.put("code", js_code);
					result = sendPost(url, map);
					System.out.println(result);
				}
			}
		};
		t1.run();

	}

	@Test
	public void post() {
		String url, result;
		url = "http://192.168.0.152:8080/tkSystem/user/getByCode";
		Map map = new HashMap();
		result = sendPost(url, map);
		System.out.println(result);
		System.exit(-1);
		try {
			String[] conf = { "mybatis-config.xml", "applicationContext.xml" };
			ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
			TkSystemMapper TkSystemMapper = ac.getBean("tkSystemMapper", TkSystemMapper.class);
			WyMap wyMap = new WyMap();
			wyMap.put("tkUserId", "152205226151757");
			List list = TkSystemMapper.selectByUserId(wyMap);
			System.out.println(list.size());
			System.exit(-1);
			String tkClientId, tkClientName, tkClientPhone, tkClientLocation, tkClientJoindate, tkClientUserId;
			wyMap.put("tkClientId", ToolsUtil.getUUID());
			wyMap.put("tkClientName", "羽生结弦");
			wyMap.put("tkClientPhone", "+8613345781256");
			wyMap.put("tkClientLocation", "中国香港");
			wyMap.put("tkClientJoindate", ToolsUtil.getDate());
			wyMap.put("tkClientUserId", "152205226151757");
			int updata = TkSystemMapper.insert(wyMap);
			System.out.println(updata);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void get() {
		try {
			String[] conf = { "mybatis-config.xml", "applicationContext.xml" };
			ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
			TkPlanExecuteClientMapper TkPlanExecuteClientMapper = ac.getBean("tkPlanExecuteClientMapper",
					TkPlanExecuteClientMapper.class);
			WyMap wyMap = new WyMap();
			wyMap.put("tkPlanExecuteClientId", "11");
			wyMap.put("tkPlanExecuteClientName", "11");
			TkPlanExecuteClient updata = TkPlanExecuteClientMapper.selectByPrimaryKey(wyMap);
			System.out.println(updata.getTkPlanExecuteClientName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void put() {
		try {
			Date as = new Date(new Date().getTime() - 24 * 60 * 60 * 1000);
			SimpleDateFormat matter1 = new SimpleDateFormat("yyyy-MM-dd");
			String time = matter1.format(as);
			System.out.println(time);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println(time);
			System.exit(-1);
			String[] conf = { "mybatis-config.xml", "applicationContext.xml" };
			ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
			TkPlanMapper TkPlanMapper = ac.getBean("tkPlanMapper", TkPlanMapper.class);
			WyMap wyMap = new WyMap();
			wyMap.put("tkPlanId", "2");
			String data = "";
			data = time;
			wyMap.put("tkPlanTime", data);
			int updata = TkPlanMapper.updateByPrimaryKey(wyMap);
			System.out.println(updata);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void del() {
		try {
			String[] conf = { "mybatis-config.xml", "applicationContext.xml" };
			ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
			TkPlanExecuteClientMapper TkPlanExecuteClientMapper = ac.getBean("tkPlanExecuteClientMapper",
					TkPlanExecuteClientMapper.class);
			WyMap wyMap = new WyMap();
			wyMap.put("tkPlanExecuteClientId", "11");
			wyMap.put("tkPlanExecuteClientName", "11");
			int updata = TkPlanExecuteClientMapper.deleteByPrimaryKey(wyMap);
			System.out.println(updata);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
