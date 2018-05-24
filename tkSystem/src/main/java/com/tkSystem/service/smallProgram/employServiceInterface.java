package com.tkSystem.service.smallProgram;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tkSystem.tools.RetCode;

public interface employServiceInterface {

	/**
	 * 拓客人员管理 (绑定拓客人员,解除拓客人员)
	 * 
	 */
	/**
	 * 绑定拓客人员
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	RetCode postEmployee(HttpServletRequest request);

	RetCode getEmployee(HttpServletRequest request);

	RetCode getEmployeeByName(HttpServletRequest request, HttpServletResponse response);

	/**
	 * 
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	RetCode deleteEmployee(HttpServletRequest request, HttpServletResponse response);

	/**
	 * 解除拓客人员
	 * 
	 * @param request
	 * @return
	 */
	RetCode updateGradePid(HttpServletRequest request);

	/**
	 * 用户登陆
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	RetCode userLogin(HttpServletRequest request, HttpServletResponse response);

	Object getTeamIndex(HttpServletRequest request);

}