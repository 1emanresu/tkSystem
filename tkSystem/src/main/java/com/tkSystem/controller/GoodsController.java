
package com.tkSystem.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tkSystem.service.GoodsService;
import com.tkSystem.tools.CLID;
import com.tkSystem.tools.ResponseUtil;
import com.tkSystem.tools.RetCode;
import com.tkSystem.tools.ToolsUtil;
import com.tkSystem.tools.WyMap;

import sun.org.mozilla.javascript.internal.Undefined;

/**
 * 
 * @author dgclrj
 *物料管理
 */
@Controller
@RequestMapping("goods")
public class GoodsController{
	@Autowired(required=true)
	GoodsService goodsService;
	
	WyMap wyMap=new WyMap();
	/**
	 * 物料管理 	　物料新增
	 * @param
	 * 
	 * @return
	 */
	@RequestMapping("post")
	public void post(HttpServletRequest request,HttpServletResponse response) {
		try {
			String tkGoodId, tkGoodName, tkGoodType, tkGoodPrice, tkGoodInsertTime="" , tkGoodAmount;
			if (request.getParameter("tkGoodName") == null || request.getParameter("tkGoodName").trim().toString() == "") {
				//if(request.getParameter("tkGoodName").isEmpty()) {	
				throw new Exception("tkGoodName字段格式有误,且不能为空");
			}
			if (request.getParameter("tkGoodType") == null || request.getParameter("tkGoodType").trim().toString() == "") {
				throw new Exception("tkGoodType字段格式有误,且不能为空");
			}
			if (request.getParameter("tkGoodPrice") == null || request.getParameter("tkGoodPrice").trim().toString() == "") {
				throw new Exception("tkGoodPrice字段格式有误,且不能为空");
			}
			if (request.getParameter("tkGoodInsertTime") == null || request.getParameter("tkGoodInsertTime").trim().toString() == "") {
				tkGoodInsertTime=ToolsUtil.getDate();
			} 
			if (request.getParameter("tkGoodAmount") == null || request.getParameter("tkGoodAmount").trim().toString() == "") {
				throw new Exception("tkGoodAmount字段格式有误,且不能为空");
			}
			tkGoodId = CLID.getID();
			WyMap paMap=wyMap.getParameter(request);
			paMap.put("tkGoodId", tkGoodId);
			if(tkGoodInsertTime.isEmpty()) {
				
			}else {
				paMap.put("tkGoodInsertTime", tkGoodInsertTime);
			}
			
			RetCode retCode=goodsService.post(paMap);
			ResponseUtil.write(response, retCode);
		} catch (Exception e) {
			String msg="tkGoodId, tkGoodName, tkGoodType, tkGoodPrice, tkGoodInsertTime, tkGoodAmount不能为空";
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage() +msg));
		}
		
	}
	/**
	 * 物料管理 	　物料查询
	 * @return
	 */
	@RequestMapping("get")
	public void  get(HttpServletRequest request,HttpServletResponse response) {
		try {
			if (request.getParameter("tkGoodId") == null || request.getParameter("tkGoodId").trim().toString() == "") {
				throw new Exception("tkGoodId字段格式有误,且不能为空");
			}
			WyMap paMap=wyMap.getParameter(request);
			RetCode retCode=goodsService.get(paMap);
			ResponseUtil.write(response, retCode);
		} catch (Exception e) {
			String msg="tkGoodId不能为空";
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage() +msg));
		}
	}
	/**
	 * 物料管理 	　物料修改
	 * @return
	 */
	@RequestMapping("put")
	public void put(HttpServletRequest request,HttpServletResponse response) {
		try {
			if (request.getParameter("tkGoodId") == null || request.getParameter("tkGoodId").trim().toString() == "") {
				throw new Exception("tkGoodId字段格式有误,且不能为空");
			}
			WyMap paMap=wyMap.getParameter(request);
			RetCode retCode=goodsService.put(paMap);
			ResponseUtil.write(response, retCode);
		} catch (Exception e) {
			String msg="tkGoodId不能为空";
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage() +msg));
		}
	}
	/**
	 * 物料管理 	　物料删除
	 * @return
	 */
	@RequestMapping("delete")
	public void delete(HttpServletRequest request,HttpServletResponse response) {
		try {
			if (request.getParameter("tkGoodId") == null || request.getParameter("tkGoodId").trim().toString() == "") {
				throw new Exception("tkGoodId字段格式有误,且不能为空");
			}
			WyMap paMap=wyMap.getParameter(request);
			RetCode retCode=goodsService.delete(paMap);
			ResponseUtil.write(response, retCode);
		} catch (Exception e) {
			String msg="tkGoodId不能为空";
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage() +msg));
		}
	}
	

}
