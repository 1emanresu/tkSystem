package com.tkSystem.service.smallProgram;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tkSystem.dao.mapper.TkGoodMapper;
import com.tkSystem.dao.mapper.TkPlanExecuteClientMapper;
import com.tkSystem.dao.mapper.TkPlanExecuteMapper;
import com.tkSystem.dao.mapper.TkPlanMapper;
import com.tkSystem.dao.mapper.TkUserMapper;
import com.tkSystem.tools.CLID;
import com.tkSystem.tools.ResponseUtil;
import com.tkSystem.tools.RetCode;
import com.tkSystem.tools.ToolsUtil;
import com.tkSystem.tools.WyMap;

@Service("tkPlanExecuteClientService")
public class TkPlanExecuteClientService {
	@Resource
	private TkPlanExecuteClientMapper TkPlanExecuteClientDao;

	/**
	 * 客户资源保护 (客户资源保护,)
	 * 
	 */
	/**
	 * 客户资源保护
	 */
	public RetCode putTkPlanExecuteClient(HttpServletRequest request) {
		RetCode retCode;
		WyMap paMap = WyMap.getParameter(request);
		try {
			String tkPlanExecuteClientId = CLID.getID();
			paMap.put("tkPlanExecuteClientId", tkPlanExecuteClientId);
			int ret = TkPlanExecuteClientDao.updateByPrimaryKeySelective(paMap);
			retCode = RetCode.getSuccessCode(ret);
		} catch (Exception e) {
			e.printStackTrace();
			return RetCode.getErrorCode();
		}

		return retCode;
	}
}
