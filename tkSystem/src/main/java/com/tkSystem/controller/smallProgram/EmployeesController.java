package com.tkSystem.controller.smallProgram;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tkSystem.service.smallProgram.EmployeesServiceInterface;
import com.tkSystem.service.smallProgram.PlanServiceInterface;
import com.tkSystem.tools.CLID;
import com.tkSystem.tools.ImageUpload;
import com.tkSystem.tools.ResponseUtil;
import com.tkSystem.tools.RetCode;
import com.tkSystem.tools.ToolsUtil;
import com.tkSystem.tools.WyMap;

@Controller
@RequestMapping("employees")
public class EmployeesController {

	WyMap wyMap = new WyMap();

	@Autowired
	EmployeesServiceInterface employeesService;
	@Autowired
	PlanServiceInterface planService;
	/**
	 * 获取正在拓客的任务信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("getAreadyPlan")
	public void getAreadyPlan(HttpServletRequest request, HttpServletResponse response) {

		try {
			ResponseUtil.write(response, RetCode.getSuccessCode(planService.getAreadyPlan(request)));
		} catch (Exception e) {
			String msg = "=> tkUserHead不能为空";
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage() + msg));
		}
	}
	@RequestMapping("getAreadyPlanByTime")
	public void getAreadyPlanByTime(HttpServletRequest request, HttpServletResponse response) {

		try {
			ResponseUtil.write(response, RetCode.getSuccessCode(planService.getAreadyPlanByTime(request,response)));
		} catch (Exception e) {
			String msg = "=> tkUserHead不能为空";
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage() + msg));
		}
	}
	@RequestMapping("getAreadyPlanByDistance")
	public void getAreadyPlanByDistance(HttpServletRequest request, HttpServletResponse response) {

		try {
			if ( request.getParameter("longitude") == null||request.getParameter("longitude").trim().equals("")) {
				throw new Exception("longitude格式有误");
			}if (  request.getParameter("latitude") == null||request.getParameter("latitude").trim().equals("") ) {
				throw new Exception("latitude格式有误");
			}
			ResponseUtil.write(response, RetCode.getSuccessCode(planService.getAreadyPlanByDistance(request)));
		} catch (Exception e) {
			String msg = "=>  不能为空";
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage() + msg));
		}
	}
	/**
	 * 申请物料
	 */
	@RequestMapping("postApplicationMaterials")
	public void postApplicationMaterials(HttpServletRequest request, HttpServletResponse response) {
		try {
			if ( request.getParameter("tkPlanId") == null||request.getParameter("tkPlanId").trim().equals("")
					) {
				throw new Exception("任务编号 tkPlanId格式有误");
			}
			if ( request.getParameter("tkGoodName") == null||request.getParameter("tkGoodName").trim().equals("")
					) {
				throw new Exception("物料名字 tkGoodName格式有误");
			}
			if ( request.getParameter("tkGoodApplyGoodAmount") == null||request.getParameter("tkGoodApplyGoodAmount").trim().equals("")
					) {
				throw new Exception("物料数量 tkGoodApplyGoodAmount格式有误");
			}
			if ( request.getParameter("remark") == null||request.getParameter("remark").trim().equals("")
					) {
				throw new Exception("备注 remark格式有误");
			}
			
			WyMap paMap = wyMap.getParameter(request);
			RetCode retCode = employeesService.postApplicationMaterials(paMap);
			ResponseUtil.write(response, retCode);
		} catch (Exception e) {
			String msg = "=> tkGoodApplyName,tkGoodApplyUserId, tkGoodApplyGoodId, tkGoodApplyGoodAmount,tkGoodApplyGoodTime, tkGoodApplyFeedbackTime, tkGoodApplyState不能为空";
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage() + msg));
		}

	}
	/**
	 * .物料申请 查询
	 */
	@RequestMapping("getApplicationMaterials")
	public void getApplicationMaterials(HttpServletRequest request, HttpServletResponse response) {
		try {
			WyMap paMap = wyMap.getParameter(request);
			RetCode retCode = employeesService.getApplicationMaterialss(paMap);
			ResponseUtil.write(response, retCode);
		} catch (Exception e) {
			String msg = " ";
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage() + msg));
		}

	}
	/**
	 * 保存打卡信息
	 */
	@RequestMapping("postClockIn")
	public void postClockIn(HttpServletRequest request, HttpServletResponse response) {
		try {
			if(request.getParameterValues("tkPunchcardPhoto") == null) {
				if ( request.getParameter("tkPunchcardPhoto") == null||request.getParameter("tkPunchcardPhoto").trim().equals("")) {
					throw new Exception("tkPunchcardPhoto格式有误");
				}
			}
			
			if ( request.getParameter("tkPlanId") == null||request.getParameter("tkPlanId").trim().equals("")
					) {
				throw new Exception("tkPlanId格式有误");
			}
			if (request.getParameter("tkPunchcardLoc") == null||request.getParameter("tkPunchcardLoc").trim().equals("")
					) {
				throw new Exception("tkPunchcardLoc格式有误");
			}
			ResponseUtil.write(response, employeesService.postClockIn(request,   response));
		} catch (Exception e) {
			String msg = "=> tkPunchcardLoc 打卡位置, tkPlanId 任务编号, tkPunchcardPhoto工作照片不能为空";
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage() + msg));
		}
	}
	/**
	 * 获取打卡信息
	 */
	@RequestMapping("getClockIn")
	public void getClockIn(HttpServletRequest request, HttpServletResponse response) {
		try {
			ResponseUtil.write(response, employeesService.getClockIn(request,   response));
		} catch (Exception e) {
			String msg = "=> tkPunchcardLoc 打卡位置, tkPlanId 任务编号, tkPunchcardPhoto工作照片不能为空";
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage() + msg));
		}
	}
	/**
	 * 打卡超时信息反馈
	 */
	@RequestMapping("postClockInFeedback")
	public void postClockInFeedback(HttpServletRequest request, HttpServletResponse response) {

		try {
			if ( request.getParameter("tkPunchcardFeedbackUserId") == null||request.getParameter("tkPunchcardFeedbackUserId").trim().equals("")
					) {
					throw new Exception("tkPunchcardFeedbackUserId格式有误");
			}
			if ( request.getParameter("tkPunchcardFeedbackTime") == null||request.getParameter("tkPunchcardFeedbackTime").trim().equals("")
					) {
				throw new Exception("tkPunchcardFeedbackTime格式有误");
			}
			if ( request.getParameter("tkPunchcardFeedbackLatetime") == null||request.getParameter("tkPunchcardFeedbackLatetime").trim().equals("")
					) {
				throw new Exception("tkPunchcardFeedbackLatetime格式有误");
			}
			if ( request.getParameter("tkPunchcardFeedbackManagerid") == null||request.getParameter("tkPunchcardFeedbackManagerid").trim().equals("")
					) {
				throw new Exception("tkPunchcardFeedbackManagerid格式有误");
			}
			if ( request.getParameter("tkPunchcardFeedbackPlanId") == null||request.getParameter("tkPunchcardFeedbackPlanId").trim().equals("")
					) {
				 	throw new Exception("tkPunchcardFeedbackPlanId格式有误");
			}
			WyMap paMap = wyMap.getParameter(request);
			String tkPunchcardFeedbackId = CLID.getID();
			paMap.put("tkPunchcardFeedbackId", tkPunchcardFeedbackId);
			RetCode retCode = employeesService.postClockInFeedback(paMap);
			ResponseUtil.write(response, retCode);
		} catch (Exception e) {
			String msg = "=>   tkPunchcardFeedbackUserId,  tkPunchcardFeedbackTime, tkPunchcardFeedbackLatetime,  tkPunchcardFeedbackManagerid, tkPunchcardFeedbackPlanId不能为空";
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage() + msg));
		}
	}

	
 

	/**
	 * 上传客户信息
	 */
	@RequestMapping("postSaveCustomerInformation")
	public void postSaveCustomerInformation(HttpServletRequest request, HttpServletResponse response) {
		try {
			String tkPlanExecuteClientId, tkPlanExecuteClientName;
			if (  request.getParameter("tkPlanExecuteClientName") == null||request.getParameter("tkPlanExecuteClientName").trim().equals("")
					) {
				throw new Exception("tkPlanExecuteClientName格式有误");
			}
			WyMap paMap = wyMap.getParameter(request);
			tkPlanExecuteClientId =CLID.getID();
			paMap.put("tkPlanExecuteClientId", tkPlanExecuteClientId);
			RetCode retCode = employeesService.postSaveCustomerInformation(paMap);
			ResponseUtil.write(response, retCode);
		} catch (Exception e) {
			String msg = "=>   tkPlanExecuteClientName不能为空";
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage() + msg));
		}
	}

	/**
	 * 给客户信息表添加字段
	 */

	/**
	 * 查看拓客任务进度
	 */
	@RequestMapping("getTaskSchedule")
	public void getTaskSchedule(HttpServletRequest request, HttpServletResponse response) {
		try {
			String tkPlanExecuteId;
			if (  request.getParameter("tkPlanExecuteId") == null||request.getParameter("tkPlanExecuteId").trim().equals("")
					) {
				throw new Exception("tkPlanExecuteId格式有误");
			}
			WyMap paMap = wyMap.getParameter(request);
			RetCode retCode = employeesService.getTaskSchedule(paMap);
			ResponseUtil.write(response, retCode);
		} catch (Exception e) {
			String msg = "=>   tkPlanExecuteId  不能为空";
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage() + msg));
		}
	}

	/**
	 * 任务报备
	 */
	@RequestMapping("postTaskFeedback")
	public void postTaskFeedback(HttpServletRequest request, HttpServletResponse response) {
		try {
			if (request.getParameter("tk_target_report_remark") == null||request.getParameter("tk_target_report_remark").trim().equals("")) {
				throw new Exception("tk_target_report_remark格式有误");
			}
			if (request.getParameter("tkPlanId") == null||request.getParameter("tkPlanId").trim().equals("")) {
				throw new Exception("tkPlanId格式有误");
			}
			RetCode retCode = employeesService.postTaskFeedback(request);
			ResponseUtil.write(response, retCode);
		} catch (Exception e) {
			String msg = "=>   tk_target_report_remark,tkPlanId 不能为空";
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage() + msg));
		}
	}
	/**
	 * 报备申请 查询
	 */
	@RequestMapping("getTaskFeedback")
	public void getTaskFeedback(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			WyMap paMap = wyMap.getParameter(request);
			RetCode retCode = employeesService.getTaskFeedback(paMap);
			ResponseUtil.write(response, retCode);
		} catch (Exception e) {
			String msg = "=>  tkTargetReuserId不能为空";
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage() + msg));
		}
	}
	@RequestMapping("getPlanDetailByPlanId")
	public void getPlanDetailByPlanId(HttpServletRequest request, HttpServletResponse response) {
		try {
			if (  request.getParameter("tk_plan_detail_id") == null||request.getParameter("tk_plan_detail_id").trim().equals("")
					) {
				throw new Exception("tk_plan_detail_id任务id有误");
			}
			ResponseUtil.write(response, planService.getPlanDetailByPlanId(request,response));
		} catch (Exception e) {
			String msg = "=>  tk_plan_detail_id不能为空";
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage() + msg));
		}
	}
	/**
	 * 收到任务提醒
	 */
	/**
	 * 开始前提醒
	 */
	@RequestMapping("getPlanByUserId")
	public void getPlanByUserId(HttpServletRequest request, HttpServletResponse response) {
		try {
			ResponseUtil.write(response, employeesService.getPlanByUserId(request ));
		} catch (Exception e) {
			String msg = " ";
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage() + msg));
		}
	}
	
}
