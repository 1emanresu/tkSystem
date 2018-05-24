package com.tkSystem.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.tkSystem.tools.WyMap;  
public class ToolsUtil {
	/**
	 * 生成二维码
	 * @param filePath
	 * @param fileName
	 * @param wymap
	 * @return
	 */
	static Logger log=Logger.getLogger("com.tkSystem.tools.ToolsUtil");
	public static void requestIsNull(HttpServletRequest request,String... str) throws Exception {
		for (String string : str) {
			String [] s;
			if(string.contains("#")) {
				 s=string.split("#");
				 if (request.getParameter(s[0]) == null || request.getParameter(s[0]).trim().equals("")) {
						throw new Exception(new String().format("%s格式有误",s[0]+"=>"+s[1]));
					}
			}else {
				if (request.getParameter(string) == null || request.getParameter(string).trim().equals("")) {
					throw new Exception(new String().format("%s格式有误", string));
				}
			}
		}
	}
	public static void MapIsNull(WyMap map,String... str) throws Exception {
		for (String string : str) {
				if (map.get(string) == null || map.get(string).toString().trim().equals("")) {
					throw new Exception(new String().format("%s为空", string));
				}
		}
	}
	public static boolean MatrixToImageWriter( String filePath, String fileName,WyMap wymap) {
		boolean bool=false;
		try {
		    String content = wymap.toString();
		       int width = 200; // 图像宽度  
		       int height = 200; // 图像高度  
		       String format = "png";// 图像类型  
		       Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();  
		       hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");  
		       BitMatrix bitMatrix = new MultiFormatWriter().encode(content,  
		               BarcodeFormat.QR_CODE, width, height, hints);// 生成矩阵  
		       Path path = FileSystems.getDefault().getPath(filePath, fileName);  
		       MatrixToImageWriter.writeToPath(bitMatrix, format, path);// 输出图像  
		       log.info("输出成功.");
		       bool=!false;
		} catch (Exception e) {
		}
		return bool;
	}
	/**
	 * post请求
	 */
	
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


	/**
	 * 0时间戳转日期
	 */
	public static String getDate(String date) {
		String formats = "yyyy-MM-dd HH:mm:ss";
		Long timestamp = Long.parseLong(date) * 1000;
		String date2 = new SimpleDateFormat(formats, Locale.CHINA).format(new Date(timestamp));
		return date2;

	}

