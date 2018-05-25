package com.tkSystem.system;

import java.security.MessageDigest;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Hex;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.tkSystem.tools.ResponseUtil;
import com.tkSystem.tools.RetCode;

import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.J2Cache;

public class SecurityInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("preHandle");
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		System.out.println(request.getRequestURI());
		Enumeration<String> srcList = request.getParameterNames();
		StringBuilder sb = new StringBuilder("");
		while (srcList.hasMoreElements()) {
			String key, value;
			key = srcList.nextElement();
			value = request.getParameter(key).trim();
			sb.append(key + ":" + value + ",");
		}
		/*String src=sb.substring(0,sb.length() - 1).toString();
		System.out.println(src);
		MessageDigest messageDigest = MessageDigest.getInstance("SHA");
		messageDigest.update(src.getBytes());
		byte[] shaBytes = messageDigest.digest();
		String hex= Hex.encodeHexString(shaBytes);
		System.out.println("jdk SHA 1: " +hex);
		request.setAttribute("hex", hex);*/
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("postHandle");

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("afterCompletion");
		/*CacheChannel cache = J2Cache.getChannel();
		String hex=request.getAttribute("hex").toString();
		String names=request.getAttribute("names").toString();
		ResponseUtil.write(response, RetCode.getSuccessCode(cache.get(names, hex).getValue()));
*/
	}

}
