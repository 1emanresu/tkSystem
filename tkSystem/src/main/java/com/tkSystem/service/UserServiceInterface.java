package com.tkSystem.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tkSystem.tools.RetCode;
import com.tkSystem.tools.WyMap;

public interface UserServiceInterface {

	/**
	 * 用户注册
	 */
	RetCode postUserRegistration(WyMap paMap);

	/**
	 * 修改密码
	 */
	RetCode putModifyPwd(WyMap paMap);

	/**
	 * 用户登陆
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	RetCode userLogin(HttpServletRequest request, HttpServletResponse response);

	RetCode wechatLogin(HttpServletRequest request, HttpServletResponse response);

	/**
	 * 获取手机验证码
	 * 
	 * @throws Exception
	 */
	RetCode getVail(HttpServletRequest request, HttpServletResponse response) throws Exception;

	/**
	 * 验证手机验证码
	 */
	Boolean postVail(HttpServletRequest request, HttpServletResponse response);

	/**
	 * 绑定手机号码 和姓名
	 */
	RetCode putPhoneAndName(HttpServletRequest request, HttpServletResponse response);

}