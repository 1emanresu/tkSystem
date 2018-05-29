package com.tkSystem.service.smallProgram;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tkSystem.tools.RetCode;
import com.tkSystem.tools.WyMap;

public interface PlanServiceInterface {

	/**
	 * 任务管理 (发布任务 ,分配任务,查看拓客人员任务进度、任务总进度,)
	 * 
	 */
	/**
	 * 发布任务
	 * 
	 * @param request
	 * @return
	 */
	RetCode postPlan(HttpServletRequest request);

	/**
	 * 分配任务
	 * 
	 * @param request
	 * @return
	 */
	RetCode putPlan(HttpServletRequest request);

	RetCode postPlanExecute(HttpServletRequest request);

	/**
	 * 分配任务
	 * 
	 * @param request
	 * @return
	 */
	RetCode getPlanByUserId(HttpServletRequest request);

	/**
	 * 分配任务 昨天
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	RetCode getPlanByUserIdZ(HttpServletRequest request, HttpServletResponse response);

	/**
	 * 分配任务 今天
	 * 
	 * @param request
	 * @return
	 */
	RetCode getTaskToClock(HttpServletRequest request);

	/**
	 * 分配任务 今天
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	RetCode getPlanByUserIdJ(HttpServletRequest request, HttpServletResponse response);

	/**
	 * 任务总进度
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	RetCode getPlan(HttpServletRequest request, HttpServletResponse response);

	RetCode getPlanThree(HttpServletRequest request);

	RetCode getPlanThreeH(HttpServletRequest request, HttpServletResponse response);

	/**
	 * 查看拓客人员任务进度
	 * 
	 * @param request
	 * @return
	 */
	RetCode getEmployeePlan(HttpServletRequest request);

	/**
	 * 读取拓客人员今日任务
	 * 
	 */
	RetCode getTodayTask(WyMap paMap);

	Object getPlanDetailByPlanId(HttpServletRequest request, HttpServletResponse response);

	RetCode getReportByManageDetail(WyMap paMap);

	RetCode putReportByManage(WyMap paMap);

	RetCode getReportByManage(WyMap paMap);

	RetCode getPlanByTime(HttpServletRequest request, HttpServletResponse response);

	Object getAreadyPlan(HttpServletRequest request);

	RetCode getAreadyPlanByTime(HttpServletRequest request, HttpServletResponse response);

	Object getAreadyPlanByDistance(HttpServletRequest request);

	Object getPlanByDistance(HttpServletRequest request, HttpServletResponse response);

	Object getAreadyPlanDistance(HttpServletRequest request);

}