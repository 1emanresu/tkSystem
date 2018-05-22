package com.tkSystem.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tkSystem.service.PersonnelManagementServier;
import com.tkSystem.tools.ResponseUtil;
import com.tkSystem.tools.RetCode;
import com.tkSystem.tools.WyMap;

/**
 * 拓客人员管理
 * */

@Controller
@RequestMapping("personnelManagement")
public class PersonnelManagementController {
	WyMap wyMap=new WyMap();
	
	@Autowired
	PersonnelManagementServier personnelManagementServier;
	
	/**
	 * 获取所有拓客人员
	 * */
	@RequestMapping("getPersonneInfo")
	public void getPersonneInfo(HttpServletRequest request,HttpServletResponse response){
		WyMap paMap=wyMap.getParameter(request);
		RetCode retCode=personnelManagementServier.getPersonneInfo(paMap);
		ResponseUtil.write(response, retCode);
	}
	
	/**
	 * 删除拓客人员，删除时，同时解除上下级关系
	 * */
	@RequestMapping("delPersonneInfo")
	public void delPersonneInfo(HttpServletRequest request,HttpServletResponse response){
		RetCode retCode=RetCode.getErrorCode();
		try {
			String tkUserGradeId, tkUserState;
			if(request.getParameter("tkUserGradeId")==null||request.getParameter("tkUserGradeId").trim().equals("")) {
				throw new Exception("tkUserGradeId格式不正确");
			}
			if(request.getParameter("tkUserState")==null||request.getParameter("tkUserState").trim().equals("")) {
				throw new Exception("tkUserState格式不正确");
			}
			WyMap paMap=wyMap.getParameter(request);
			 retCode=personnelManagementServier.delPersonneInfo(paMap);
			ResponseUtil.write(response, retCode);
		
		} catch (Exception e) {
			String msg="=》tkUserGradeId	 tkUserState不能为空";
			retCode.put("msg", e.getMessage()+msg);
			ResponseUtil.write(response, retCode);
		}
	}
	
}
