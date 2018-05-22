package com.tkSystem.tools;

import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONObject;

public class RetCode extends HashMap {

	/**
	 * 获取到成功状态吗的RetCode
	 **/
	public static RetCode getSuccessCode() {
		RetCode retCode = new RetCode();
		retCode.put("code", "1");
		retCode.put("msg", "成功");
		return retCode;
	}

	/**
	 * 获取到失败状态吗的RetCode
	 **/
	public static RetCode getErrorCode() {
		RetCode retCode = new RetCode();
		retCode.put("code", "0");
		retCode.put("msg", "");
		return retCode;
	}

	/**
	 * 获取到失败状态吗的RetCode,并且设置失败信息
	 **/
	public static RetCode getErrorCode(String message) {
		RetCode retCode = new RetCode();
		retCode.put("code", "0");
		retCode.put("msg", message);
		return retCode;
	}

	/**
	 * 获取到成功状态吗的RetCode,并且设置失败信息
	 **/
	public static RetCode getSuccessCode(String message) {
		RetCode retCode = new RetCode();
		retCode.put("code", "1");
		retCode.put("msg", message);
		return retCode;
	}
	/**
	 * 获取到成功状态吗的RetCode,并且设置data信息
	 **/
	public static RetCode getSuccessCode(Object data) {
		RetCode retCode = new RetCode();
		retCode.put("code", "1");
		retCode.put("data", data);
		retCode.put("msg", "参数信息");
		return retCode;
	}
	/**
	 * 获取到成功状态吗的RetCode,并且设置data信息,并且设置message信息
	 **/
	public static RetCode getSuccessCode(Object data,String message) {
		RetCode retCode = new RetCode();
		retCode.put("code", "1");
		retCode.put("data", data);
		retCode.put("msg", message);
		return retCode;
	}
	/**
	 * 获取到成功状态吗的RetCode,并且设置data信息
	 **/
	public static RetCode getSuccessCode(List data) {
		RetCode retCode = new RetCode();
		retCode.put("code", "1");
		retCode.put("data", data);
		retCode.put("msg", "参数信息");
		return retCode;
	}
	/**
	 * 获取到成功状态吗的RetCode,并且设置data信息,并且设置message信息
	 **/
	public static RetCode getSuccessCode(List data,String message) {
		RetCode retCode = new RetCode();
		retCode.put("code", "1");
		retCode.put("data", data);
		retCode.put("msg",message);
		return retCode;
	}

	/**
	 * 将实体对象转换为JSON Object转换
	 **/
	public static String toJsonString(RetCode retCode) {
		JSONObject jsonObjecy = JSONObject.fromObject(retCode);
		return jsonObjecy.toString();
	}
@Override
public String toString() {
	return toJsonString(this);
}
}
