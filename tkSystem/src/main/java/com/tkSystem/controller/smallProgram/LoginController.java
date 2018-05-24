package com.tkSystem.controller.smallProgram;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tkSystem.service.smallProgram.employServiceInterface;
import com.tkSystem.service.smallProgram.impl.PlanService;
import com.tkSystem.service.smallProgram.impl.TkPlanExecuteClientService;
import com.tkSystem.service.smallProgram.impl.TkPlanExecuteService;
import com.tkSystem.tools.ResponseUtil;
import com.tkSystem.tools.RetCode;

@Controller
@RequestMapping("login")
public class LoginController {
	/**
	 * 服务注册
	 */
	@Autowired(required=true)
	employServiceInterface employService;
	

	/**
	 * 用户登陆
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("userLogin")
	public void postPlan(HttpServletRequest request,HttpServletResponse response) {
		try {
			ResponseUtil.write(response, employService.userLogin(request, response));
			
		} catch (Exception e) {
			ResponseUtil.write(response, RetCode.getErrorCode());
			
		}
	}

}
