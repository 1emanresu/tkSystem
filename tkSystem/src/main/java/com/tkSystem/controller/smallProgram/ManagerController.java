package com.tkSystem.controller.smallProgram;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tkSystem.service.GoodsServiceInterface;
import com.tkSystem.service.smallProgram.PlanServiceInterface;
import com.tkSystem.service.smallProgram.TkPlanExecuteClientServiceInterface;
import com.tkSystem.service.smallProgram.TkPlanExecuteServiceInterface;
import com.tkSystem.service.smallProgram.employServiceInterface;
import com.tkSystem.tools.ImageUpload;
import com.tkSystem.tools.ResponseUtil;
import com.tkSystem.tools.RetCode;
import com.tkSystem.tools.ToolsUtil;
import com.tkSystem.tools.WyMap;

@Controller
@RequestMapping("manager")
public class ManagerController {
	/**
	 * 服务注册
	 */
	@Autowired(required = true)
	PlanServiceInterface planService;
	@Autowired(required = true)
	employServiceInterface employService;
	@Autowired(required = true)
	TkPlanExecuteClientServiceInterface tkPlanExecuteClientService;
	@Autowired(required = true)
	TkPlanExecuteServiceInterface tkPlanExecuteService;
	@Autowired(required = true)
	GoodsServiceInterface goodsService;
	
	WyMap wyMap = new WyMap();

	/**
	 * 任务管理 (发布任务 ,分配任务,查看拓客人员任务进度、任务总进度,)
	 * 
	 */
	/**
	 * 发布任务
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("postPlan")
	public void postPlan(HttpServletRequest request, HttpServletResponse response) {
		try {
			String [] str= {"tkPlanDetailTitle#任务标题不能为空"
					,"tkPlanDetailRemark#任务备注不能为空"
					,"tkPlanDetailStart#任务开始时间不能为空"
					,"tkPlanDetailRemark#任务备注不能为空"
					,"tkPlanDetailStart#开始时间不能为空"
					,"tkPlanDetailEnd#结束时间不能为空"
					,"tkPlanDetailTarget#目标不能为空"
					,"tkPlanDetailContactsName#任务联系人姓名不能为空"
					,"tkPlanDetailContactsSex#任务联系人性别不能为空"
					,"tkPlanDetailLocation#任务地址不能为空"
					,"tkPlanDetailLocationDetail#任务详细地址不能为空"
					,"tkPlanDetailGoodName#任务礼品不能为空"
					,"tkPlanDetailGoodAmount#任务礼品数量不能为空"
					,"tkPlanContactsPhone#联系人电话不能为空"
					,"tkChannelId#渠道编号不能为空"
					,"latitude#纬度，浮点数，范围为-90~90，负数表示南纬不能为空"
					,"longitude#经度，浮点数，范围为-180~180，负数表示西经不能为空"
					,"address#详细地址"
					};
				ToolsUtil.requestIsNull(request, str);
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				java.util.Date now = df.parse(request.getParameter("tkPlanDetailStart"));
				java.util.Date now1 = df.parse(request.getParameter("tkPlanDetailEnd"));
			if (request.getParameterValues("tkPlanDetailPhoto") == null) {
				if (request.getParameter("tkPlanDetailPhoto") == null
						|| request.getParameter("tkPlanDetailPhoto").trim().equals("")) {
					throw new Exception("tkPlanDetailPhoto照片不能为空");
				}
			}
		
			ResponseUtil.write(response, planService.postPlan(request));
		} catch (ParseException e) {
			String msg = "=>  tkPlanDetailId, tkPlanDetailTitle,tkPlanDetailRemark, tkPlanDetailStart,tkPlanDetailEnd, tkPlanDetailTarget, tkPlanDetailContactsName,tkPlanDetailContactsSex, tkPlanDetailLocation,tkPlanDetailLocationDetail, tkPlanDetailGoodName,tkPlanDetailGoodAmount, tkPlanDetailPhoto  不能为空";
			ResponseUtil.write(response, RetCode.getErrorCode("yyyy-MM-dd HH:mm:ss时间格式转化异常" + msg));
		} catch (Exception e) {
			String msg = "=>  tkPlanDetailId, tkPlanDetailTitle,tkPlanDetailRemark, tkPlanDetailStart,tkPlanDetailEnd, tkPlanDetailTarget, tkPlanDetailContactsName,tkPlanDetailContactsSex, tkPlanDetailLocation,tkPlanDetailLocationDetail, tkPlanDetailGoodName,tkPlanDetailGoodAmount, tkPlanDetailPhoto  不能为空";
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage() + msg));
		}

	}

	/**
	 * 修改任务
	 * 
	 * @param request
	 * @param response
	 * @return
	 */