	/**
	 * 0时间戳转日期
	 * 
	 * @throws ParseException
	 */
	public static String getAmongDate(String date1, String date2) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date now = df.parse(date1);
		java.util.Date date = df.parse(date2);
		long l = now.getTime() - date.getTime();
		long day = l / (24 * 60 * 60 * 1000);
		long hour = (l / (60 * 60 * 1000) - day * 24);
		long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		return new String("" + day + "天" + hour + "小时" + min + "分" + s + "秒");
	}
	public static long getAmongDateToDay(String date1, String date2) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date now = df.parse(date1);
		java.util.Date date = df.parse(date2);
		long l = now.getTime() - date.getTime();
		long day = l / (24 * 60 * 60 * 1000);
		return day;
	}
	public static long getAmongDateToLong(String date1, String date2) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date now = df.parse(date1);
		java.util.Date date = df.parse(date2);
		long l = now.getTime() - date.getTime();
		return l;
	}

	/**
	 * 1 返回UUId
	 */
	public static String getUUID() {
		String uuidStr = UUID.randomUUID().toString().replaceAll("-", "");
		return uuidStr;
	}

	/**
	 * 2 图片地址前段
	 */
	public static String getImgscIp() {
		String ImgscIp = "http://112.74.179.74:8080/wanyi/static/img/";
		return ImgscIp;
	}

	/**
	 * 3用户默认图片base64编码
	 */
	public static String getMemberAvatar() {
		String img = "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";
		return img;
	}

	/**
	 * 4获取当前时间 时分秒
	 */
	public static String getDate() {
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format.format(date);
		return time;
	}

	/**
	 * 5 获取当前时间 时分秒
	 */
	public static String getNumberDate() {
		String date = String.valueOf(System.currentTimeMillis() / 1000);
		return date;
	}

	/**
	 * 6 获取当前时间 天
	 */
	public static String getDateDay() {
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String time = format.format(date);
		return time;
	}

	/**
	 * 7获取时间差，天
	 * 
	 * @throws ParseException
	 */
	public static int getTimeDifference(String dqDate, String scoringDate) throws ParseException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = df.parse(dqDate);
		Date d2 = df.parse(scoringDate);
		long diff = d1.getTime() - d2.getTime();
		int dateRet = (int) ((diff / ((1000 * 60 * 60) * 24)));
		return dateRet;
	}

	/**
	 * 7获取时间差，分钟
	 * 
	 * @throws ParseException
	 */
	public static String getTimeMinutes(String dqDate, String scoringDate) {
		String retStr = null;
		try {
			SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");// 如2016-08-10 20:40
			String fromDate = simpleFormat.format(simpleFormat.parse(dqDate));
			String toDate = simpleFormat.format(simpleFormat.parse(scoringDate));
			long from = simpleFormat.parse(fromDate).getTime();
			long to = simpleFormat.parse(toDate).getTime();
			Integer minutes = (int) ((to - from) / (1000 * 60));
			retStr = minutes.toString();
		} catch (Exception e) {
			return null;
		}
		return retStr;

	}

	/**
	 * 获取年
	 */
	public static String getYear() {
		Calendar cal = Calendar.getInstance();
		String year = String.valueOf(cal.get(Calendar.YEAR));
		return year;
	}

	/**
	 * 8MD5加密
	 */
	public static String getMD5(String message) {
		MessageDigest messageDigest = null;
		StringBuffer md5StrBuff = new StringBuffer();
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(message.getBytes("UTF-8"));

			byte[] byteArray = messageDigest.digest();
			for (int i = 0; i < byteArray.length; i++) {
				if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
					md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
				else
					md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
			}
		} catch (Exception e) {
			throw new RuntimeException();
		}
		return md5StrBuff.toString();// 字母大写
	}

	/**
	 * 9随机获取六位数
	 */
	public static int getVerificationCode() {
		int ret = (int) ((Math.random() * 9 + 1) * 100000);
		return ret;
	}

	/**
	 * java解析PHP序列化
	 */
	public static HashMap<Object, Object> getUnserializeList(String str) throws Exception {
		str = str.substring(5, str.length() - 1);
		str = str.replaceAll("[s|i]:\\d+:", "").replaceAll("N", "\"\"");

		String[] split = str.split(";");
		HashMap<Object, Object> list = new HashMap<Object, Object>();
		for (int i = 0; i < split.length; i = i + 2) {
			if (i % 2 == 0) {
				list.put(split[i].replaceAll("\"", ""), split[i + 1].replaceAll("\"", ""));
			}
		}
		return list;
	}

	/**
	 * 对象转序列化
	 */
	public static String objTurnSerialization(Object obj) {
		byte[] b = null;
		b = Serializable4php.PHPSerializer.serialize(obj);// 将一个对象序列化后返回byte[]
		String phpserialstr = new String(b);
		return phpserialstr;
	}
	 private static double EARTH_RADIUS = 6371.393;  
	 private static double rad(double d)  
	    {  
	       return d * Math.PI / 180.0;  
	    }  
	/**
	 * 获取两个坐标之间的距离
	 * */
	public static String getDistance(double lat1, double lng1, double lat2, double lng2){
    	double radLat1 = rad(lat1);  
        double radLat2 = rad(lat2);  
        double a = radLat1 - radLat2;      
        double b = rad(lng1) - rad(lng2);  
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +   
         Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));  
        s = s * EARTH_RADIUS;  
        return new java.text.DecimalFormat("#.00").format(s); 
	}
	
	
}
