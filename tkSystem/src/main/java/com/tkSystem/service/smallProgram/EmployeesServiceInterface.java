package com.tkSystem.service.smallProgram;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tkSystem.tools.RetCode;
import com.tkSystem.tools.WyMap;

public interface EmployeesServiceInterface {

	/**
	 * 申请物料 tkGoodApplyUserId 申请人ID tkGoodApplyId 物料ID tkGoodApplyName 物料名称
	 * 
	 */
	RetCode postApplicationMaterials(WyMap paMap);

	/**
	 * 申请物料 tkGoodApplyUserId 申请人ID tkGoodApplyId 物料ID tkGoodApplyName 物料名称
	 * 
	 */
	RetCode getApplicationMaterials(WyMap paMap);

	RetCode getApplicationMaterialss(WyMap wyMap1);

	/**
	 * 保存打卡信息 tkPunchcardTime 打卡时间 tkPunchcardPerson 打卡人 tkPunchcardLoc 打卡位置，上传经纬度
	 *
	 */
	RetCode postClockIn(HttpServletRequest request, HttpServletResponse response);

	/**
	 * 打卡超时信息反馈 tkPunchcardFeedbackUserId 打卡人ID tkPunchcardFeedbackTime 打卡时间
	 * tkPunchcardFeedbackPlanId 任务ID rwDate 任务开始时间
	 */
	RetCode postClockInFeedback(WyMap paMap);

	/**
	 * 上传客户信息
	 */
	RetCode postSaveCustomerInformation(WyMap paMap);

	/**
	 * 查看拓客任务进度
	 */
	RetCode getTaskSchedule(WyMap paMap);

	/**
	 * 任务报备
	 */
	RetCode postTaskFeedback(WyMap paMap);

	/**
	 * 报备申请 查询
	 */
	RetCode getTaskFeedback(WyMap paMap);

	/**
	 * 查看打卡记录
	 */
	RetCode getClockIn(HttpServletRequest request, HttpServletResponse response);

	RetCode postTaskFeedback(HttpServletRequest request);

	RetCode getPlanByUserId(HttpServletRequest request);

}