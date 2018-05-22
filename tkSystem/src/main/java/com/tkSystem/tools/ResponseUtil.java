package com.tkSystem.tools;

import static org.junit.Assert.*;

import org.springframework.beans.factory.annotation.Autowired;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;

/**
 * 将后台处理完的结果写回前端页面,如jsp页面; 或者可用于在ajax异步调用后台方法,该方法处理完相应业务逻辑之后将结果返回,这个结果即通过这个工具类实现
 * 
 * @author 无名
 */
public class ResponseUtil {

	public static void write(HttpServletResponse response, Object o) {
		PrintWriter out = null;
		try {
			if (o.getClass().isArray()) {
				JSONArray jsonArray = JSONArray.fromObject(o);
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json; charset=utf-8");
				out = response.getWriter();
				out.append(jsonArray.toString());
			} else {
				// 将实体对象转换为JSON Object转换
				JSONObject responseJSONObject = JSONObject.fromObject(o);
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json; charset=utf-8");
				out = response.getWriter();
				out.append(responseJSONObject.toString());
				/*
				 * logUtil.debug("返回是\n");
				 * logUtil.debug(responseJSONObject.toString());
				 */
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}

	}
}
