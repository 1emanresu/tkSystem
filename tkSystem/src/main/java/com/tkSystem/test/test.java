package com.tkSystem.test;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang.time.DateFormatUtils;
import org.junit.Test;

import com.aliyuncs.exceptions.ClientException;
import com.tkSystem.tools.RedisUtil;
import com.tkSystem.tools.ShortMessageService;
import com.tkSystem.tools.ToolsUtil;

public class test {
    
	public static void test(int i) throws ParseException {
		new Runnable() {
			int k = 0;

			public void run() {
				while (1 == 1) {
					System.out.println(i+"thread");
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
	@Test
	public void test2() throws ParseException {
		try {
			 SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 Date dt1=sDateFormat.parse("2018-04-25 16:21:53");
			 Date dt2=sDateFormat.parse("2017-04-26 16:21:53");
			   if (dt1.getTime() > dt2.getTime()) {
	                System.out.println("dt1 在dt2前");
	            } else if (dt1.getTime() < dt2.getTime()) {
	                System.out.println("dt1在dt2后");
	            } else {
	            }
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	
	}
	@Test
	public void test1() throws ParseException {
		new Runnable() {
			int k = 0;

			public void run() {
				for(int i = 0;i < 4; i++){  
		            Thread thread = new YourThreadTest( );  
		            thread.setName(" user NO."+(i+1));  
		            thread.start();  
		            try {  
		                //线程休眠时间1-5秒，每间隔1-5秒开启一个线程，模拟一个用户进行访问  
		                Thread.sleep(1000*(new Random().nextInt(5)+1));  
		            } catch (InterruptedException e) {  
		            	System.out.println(i);
		            	thread.stop();
		            	thread.destroy();
		                e.printStackTrace();  
		            }  
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
	@Test
	public void test3() throws ParseException {
		try {

			 String s="sdfs";
			 System.out.println(s.split(",")[0]);
		} catch (Exception e) {
e.printStackTrace();		}
	}
	class YourThreadTest extends Thread {  
	  
	    public void run(){  
	    	int k = 0;
	        while (true){  
	        	while (1 == 1) {
					System.out.println("thread");
					System.out.println(RedisUtil.valiToken("36c77990c8584246856dd7b56e0bb8ea", "152205226151757"));
					k++;
				}
	        }  
	    }  
	}  
}