	@RequestMapping("putPlan")
	public void putPlan(HttpServletRequest request, HttpServletResponse response) {
		try {
			String str[] = { "tkPlanId", "tkPlanUserId", "tkPlanUserName", "tkClientTypeId", "tkPlanLocId",
					"tkPlanTime", "tkPlanTarget", "tkPlanTarget", "tkPlanState" };
			ToolsUtil.requestIsNull(request, str);
			ResponseUtil.write(response, planService.putPlan(request));
		} catch (Exception e) {
			String msg = "=> tkPlanId, tkPlanUserId, tkPlanUserName, tkClientTypeId, tkPlanLocId, tkPlanTime, tkPlanTarget,tkPlanState不能为空";
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage() + msg));
		}
	}

	/**
	 * 分配任务
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("postPlanExecute")
	public void postPlanExecute(HttpServletRequest request, HttpServletResponse response) {
		try {
			String str[] = { "tkPlanId", "tkPlanUserId" };
			ToolsUtil.requestIsNull(request, str);
			ResponseUtil.write(response, planService.postPlanExecute(request));
		} catch (Exception e) {
			String msg = "=> tkPlanId, tkPlanUserId 不能为空";
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage() + msg));
		}

	}

	/**
	 * 分配任务 查询
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("getPlanByUserId")
	public void getPlanByUserId(HttpServletRequest request, HttpServletResponse response) {
		try {

			ResponseUtil.write(response, planService.getPlanByUserId(request ));
		} catch (Exception e) {
			String msg = "=>  tkPlanUserId不能为空";
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage() + msg));
		}

	}

	@RequestMapping("getPlanByUserIdJ")
	public void getPlanByUserIdJ(HttpServletRequest request, HttpServletResponse response) {
		try {

			ResponseUtil.write(response, planService.getPlanByUserIdJ(request, response ));
		} catch (Exception e) {
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage()));
		}

	}

	@RequestMapping("getTaskToClock")
	public void getTaskToClock(HttpServletRequest request, HttpServletResponse response) {
		try {
			ResponseUtil.write(response, planService.getTaskToClock(request ));
		} catch (Exception e) {
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage()));
		}

	}

	@RequestMapping("getPlanByUserIdZ")
	public void getPlanByUserIdZ(HttpServletRequest request, HttpServletResponse response) {
		try {
			ResponseUtil.write(response, planService.getPlanByUserIdZ(request, response ));
		} catch (Exception e) {
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage()));
		}

	}

	/**
	 * 任务总进度
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("getPlan")
	public void getPlan(HttpServletRequest request, HttpServletResponse response) {
		try {
			ResponseUtil.write(response, planService.getPlan(request, response ));
		} catch (Exception e) {
			String msg = "=> tkPlanId不能为空";
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage() + msg));
		}
	}
	/**
	 * 团队进度 查询  
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("getTeamIndex")
	public void getTeamIndex(HttpServletRequest request, HttpServletResponse response) {
		try {
			ResponseUtil.write(response, employService.getTeamIndex(request ));
		} catch (Exception e) {
			String msg = " ";
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage() + msg));
		}
	}
	/**
	 * 团队进度 查询 （排序前三名）
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("getPlanThree")
	public void getPlanThree(HttpServletRequest request, HttpServletResponse response) {
		try {
			ResponseUtil.write(response, planService.getPlanThree(request ));
		} catch (Exception e) {
			String msg = " ";
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage() + msg));
		}
	}
	/**
	 * 获取正在管理的任务信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("getAreadyPlan")
	public void uploadHead(HttpServletRequest request, HttpServletResponse response) {

		try {
			WyMap wymap=WyMap.getParameter(request);
			wymap.put("tk_plan_detail_user_id", request.getParameter("tkUserId"));
			ResponseUtil.write(response, RetCode.getSuccessCode(planService.getAreadyPlan(wymap)));
		} catch (Exception e) {
			String msg = "=> tkUserHead不能为空";
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage() + msg));
		}
	}

	/**
	 * 团队进度 查询 （排序前三名以后）
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("getPlanThreeH")
	public void getPlanThreeH(HttpServletRequest request, HttpServletResponse response) {
		try {
			ResponseUtil.write(response, planService.getPlanThree(request ));
		} catch (Exception e) {
			String msg = " ";
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage() + msg));
		}
	}

	/**
	 * 查看拓客人员任务进度
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("getEmployeePlan")
	public void getEmployeePlan(HttpServletRequest request, HttpServletResponse response) {
		try {
			String tkPlanId;
			if (request.getParameter("tkPlanId").trim().equals("") || request.getParameter("tkPlanId") == null) {
				throw new Exception("tkPlanId格式有误");
			}

			ResponseUtil.write(response, planService.getEmployeePlan(request ));
		} catch (Exception e) {
			String msg = "=> tkPlanId不能为空";
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage() + msg));
		}
	}

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
	@RequestMapping("postEmployee")
	public void postEmployee(HttpServletRequest request, HttpServletResponse response) {
		try {
			if (request.getParameter("userId") == null
					|| request.getParameter("userId").trim().equals("")) {
				throw new Exception("userId格式有误");
			}
			ResponseUtil.write(response, employService.postEmployee(request ));
		} catch (Exception e) {
			String msg = "=> userId不能为空";
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage() + msg));
		}

	}

	/**
	 * 解除拓客人员
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("deleteEmployee")

	public void deleteEmployee(HttpServletRequest request, HttpServletResponse response) {
		try {
			if (request.getParameter("userId") == null
					|| request.getParameter("userId").trim().equals("")) {
				throw new Exception("userId格式有误");
			}
			ResponseUtil.write(response, employService.updateGradePid(request ));
		} catch (Exception e) {
			String msg = "=> userId不能为空";
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage() + msg));
		}
	}

	/**
	 * 人员选择 查询
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("getEmployee")

	public void getEmployee(HttpServletRequest request, HttpServletResponse response) {
		try {
			ResponseUtil.write(response, employService.getEmployee(request ));
		} catch (Exception e) {
			String msg = " ";
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage() + msg));
		}
	}

	/**
	 * 人员选择 查询 查询 时可以给参数
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("getEmployeeByName")

	public void getEmployeeByName(HttpServletRequest request, HttpServletResponse response) {
		try {
			ResponseUtil.write(response, employService.getEmployee(request ));
		} catch (Exception e) {
			String msg = "=>tkUserName不能为空 ";
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage() + msg));
		}
	}

	/**
	 * 拓客任务 (上传拓客客户信息,查看拓客记录,)
	 * 
	 */
	/**
	 * 上传拓客客户信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("postTkPlanExecute")
	public void postTkPlanExecute(HttpServletRequest request, HttpServletResponse response) {
		try {
			String str[] = { "tkPlanExecuteName", "tkPlanExecuteContent", "tkPlanId", "tkPlanExecuteStartTime",
					"tkPlanExecuteEndTime", "tkPlanExecuteCutormId", "tkUserId", "tkPlanExecuteState",
					"tkPlanExecuteRemark" };
			ToolsUtil.requestIsNull(request, str);
			ResponseUtil.write(response, tkPlanExecuteService.postTkPlanExecute(request ));
		} catch (Exception e) {
			String msg = "=> tkPlanExecuteName, tkPlanExecuteContent, tkPlanId, tkPlanExecuteStartTime,tkPlanExecuteEndTime, tkPlanExecuteCutormId, tkUserId, tkPlanExecuteState, tkPlanExecuteRemark不能为空";
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage() + msg));
		}

	}

	/**
	 * 查看拓客记录
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("getTkPlanExecute")
	public void getTkPlanExecute(HttpServletRequest request, HttpServletResponse response) {
		try {
			String str[] = { "tkPlanExecuteId" };
			ToolsUtil.requestIsNull(request, str);
			ResponseUtil.write(response, tkPlanExecuteService.postTkPlanExecute(request ));
		} catch (Exception e) {
			String msg = "=>  tkPlanExecuteId不能为空";
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage() + msg));
		}
	}

	/**
	 * 客户资源保护 (客户资源保护,)
	 * 
	 */
	/**
	 * 客户资源保护
	 */
	@RequestMapping("putTkPlanExecuteClient")
	public void putTkPlanExecuteClient(HttpServletRequest request, HttpServletResponse response) {
		try {
			String str[] = { "tkPlanExecuteClientName" };
			ToolsUtil.requestIsNull(request, str);
			ResponseUtil.write(response, tkPlanExecuteClientService.putTkPlanExecuteClient(request));
		} catch (Exception e) {
			String msg = "=>  tkPlanExecuteClientName不能为空";
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage() + msg));
		}
	}

	/**
	 * 拓客人员今日任务
	 */
	@RequestMapping("getTodayTask")
	public void getTodayTask(HttpServletRequest request, HttpServletResponse response) {
		WyMap paMap = wyMap.getParameter(request);
		RetCode retCode = planService.getTodayTask(paMap);
		ResponseUtil.write(response, retCode);
	}
	/**
	 * 经理审批物料申请查询
	 */
	@RequestMapping("getGoodsByManage")
	public void getGoodsByManage(HttpServletRequest request, HttpServletResponse response) {
		WyMap paMap = wyMap.getParameter(request);
		ResponseUtil.write(response, goodsService.s(paMap));
	}
	/**
	 * 经理审批物料申请
	 */
	@RequestMapping("putGoodsByManage")
	public void putGoodsByManage(HttpServletRequest request, HttpServletResponse response) {
	//	tk_good_apply_plan_id
		RetCode re=RetCode.getErrorCode();
		String str[]= {"tk_good_apply_id","tkGoodApplyState"};
		try {
			ToolsUtil.requestIsNull(request, str);
			WyMap paMap = wyMap.getParameter(request);
			re=(RetCode) goodsService.ss(paMap);
		} catch (Exception e) {
			e.printStackTrace();
			re.put("msg", e.getMessage());
		}
		
		ResponseUtil.write(response, re);
	}
	/**
	 * 经理查看物料申请详情
	 */
	@RequestMapping("getGoodsByManageDetail")
	public void getGoodsByManageDetail(HttpServletRequest request, HttpServletResponse response) {
	//	tk_good_apply_plan_id
		RetCode re=RetCode.getErrorCode();
		String str[]= {"tk_good_apply_id"};
		try { 
			ToolsUtil.requestIsNull(request, str);
			WyMap paMap = wyMap.getParameter(request);
			re=(RetCode) goodsService.getGoodsByManageDetail(paMap);
		} catch (Exception e) {
			e.printStackTrace();
			re.put("msg", e.getMessage());
		}
		
		ResponseUtil.write(response, re);
	}
	/**
	 * 经理审批任务报备
	 */
	@RequestMapping("putReportByManage")
	public void putReportByManage(HttpServletRequest request, HttpServletResponse response) {
	//	tk_good_apply_plan_id
		RetCode re=RetCode.getErrorCode();
		String str[]= {"tkTargetReportId","tk_report_state","tk_plan_detail_starttime"};
		try {
			ToolsUtil.requestIsNull(request, str);
			WyMap paMap = wyMap.getParameter(request);
			re=(RetCode) planService.putReportByManage(paMap);
		} catch (Exception e) {
			e.printStackTrace();
			re.put("msg", e.getMessage());
		}
		
		ResponseUtil.write(response, re);
	}
	/**
	 * 经理查看任务报备详情
	 */
	@RequestMapping("getReportByManageDetail")
	public void getReportByManageDetail(HttpServletRequest request, HttpServletResponse response) {
	//	tk_good_apply_plan_id
		RetCode re=RetCode.getErrorCode();
		String str[]= {"tkTargetReportId"};
		try { 
			ToolsUtil.requestIsNull(request, str);
			WyMap paMap = wyMap.getParameter(request);
			re=(RetCode) planService.getReportByManageDetail(paMap);
		} catch (Exception e) {
			e.printStackTrace();
			re.put("msg", e.getMessage());
		}
		
		ResponseUtil.write(response, re);
	}/**
	 * 经理查看任务报备
	 */
	@RequestMapping("getReportByManage")
	public void getReportByManage(HttpServletRequest request, HttpServletResponse response) {
	//	tk_good_apply_plan_id
		RetCode re=RetCode.getErrorCode();
		try { 
			WyMap paMap = wyMap.getParameter(request);
			re=(RetCode) planService.getReportByManage(paMap);
		} catch (Exception e) {
			e.printStackTrace();
			re.put("msg", e.getMessage());
		}
		
		ResponseUtil.write(response, re);
	}
}
