package com.tkSystem.service.smallProgram;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tkSystem.tools.RetCode;

public interface TkPlanExecuteServiceInterface {

	/**
	 * 拓客任务 (上传拓客客户信息,查看拓客记录,)
	 * 
	 */
	/**
	 * 上传拓客客户信息
	 * 
	 * @param request
	 * @return
	 */
	RetCode postTkPlanExecute(HttpServletRequest request);

	/**
	 * 查看拓客记录
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	RetCode getTkPlanExecute(HttpServletRequest request, HttpServletResponse response);

}