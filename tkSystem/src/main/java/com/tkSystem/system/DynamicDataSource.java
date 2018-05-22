package com.tkSystem.system;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.SmartDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DriverManagerDataSource;

public class DynamicDataSource 
//extends AbstractRoutingDataSource
{

	//@Override
	protected Object determineCurrentLookupKey() {
		/*try {
			Connection conn = null;
			try {
				// DataSourceTransactionManager txManager = new
				// DataSourceTransactionManager(getDataSource());
				SmartDataSource dataSource = null;
				String[] conf = { "mybatis-config.xml", "applicationContext.xml" };
				ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
				com.mchange.v2.c3p0.ComboPooledDataSource djbc = (ComboPooledDataSource) ac.getBean("dataSourceOne");
			    conn = DataSourceUtils.getConnection(djbc);
			    conn.close();
				 Class.forName("com.mysql.jdbc.Driver");//加载驱动类 
				 conn=DriverManager.getConnection("jdbc:mysql://192.168.0.168:3306/tk_manage","root","root");
			} catch (Exception e) {
				throw new Exception();
			}
			DatabaseContextHolder.setCustomerType("dataSourceOne");
			System.out.println("dataSourceOne");
			} catch (Exception e) {
			System.out.println("dataSourceTwo");
			DatabaseContextHolder.setCustomerType("dataSourceTwo");
		}*/
		DatabaseContextHolder.setCustomerType("dataSourceOne");
		System.out.println(DatabaseContextHolder.getCustomerType());
		return DatabaseContextHolder.getCustomerType();
	}

}