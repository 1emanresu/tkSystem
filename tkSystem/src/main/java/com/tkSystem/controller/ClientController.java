package com.tkSystem.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tkSystem.service.ClientService;
import com.tkSystem.service.smallProgram.EmployService;
import com.tkSystem.service.smallProgram.PlanService;
import com.tkSystem.service.smallProgram.TkPlanExecuteClientService;
import com.tkSystem.service.smallProgram.TkPlanExecuteService;
import com.tkSystem.tools.ResponseUtil;
import com.tkSystem.tools.RetCode;
import com.tkSystem.tools.ToolsUtil;
import com.tkSystem.tools.WyMap;

@Controller
@RequestMapping("client")
public class ClientController {
	/**
	 * 服务注册
	 */
	@Autowired(required = true)
	 ClientService clientService;
	/**
	 * 客户信息 已录入查询
	 * 
	 */
	@RequestMapping("getClientInfo")
	public void getClientInfo(HttpServletRequest request, HttpServletResponse response) {
		try {
			ResponseUtil.write(response, clientService.getClientInfo(request ));
		} catch (Exception e) {
			String msg = " ";
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage() + msg));
		}
	}

	/**
	 * 
	 * 本月业务
	 * 
	 */
	@RequestMapping("getWorkNumber")
	public void getWorkNumber(HttpServletRequest request, HttpServletResponse response) {
		try {
			ResponseUtil.write(response, clientService.getWorkNumber(request ));
		} catch (Exception e) {
			String msg = " ";
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage() + msg));
		}
	}

	/**
	 * 组别名次
	 * 
	 */
	@RequestMapping("getTeamIndex")
	public void getTeamIndex(HttpServletRequest request, HttpServletResponse response) {
		try {
			ResponseUtil.write(response, clientService.getTeamIndex(request ));
		} catch (Exception e) {
			String msg = " ";
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage() + msg));
		}
	}

	/**
	 * 公司排名
	 * 
	 */
	@RequestMapping("getCompanyIndex")
	public void getCompanyIndex(HttpServletRequest request, HttpServletResponse response) {
		try {
			ResponseUtil.write(response, clientService.getTeamIndex(request ));
		} catch (Exception e) {
			String msg = " ";
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage() + msg));
		}
	}

	/**
	 * 客户跟进列表查询
	 * 
	 */
	@RequestMapping("getClientInfoByPlanId")
	public void getClientInfoByPlanId(HttpServletRequest request, HttpServletResponse response) {
		try {
			if (request.getParameter("tkPlanId") == null || request.getParameter("tkPlanId").trim().equals("")) {
				throw new Exception("tkPlanId任务编号为空");
			}

			ResponseUtil.write(response, clientService.getClientInfo(request ));
		} catch (Exception e) {
			String msg = "tkPlanId任务编号不能为空 ";
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage() + msg));
		}
	}

	@RequestMapping("getTkChannel")
	public void getTkChannel(HttpServletRequest request, HttpServletResponse response) {
		try {

			ResponseUtil.write(response, clientService.getTkChannel(request ));
		} catch (Exception e) {
			String msg = "  ";
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage() + msg));
		}
	}
	/**
	 * 拓客进度
	 * @param request
	 * @param response
	 */
	@RequestMapping("getClientNumByChannel")
	public void getClientNumByChannel(HttpServletRequest request, HttpServletResponse response) {
		try {
			ResponseUtil.write(response, clientService.getClientNumByChannel(request ));
		} catch (Exception e) {
			String msg = "  ";
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage() + msg));
		}
	}
	@RequestMapping("postClientInfo")
	public void postClientInfo(HttpServletRequest request, HttpServletResponse response) {
		try {
			String [] str= {"tk_client_name#客户名字不能为空"
					,"tk_client_phone#客户手机号码不能为空"
					,"tk_client_sex#客户性别不能为空"
					,"tk_plan_id#任务编号不能为空"
					,"tk_client_goods_amount#礼品数量不能为空"
					,"tk_client_goods_name#礼品名称不能为空"
					,"tk_client_location_detail#详细地址不能为空"
					,"tk_client_location#地址不能为空"
					};
				ToolsUtil.requestIsNull(request, str);
			if (request.getParameterValues("tk_client_photo") == null) {
				if (request.getParameter("tk_client_photo") == null
						|| request.getParameter("tk_client_photo").trim().equals("")) {
					throw new Exception("tk_client_photo照片为空");
				}
			}
			ResponseUtil.write(response, clientService.postClientInfo(request ));
		} catch (Exception e) {
			String msg = "  ";
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage() + msg));
		}
	}
	/**
	 * 客户跟进
	 */
	@RequestMapping("putClientInfo")
	public void putClientInfo(HttpServletRequest request, HttpServletResponse response) {
		try {
			String [] str= {"tk_client_id#客户编号不能为空"
					 
					};
				ToolsUtil.requestIsNull(request, str);
			 
			ResponseUtil.write(response, clientService.putClientInfo(request ));
		} catch (Exception e) {
			String msg = "  ";
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage() + msg));
		}
	}
}
