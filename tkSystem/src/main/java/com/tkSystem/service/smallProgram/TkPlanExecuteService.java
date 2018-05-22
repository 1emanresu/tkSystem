package com.tkSystem.service.smallProgram;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tkSystem.dao.mapper.TkGoodMapper;
import com.tkSystem.dao.mapper.TkPlanExecuteMapper;
import com.tkSystem.dao.mapper.TkPlanMapper;
import com.tkSystem.dao.mapper.TkUserMapper;
import com.tkSystem.tools.CLID;
import com.tkSystem.tools.ResponseUtil;
import com.tkSystem.tools.RetCode;
import com.tkSystem.tools.ToolsUtil;
import com.tkSystem.tools.WyMap;

@Service("tkPlanExecuteService")
public class TkPlanExecuteService {
	@Resource
	private TkPlanExecuteMapper TkPlanExecuteDao;

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
	public RetCode postTkPlanExecute(HttpServletRequest request) {
		RetCode retCode;
		String tkPlanExecuteId;
		WyMap paMap = WyMap.getParameter(request);
		try {
			tkPlanExecuteId = CLID.getID();
			paMap.put("tkPlanExecuteId", tkPlanExecuteId);
			int ret = TkPlanExecuteDao.insertSelective(paMap);
			retCode = RetCode.getSuccessCode(ret);
		} catch (Exception e) {
			e.printStackTrace();
			return RetCode.getErrorCode();
		}

		return retCode;
	}

	/**
	 * 查看拓客记录
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public RetCode getTkPlanExecute(HttpServletRequest request, HttpServletResponse response) {
		RetCode retCode;
		WyMap paMap = WyMap.getParameter(request);
		try {
			retCode = RetCode.getSuccessCode(TkPlanExecuteDao.selectByPrimaryKey(paMap));
		} catch (Exception e) {
			e.printStackTrace();
			return RetCode.getErrorCode();
		}

		return retCode;
	}

}
