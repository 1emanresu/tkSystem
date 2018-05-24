package com.tkSystem.test;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.tkSystem.dao.entity.TkGood;
import com.tkSystem.dao.entity.TkPlanExecuteClient;
import com.tkSystem.dao.entity.TkUser;
import com.tkSystem.dao.mapper.*;
import com.tkSystem.tools.RedisImageurlUtil;
import com.tkSystem.tools.RetCode;
import com.tkSystem.tools.ToolsUtil;
import com.tkSystem.tools.WyMap;

import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.J2Cache;
import net.oschina.j2cache.redis.RedisClient;
import redis.clients.jedis.Jedis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
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

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.SmartDataSource;

import com.tkSystem.dao.mapper.*;

public class ImageUrlTest {
	Connection conn;
	@Before
	public void conn() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// 加载驱动类
		try {
			/*Class.forName("com.mysql.jdbc.Driver");// 加载驱动类
			Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.0.168:3306/tk_manage", "root",
					"root");*/
			  conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/tk_manage", "root",
					"dgclrjroot");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void mainTest() {
		try {
			CacheChannel cache = J2Cache.getChannel();
			// 缓存操作
			cache.set("default", "1", "Hello J2Cache");
			System.out.println(cache.get("default", "1"));
			cache.evict("default", "1");
			System.out.println(cache.get("default", "1"));

			cache.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void updateClientPhoto() {
		try {
			try {
				
				Statement stmt = conn.createStatement();
				// 查询出 客户图片编号
				String sql;
				sql = "SELECT * FROM tk_client_photo";
				ResultSet rs = stmt.executeQuery(sql);

				// 展开结果集数据库
				List<String> list = new ArrayList();
				List<Map> Maplist = new ArrayList();

				while (rs.next()) {
					// 通过字段检索
					ResultSetMetaData rsmd = rs.getMetaData();

					int count = rsmd.getColumnCount();

					String[] name = new String[count];
					WyMap WyMap = new WyMap();
					for (int i = 0; i < count; i++) {
						name[i] = rsmd.getColumnName(i + 1);
						WyMap.put(name[i], rs.getString(name[i]));
					}
					Maplist.add(WyMap);
					// 更新出 客户图片编号
				}
				System.out.println(Maplist.size());
				// RedisClient.Builder.
				System.exit(-1);
				for (String string : list) {
					sql = String.format(
							"update tk_client_photo set tk_client_photo_url = '%s' where tk_client_photo_id ='%s'",
							RedisImageurlUtil.getImageUrlRandom(), string);
					stmt.executeUpdate(sql);
				}

				// 完成后关闭
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void updatePlanPhoto() {
		try {
			try {
				 
				Statement stmt = conn.createStatement();
				// 查询出 客户图片编号
				String sql;
				sql = "SELECT tk_plan_photo_id FROM tk_plan_photo";
				ResultSet rs = stmt.executeQuery(sql);

				// 展开结果集数据库
				List<String> list = new ArrayList();
				while (rs.next()) {
					// 通过字段检索
					String tk_plan_photo_id = rs.getString("tk_plan_photo_id");
					list.add(tk_plan_photo_id);
					// 更新出 客户图片编号
				}
				for (String string : list) {
					sql = String.format(
							"update tk_plan_photo set tk_plan_photo_url = '%s' where tk_plan_photo_id ='%s'",
							RedisImageurlUtil.getImageUrlRandom(), string);
					stmt.executeUpdate(sql);
				}

				// 完成后关闭
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void updatePlanDetailPhoto() {
		try {
			try {
				 
				Statement stmt = conn.createStatement();
				// 查询出 客户图片编号
				String sql;
				sql = "SELECT tk_plan_detail_id FROM tk_plan_detail";
				ResultSet rs = stmt.executeQuery(sql);

				// 展开结果集数据库
				List<String> list = new ArrayList();
				while (rs.next()) {
					// 通过字段检索
					String tk_plan_detail_id = rs.getString("tk_plan_detail_id");
					list.add(tk_plan_detail_id);
					// 更新出 客户图片编号
				}
				for (String string : list) {
					sql = String.format(
							"update tk_plan_detail set tk_plan_detail_photo = '%s' where tk_plan_detail_id ='%s'",
							RedisImageurlUtil.getImageUrlRandom(), string);
					stmt.executeUpdate(sql);
				}

				// 完成后关闭
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void updatetk_punchcard_photo() {
		try {
			try {
				 
				Statement stmt = conn.createStatement();
				// 查询出 客户图片编号
				String sql;
				sql = "SELECT tk_punchcard_photo_id FROM tk_punchcard_photo";
				ResultSet rs = stmt.executeQuery(sql);

				// 展开结果集数据库
				List<String> list = new ArrayList();
				while (rs.next()) {
					// 通过字段检索
					String tk_punchcard_photo_id = rs.getString("tk_punchcard_photo_id");
					list.add(tk_punchcard_photo_id);
					// 更新出 客户图片编号
				}
				for (String string : list) {
					sql = String.format(
							"update tk_punchcard_photo set tk_punchcard_photo_url = '%s' where tk_punchcard_photo_id ='%s'",
							RedisImageurlUtil.getImageUrlRandom(), string);
					stmt.executeUpdate(sql);
				}

				// 完成后关闭
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void updatetk_user() {
		try {
			try {
				 
				Statement stmt = conn.createStatement();
				// 查询出 客户图片编号
				String sql;
				sql = "SELECT tk_user_id FROM tk_user";
				ResultSet rs = stmt.executeQuery(sql);

				// 展开结果集数据库
				List<String> list = new ArrayList();
				while (rs.next()) {
					// 通过字段检索
					String tk_user_id = rs.getString("tk_user_id");
					list.add(tk_user_id);
					// 更新出 客户图片编号
				}
				for (String string : list) {
					sql = String.format("update tk_user set tk_user_head = '%s' where tk_user_id ='%s'",
							RedisImageurlUtil.getImageUrlRandom(), string);
					stmt.executeUpdate(sql);
				}

				// 完成后关闭
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception();
			}

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
