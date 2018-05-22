package com.tkSystem.tools;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

public class WyMap extends HashMap<String, Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static WyMap getParameter(HttpServletRequest request) {
		WyMap wyMap = new WyMap();
		try {
			Enumeration<String> srcList = request.getParameterNames();
			while (srcList.hasMoreElements()) {
				String key, value;
				key = srcList.nextElement();
				value = request.getParameter(key).trim();
				wyMap.put(key, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wyMap;
	}

	public WyMap() {

	}

	public WyMap(String strs, Object str) {
		this.put(strs, str);
	}

	public static WyMap getParameter(HttpServletRequest request, String... str) {
		WyMap wyMap = new WyMap();
		try {
			for (String string : str) {
				String key, value;
				key = string;
				value = request.getParameter(key).trim();
				wyMap.put(key, value);
			}
			String key, value;
			key = "tkUserId";
			value = request.getParameter(key).trim();
			wyMap.put(key, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wyMap;
	}

	public void printStr() {
		System.out.println("=========");
		Set<String> set = this.keySet();
		Iterator iterator = set.iterator();
		while (iterator.hasNext()) {
			Object ob = iterator.next();
			String key = ob.toString();
			if (this.get(key) == null) {
				String value = "null";
				String str = String.format("key=%s : value=%s", key, value);
				System.out.println(str);
				continue;
			}
			String value = this.get(key).toString();
			String str = String.format("key=%s : value=%s", key, value);
			System.out.println(str);
		}
	}

}
